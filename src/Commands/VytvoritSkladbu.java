package Commands;

import Predmety.Predmety;

public class VytvoritSkladbu implements Command {
    private Hrac hrac;

    public VytvoritSkladbu(Hrac hrac) {
        this.hrac = hrac;
    }

    @Override
    public String execute(String[] args) {
        String locId = hrac.getAktualniLokace().getId();
        if (locId == null || (!locId.equals("loc_byt") && !locId.equals("loc_studio"))) {
            return "Skladby můžeš tvořit jen doma (byt) nebo ve studiu.";
        }

        java.util.List<Predmety> beats = new java.util.ArrayList<>();
        java.util.List<Predmety> texts = new java.util.ArrayList<>();

        for (Predmety p : hrac.getInventarList()) {
            if (p.getId().contains("item_beat"))
                beats.add(p);
            if (p.getId().contains("item_text"))
                texts.add(p);
        }

        if (beats.isEmpty() || texts.isEmpty()) {
            return "K vytvoření skladby potřebuješ aspoň jeden Beat a jeden Text v inventáři!";
        }

        Predmety selectedBeat = null;
        Predmety selectedText = null;

        if (args.length == 0 || args[0].trim().isEmpty()) {
            if (beats.size() == 1 && texts.size() == 1) {
                selectedBeat = beats.get(0);
                selectedText = texts.get(0);
            } else {
                StringBuilder sb = new StringBuilder(
                        "Máš více komponentů. Vyber si je pomocí: 'vytvorit_skladbu [Číslo Beatu] [Číslo Textu]'\n\nBeaty:\n");
                for (int i = 0; i < beats.size(); i++) {
                    Predmety b = beats.get(i);
                    sb.append(i + 1).append(") ").append(b.getNazev()).append(" [").append(b.getRarita()).append("]\n");
                }
                sb.append("\nTexty:\n");
                for (int i = 0; i < texts.size(); i++) {
                    Predmety t = texts.get(i);
                    sb.append(i + 1).append(") ").append(t.getNazev()).append(" [").append(t.getRarita()).append("]\n");
                }
                return sb.toString();
            }
        } else {
            String input = String.join(" ", args).trim();
            String beatPart, textPart;

            if (input.contains("|")) {
                String[] parts = input.split("\\|");
                if (parts.length < 2) { // Zpracování případů jako "beat |" nebo "| text"
                    return "Použij formát: 'vytvorit_skladbu [Jméno Beatu] | [Jméno Textu]'";
                }
                beatPart = parts[0].trim();
                textPart = parts[1].trim();
            } else {
                // Zkusit rozdělit mezerou, pokud jsou zadána dvě čísla
                String[] parts = input.split("\\s+");
                if (parts.length >= 2) {
                    beatPart = parts[0];
                    textPart = parts[1];
                } else {
                    return "Použij formát: 'vytvorit_skladbu [Číslo Beatu] [Číslo Textu]' nebo '[Jméno Beatu] | [Jméno Textu]'";
                }
            }
            try {
                int bIdx = Integer.parseInt(beatPart) - 1;
                int tIdx = Integer.parseInt(textPart) - 1;

                if (bIdx >= 0 && bIdx < beats.size())
                    selectedBeat = beats.get(bIdx);
                if (tIdx >= 0 && tIdx < texts.size())
                    selectedText = texts.get(tIdx);
            } catch (NumberFormatException e) {
                // Pokud to nejsou čísla, zkusit hledat podle názvu
                for (Predmety b : beats) {
                    if (b.getNazev().equalsIgnoreCase(beatPart)) {
                        selectedBeat = b;
                        break;
                    }
                }
                for (Predmety t : texts) {
                    if (t.getNazev().equalsIgnoreCase(textPart)) {
                        selectedText = t;
                        break;
                    }
                }
            }

            if (selectedBeat == null || selectedText == null) {
                return "Chyba: Zadaný beat nebo text (číslo nebo název) nebyl nalezen. Zadej správná čísla ze seznamu.";
            }
        }

        // Výpočet rarity
        int beatScore = getRarityScore(selectedBeat.getRarita());
        int textScore = getRarityScore(selectedText.getRarita());
        int locBonus = locId.equals("loc_studio") ? 1 : 0; // Studio dává +1 k hodnocení

        double avg = (beatScore + textScore + locBonus) / 2.0;
        // Logika: 1=Common, 2=Rare, 3=Epic, 4=Legendary, 5=Mythic
        String resultId = "item_skladba"; // Výchozí Common

        if (avg >= 4.5)
            resultId = "item_skladba_mythic";
        else if (avg >= 3.5)
            resultId = "item_skladba_legendary";
        else if (avg >= 2.5)
            resultId = "item_skladba_epic";
        else if (avg >= 1.5)
            resultId = "item_skladba_rare";
        else
            resultId = "item_skladba";

        // Spotřebovat předměty
        hrac.odeberPredmet(selectedBeat);
        hrac.odeberPredmet(selectedText);

        // Přidat skladbu
        Predmety songTemplate = hrac.getGameData().findItem(resultId);
        if (songTemplate != null) {
            String randomName = getRandomSongName();
            Predmety uniqueSong = new Predmety(songTemplate, randomName);
            hrac.pridejPredmet(uniqueSong);
            return "Vytvořil jsi skladbu '" + uniqueSong.getNazev() + "' (" + uniqueSong.getRarita() + ")!\n" +
                    "Použil jsi:\n- Beat: " + selectedBeat.getNazev() + " [" + selectedBeat.getRarita() + "]\n" +
                    "- Text: " + selectedText.getNazev() + " [" + selectedText.getRarita() + "]";
        }

        return "Chyba při tvorbě skladby.";
    }

    private String getRandomSongName() {
        String[] adjectives = { "Temný", "Zlatý", "Rychlý", "Smutný", "Drsný", "Uliční", "Noční", "Věčný", "Mladý",
                "Starý" };
        String[] nouns = { "Příběh", "Sen", "Život", "Svět", "Rým", "Beat", "Vibe", "Klub", "Dolar", "Gang" };
        int adjIdx = (int) (Math.random() * adjectives.length);
        int nounIdx = (int) (Math.random() * nouns.length);
        return adjectives[adjIdx] + " " + nouns[nounIdx] + " (" + (int) (Math.random() * 100) + ")";
    }

    private int getRarityScore(String rarity) {
        if (rarity == null)
            return 1;
        switch (rarity) {
            case "MYTHIC":
                return 5;
            case "LEGENDARY":
                return 4;
            case "EPIC":
                return 3;
            case "RARE":
                return 2;
            case "COMMON":
                return 1;
            default:
                return 1;
        }
    }

    @Override
    public boolean exit() {
        return false;
    }
}
