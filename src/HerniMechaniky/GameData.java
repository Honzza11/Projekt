package HerniMechaniky;

import Lokace.Lokace;
import com.google.gson.Gson;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import Predmety.Predmety;
import Postavy.Postavy;
import Ukoly.Ukol;

/**
 * Třída pro správu a načítání herních dat (lokace, předměty, postavy, úkoly).
 * 
 * @author Honza
 */
public class GameData {

    public ArrayList<Lokace> locations;
    public ArrayList<Predmety> items;
    public ArrayList<Postavy> characters;
    public ArrayList<Ukol> quests;

    /**
     * Načte herní data z JSON souboru v resources.
     * 
     * @param resourcePath cesta k souboru
     * @return instance GameData s načtenými daty
     */
    public static GameData loadGameDataFromResources(String resourcePath) {
        Gson gson = new Gson();
        try (InputStream is = GameData.class.getResourceAsStream(resourcePath)) {
            if (is == null) {
                throw new IllegalStateException("Nenalezen resource: " + resourcePath);
            }
            return gson.fromJson(new InputStreamReader(is, StandardCharsets.UTF_8), GameData.class);
        } catch (Exception e) {
            throw new RuntimeException("Chyba při načítání JSON: " + e.getMessage());
        }
    }

    /**
     * Najde lokaci podle názvu nebo ID.
     * 
     * @param nazev název nebo ID lokace
     * @return nalezená lokace nebo null
     */
    public Lokace findLocation(String nazev) {
        if (locations == null)
            return null;
        for (Lokace l : locations) {
            if (l.getNazev().equalsIgnoreCase(nazev) || (l.getId() != null && l.getId().equalsIgnoreCase(nazev))) {
                return l;
            }
        }
        return null;
    }

    /**
     * Najde předmět podle názvu nebo ID.
     * 
     * @param nazev název nebo ID předmětu
     * @return nalezený předmět nebo null
     */
    public Predmety findItem(String nazev) {
        if (items == null)
            return null;
        for (Predmety p : items) {
            if (p.getNazev().equalsIgnoreCase(nazev) || (p.getId() != null && p.getId().equalsIgnoreCase(nazev))) {
                return p;
            }
        }
        return null;
    }

    /**
     * Najde postavu podle jména nebo ID.
     * 
     * @param jmeno jméno nebo ID postavy
     * @return nalezená postava nebo null
     */
    public Postavy findCharacter(String jmeno) {
        if (characters == null)
            return null;
        for (Postavy p : characters) {
            if (p.getJmeno().equalsIgnoreCase(jmeno) || (p.getId() != null && p.getId().equalsIgnoreCase(jmeno))) {
                return p;
            }
        }
        return null;
    }

    /**
     * Najde úkol podle ID.
     * 
     * @param id ID úkolu
     * @return nalezený úkol nebo null
     */
    public Ukol findQuest(String id) {
        if (quests == null)
            return null;
        for (Ukol q : quests) {
            if (q.getId().equalsIgnoreCase(id)) {
                return q;
            }
        }
        return null;
    }
}
