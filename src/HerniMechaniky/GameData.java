package HerniMechaniky;

import Lokace.Lokace;
import com.google.gson.Gson;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import Predmety.Predmety;
import Postavy.Postavy;

public class GameData {

    public ArrayList<Lokace> locations;
    public ArrayList<Predmety> items;
    public ArrayList<Postavy> characters;

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

    public Lokace findLocation(String id) {
        if (locations == null)
            return null;
        for (Lokace l : locations) {
            if (l.getId().equals(id)) {
                return l;
            }
        }
        return null;
    }

    public Predmety findItem(String nazev) {
        if (items == null)
            return null;
        for (Predmety p : items) {
            if (p.getNazev().equals(nazev)) {
                return p;
            }
        }
        return null;
    }

    public Postavy findCharacter(String jmeno) {
        if (characters == null)
            return null;
        for (Postavy p : characters) {
            if (p.getJmeno().equals(jmeno)) {
                return p;
            }
        }
        return null;
    }
}
