package Commands;

import Predmety.Predmety;
import java.util.Random;

public class NapsatText implements Command {
    private Hrac hrac;
    private Random random;

    public NapsatText(Hrac hrac) {
        this.hrac = hrac;
        this.random = new Random();
    }

    @Override
    public String execute(String[] args) {
        String locId = hrac.getAktualniLokace().getId();
        if (locId == null)
            return "Nevíš kde jsi.";

        // Lokace: loc_byt (Byt), loc_studio (Studio)
        // Zadání: Byt -> Common-Rare. Studio -> Rare-Mythic.

        String rarity = "COMMON";
        String itemId = "item_text_skladby";

        if (locId.equals("loc_byt")) {
            // Logika bytu: Common (70%), Rare (30%)
            int roll = random.nextInt(100);
            if (roll < 70) {
                rarity = "COMMON";
                itemId = "item_text_skladby";
            } else {
                rarity = "RARE";
                itemId = "item_text_rare";
            }
        } else if (locId.equals("loc_studio")) {
            // Logika Studia: Rare (35%), Epic (35%), Legendary (20%), Mythic (10%)
            int roll = random.nextInt(100);
            if (roll < 35) {
                rarity = "RARE";
                itemId = "item_text_rare";
            } else if (roll < 70) {
                rarity = "EPIC";
                itemId = "item_text_epic";
            } else if (roll < 90) {
                rarity = "LEGENDARY";
                itemId = "item_text_legendary";
            } else {
                rarity = "MYTHIC";
                itemId = "item_text_mythic";
            }
        } else {
            return "Texty můžeš psát jen doma (byt) nebo ve studiu.";
        }

        Predmety template = hrac.getGameData().findItem(itemId);
        if (template != null) {
            String randomName = getRandomTextName();
            Predmety uniqueText = new Predmety(template, randomName);
            hrac.pridejPredmet(uniqueText);
            return "Napsal jsi text!\nJméno: " + uniqueText.getNazev() + " (" + rarity + ")";
        }

        return "Chyba: Nepodařilo se najít předmět " + itemId;
    }

    private String getRandomTextName() {
        String[] adjectives = { "Hluboký", "Temný", "Pouliční", "Rychlý", "Zlatý", "Syrový", "Pravdivý", "Ostrý",
                "Jemný", "Drsný" };
        String[] nouns = { "Rým", "Příběh", "Text", "Vzkaz", "Flow", "Básně", "Slova", "Myšlenky", "Odkaz", "Styl" };
        int adjIdx = random.nextInt(adjectives.length);
        int nounIdx = random.nextInt(nouns.length);
        return adjectives[adjIdx] + " " + nouns[nounIdx] + " (" + (random.nextInt(100)) + ")";
    }

    @Override
    public boolean exit() {
        return false;
    }
}
