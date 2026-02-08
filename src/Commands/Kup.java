package Commands;

import Predmety.Predmety;

public class Kup implements Command {
    private Hrac hrac;

    public Kup(Hrac hrac) {
        this.hrac = hrac;
    }

    @Override
    public String execute(String[] args) {
        if (args.length == 0) {
            return "Co chceš koupit?";
        }

        String nazevPredmetu = String.join(" ", args);
        Predmety predmet = hrac.getGameData().findItem(nazevPredmetu);

        if (predmet == null) {
            return "Takový předmět neexistuje nebo ho nevidíš v nabídce.";
        }

        // Logika pro specifické předměty/obchody
        // Předpokládáme, že obchody zatím nejsou v JSONu definovány jako inventáře,
        // nebo kontrolujeme ID lokace
        String locId = hrac.getAktualniLokace().getId();
        boolean canBuy = false;

        // Mikrofony -> Obchod (loc_obchod)
        if (predmet.getType().equals("EQUIPMENT") && (predmet.getId().startsWith("item_mikrofon"))) {
            if (locId != null && locId.equals("loc_obchod")) {
                canBuy = true;
            } else {
                return "Mikrofony koupíš jen v obchodě (loc_obchod).";
            }
        }
        // Beaty -> Producent
        else if (predmet.getType().equals("CONSUMABLE") && predmet.getId().startsWith("item_beat")) {
            // Producent je v loc_byt_producenta
            if (locId != null && locId.equals("loc_byt_producenta")) {
                canBuy = true;
                // Kontrola reputace
                int rep = hrac.getReputace();
                int reqRep = 0;
                if (predmet.getRarita().equals("RARE"))
                    reqRep = 250;
                if (predmet.getRarita().equals("EPIC"))
                    reqRep = 800;
                if (predmet.getRarita().equals("LEGENDARY"))
                    reqRep = 2500;
                if (predmet.getRarita().equals("MYTHIC"))
                    reqRep = 5000;

                if (rep < reqRep) {
                    return "TDF Producent: S tebou ještě nepracuju. Potřebuješ alespoň " + reqRep
                            + " reputace, abych ti prodal tento beat! (máš " + rep + ")";
                }
            } else {
                return "Beaty koupíš jen u producenta.";
            }
        } else {
            return "Tohle se asi nedá koupit.";
        }

        if (canBuy) {
            // Parsování ceny "10$" -> 10.0
            String priceStr = predmet.getCena().replace("$", "").trim();
            double price = Double.parseDouble(priceStr);

            if (hrac.getPenize() >= price) {
                hrac.setPenize(hrac.getPenize() - price);

                if (predmet.getType().equals("EQUIPMENT") && predmet.getId().startsWith("item_mikrofon")) {
                    hrac.setVybaveneMikrofon(predmet);
                    return "Koupil jsi a vybavil: " + predmet.getNazev();
                } else if (predmet.getId().startsWith("item_beat")) {
                    String randomName = getRandomBeatName(predmet.getNazev());
                    Predmety uniqueBeat = new Predmety(predmet, randomName);
                    hrac.pridejPredmet(uniqueBeat);
                    return "Koupil jsi: " + uniqueBeat.getNazev() + " (" + uniqueBeat.getRarita() + ")";
                } else {
                    hrac.pridejPredmet(predmet);
                    return "Koupil jsi: " + predmet.getNazev();
                }
            } else {
                return "Nemáš dost peněz! Cena: " + predmet.getCena();
            }
        }

        return "Něco se pokazilo.";
    }

    private String getRandomBeatName(String baseName) {
        String[] adjectives = { "Lo-fi", "Hardcore", "Smooth", "Bouncy", "Dark", "Epic", "Street", "Deep", "Chill",
                "Aggressive" };
        String[] nouns = { "Beat", "Instrumental", "Rhythm", "Sound", "Vibe", "Loop", "Sample", "Drumkit", "Pulse",
                "Wave" };
        java.util.Random rand = new java.util.Random();
        int adjIdx = rand.nextInt(adjectives.length);
        int nounIdx = rand.nextInt(nouns.length);
        return adjectives[adjIdx] + " " + nouns[nounIdx] + " (" + (rand.nextInt(100)) + ")";
    }

    @Override
    public boolean exit() {
        return false;
    }
}
