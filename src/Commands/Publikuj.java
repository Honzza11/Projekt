package Commands;

import Predmety.Predmety;

public class Publikuj implements Command {
    private Hrac hrac;

    public Publikuj(Hrac hrac) {
        this.hrac = hrac;
    }

    @Override
    public String execute(String[] args) {
        // Najít skladbu
        if (hrac.getInventarList().stream().noneMatch(p -> p.getId().contains("item_skladba"))) {
            return "Nemáš žádnou skladbu (item_skladba) k publikování!";
        }

        Predmety song = null;
        if (args.length > 0) {
            String songName = String.join(" ", args);
            for (Predmety p : hrac.getInventarList()) {
                if (p.getId().contains("item_skladba") && p.getNazev().equalsIgnoreCase(songName)) {
                    song = p;
                    break;
                }
            }
            if (song == null) {
                return "Skladbu '" + songName + "' nemáš v inventáři.";
            }
        } else {
            // Kontrola, zda existuje více skladeb
            long songCount = hrac.getInventarList().stream().filter(p -> p.getId().contains("item_skladba")).count();
            if (songCount > 1) {
                StringBuilder sb = new StringBuilder("Máš více skladeb. Napiš 'publikuj [nazev]':\n");
                for (Predmety p : hrac.getInventarList()) {
                    if (p.getId().contains("item_skladba")) {
                        sb.append("- ").append(p.getNazev()).append(" [").append(p.getRarita()).append("]\n");
                    }
                }
                return sb.toString();
            } else {
                // Automaticky vybrat tu jedinou
                for (Predmety p : hrac.getInventarList()) {
                    if (p.getId().contains("item_skladba")) {
                        song = p;
                        break;
                    }
                }
            }
        }

        if (song == null) {
            return "Chyba: Skladba nenalezena.";
        }

        String locId = hrac.getAktualniLokace().getId();
        if (locId == null)
            return "Nevíš kde jsi.";

        boolean isRadio = locId.equals("loc_radio");
        boolean isStudio = locId.equals("loc_studio");

        if (!isRadio && !isStudio) {
            return "Publikovat můžeš jen v Rádiu nebo ve Studiu.";
        }

        // Logika
        // Studio: Více peněz, méně reputace
        // Rádio: Vyvážené

        double baseMoney = 0;
        int baseRep = 0;

        String rarity = song.getRarita();
        if (rarity == null)
            rarity = "COMMON";

        switch (rarity) {
            case "MYTHIC":
                baseMoney = 10000;
                baseRep = 2000;
                break;
            case "LEGENDARY":
                baseMoney = 5000;
                baseRep = 1000;
                break;
            case "EPIC":
                baseMoney = 1500;
                baseRep = 300;
                break;
            case "RARE":
                baseMoney = 500;
                baseRep = 100;
                break;
            case "COMMON":
                baseMoney = 100;
                baseRep = 20;
                break;
            default:
                baseMoney = 50;
                baseRep = 10;
        }

        // Aplikování bonusu za mikrofon na peníze
        Predmety mic = hrac.getVybaveneMikrofon();
        double multiplier = 1.0;
        if (mic != null && mic.getBonus() != null) {
            try {
                // Formát "110%" -> 1.1
                String b = mic.getBonus().replace("%", "").trim();
                double val = Double.parseDouble(b);
                multiplier = val / 100.0;
            } catch (Exception e) {

            }
        }

        double finalMoney = baseMoney * multiplier;
        int finalRep = baseRep;

        if (isStudio) {
            finalMoney *= 1.5; // Bonusové peníze v studiu
            finalRep *= 0.5; // Menší reputace v studiu
        } else if (isRadio) {
            // Vyvážené
        }

        hrac.odeberPredmet(song);
        hrac.setPenize(hrac.getPenize() + finalMoney);
        hrac.setReputace(hrac.getReputace() + finalRep);

        return "Publikoval jsi skladbu '" + song.getNazev() + "'!\n" +
                "Získal jsi: " + (int) finalMoney + "$ (Mic Bonus: x" + multiplier + ") a " + finalRep + " reputace.";
    }

    @Override
    public boolean exit() {
        return false;
    }
}
