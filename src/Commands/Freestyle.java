package Commands;

import Predmety.Predmety;
import java.util.Random;

public class Freestyle implements Command {
    private Hrac hrac;
    private Random random;

    public Freestyle(Hrac hrac) {
        this.hrac = hrac;
        this.random = new Random();
    }

    @Override
    public String execute(String[] args) {
        if (args.length == 0 || args[0].trim().isEmpty()) {
            return "Musíš si vybrat obtížnost! Napiš 'freestyle [1-5] [nepovinné: název skladby]'.\n1 = Lehký, 5 = MC Freestyle (Boss)";
        }

        String[] realArgs = args[0].trim().split("\\s+");
        String locId = hrac.getAktualniLokace().getId();
        if (locId == null || !locId.equals("loc_freestyle_spot")) {
            return "Freestyle battle můžeš dát jen ve Freestyle Spotu.";
        }

        int level;
        try {
            level = Integer.parseInt(realArgs[0]);
        } catch (NumberFormatException e) {
            return "Obtížnost musí být číslo 1-5.";
        }

        if (level < 1 || level > 5)
            return "Obtížnost musí být 1-5.";

        // Najít skladbu
        Predmety song = null;
        if (realArgs.length > 1) {
            // Uživatel specifikoval skladbu (vše od realArgs[1] dále)
            StringBuilder sb = new StringBuilder();
            for (int i = 1; i < realArgs.length; i++) {
                sb.append(realArgs[i]).append(" ");
            }
            String songName = sb.toString().trim();

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
            // Automatický výběr nebo výpis seznamu
            long songCount = hrac.getInventarList().stream().filter(p -> p.getId().contains("item_skladba")).count();
            if (songCount > 1) {
                StringBuilder sb = new StringBuilder("Máš více skladeb. Napiš 'freestyle " + level + " [nazev]':\n");
                for (Predmety p : hrac.getInventarList()) {
                    if (p.getId().contains("item_skladba")) {
                        String rarity = p.getRarita() != null ? p.getRarita() : "COMMON";
                        sb.append("- ").append(p.getNazev()).append(" [").append(rarity).append("]\n");
                    }
                }
                return sb.toString();
            } else if (songCount == 1) {
                for (Predmety p : hrac.getInventarList()) {
                    if (p.getId().contains("item_skladba")) {
                        song = p;
                        break;
                    }
                }
            } else {
                return "Bez vlastní skladby nemáš šanci! (Potřebuješ item_skladba)";
            }
        }

        int baseChance = 0;
        switch (level) {
            case 1:
                baseChance = 60;
                break;
            case 2:
                baseChance = 50;
                break;
            case 3:
                baseChance = 40;
                break;
            case 4:
                baseChance = 30;
                break;
            case 5:
                baseChance = 10;
                break; // MC Freestyle je těžký
        }

        int bonus = 0;
        if (song != null) {
            switch (song.getRarita()) {
                case "MYTHIC":
                    bonus = 100;
                    break;
                case "LEGENDARY":
                    bonus = 80;
                    break;
                case "EPIC":
                    bonus = 50;
                    break;
                case "RARE":
                    bonus = 30;
                    break;
                case "COMMON":
                    bonus = 10;
                    break;
            }
        }

        int totalChance = baseChance + bonus;
        if (totalChance > 100)
            totalChance = 100;

        int roll = random.nextInt(100);

        if (roll < totalChance) {
            // Výhra
            int rewardMoney = level * 500;
            int rewardRep = level * 200;
            hrac.setPenize(hrac.getPenize() + rewardMoney);
            hrac.setReputace(hrac.getReputace() + rewardRep);
            hrac.upravPorazeneLevely(level);
            hrac.odeberPredmet(song);

            String winMsg = "Použil jsi skladbu: " + song.getNazev() + " (" + song.getRarita() + ")\n" +
                    "Vyhrál jsi battle (Level " + level + ")!\nZískáváš " + rewardMoney + "$ a " + rewardRep
                    + " reputace.";
            if (level == 5) {
                winMsg += "\nPORAZIL JSI MC FREESTYLE! Stal ses legendou města.";
            }
            return winMsg;
        } else {
            hrac.odeberPredmet(song);
            return "Použil jsi skladbu: " + song.getNazev() + "\nProhrál jsi battle! Tvůj text nestačil.";
        }
    }

    @Override
    public boolean exit() {
        return false;
    }
}
