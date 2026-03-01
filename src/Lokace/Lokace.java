package Lokace;

import Postavy.Postavy;
import Predmety.Predmety;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Třída představující lokaci v herním světě.
 * Lokace může obsahovat předměty, postavy a východy do dalších lokací.
 */
public class Lokace {

    /** Unikátní identifikátor lokace. */
    private String id;

    /** Název lokace zobrazený hráči. */
    @SerializedName("name")
    private String nazev;

    /** Popis lokace zobrazený hráči. */
    @SerializedName("description")
    private String description;

    /** Seznam ID sousedních lokací. */
    @SerializedName("neighbors")
    private ArrayList<String> neighbors;

    /** Mapa reálných instancí lokací pro snadný přístup k východům. */
    private transient Map<String, Lokace> vychody = new HashMap<>();

    /** Seznam instancí předmětů v lokaci. */
    protected List<Predmety> predmety = new ArrayList<>();

    /** Seznam instancí postav v lokaci. */
    protected List<Postavy> postavy = new ArrayList<>();

    /**
     * Konstruktor pro vytvoření nové lokace.
     * 
     * @param nazev Název lokace.
     * @param popis Popis lokace.
     */
    public Lokace(String nazev, String popis) {
        this.nazev = nazev;
        this.description = popis;
    }

    /**
     * Vrátí ID lokace.
     * 
     * @return ID lokace.
     */
    public String getId() {
        return id;
    }

    /**
     * Vrátí název lokace.
     * 
     * @return Název lokace.
     */
    public String getNazev() {
        return nazev;
    }

    /**
     * Vrátí popis lokace.
     * 
     * @return Popis lokace.
     */
    public String getPopis() {
        return description;
    }

    /**
     * Vrátí seznam ID sousedních lokací.
     * 
     * @return Seznam ID sousedů.
     */
    public ArrayList<String> getNeighbors() {
        return neighbors;
    }

    /** Seznam ID předmětů, které se v lokaci nacházejí (loot table). */
    @SerializedName("lootTable")
    private ArrayList<String> lootTable;

    /** Seznam ID postav, které se v lokaci nacházejí. */
    @SerializedName("npcs")
    private ArrayList<String> npcs;

    /**
     * Vrátí seznam ID předmětů v lokaci.
     * 
     * @return Seznam ID předmětů.
     */
    public ArrayList<String> getLootTable() {
        return lootTable;
    }

    /**
     * Vrátí seznam ID postav v lokaci.
     * 
     * @return Seznam ID postav.
     */
    public ArrayList<String> getNpcs() {
        return npcs;
    }

    /**
     * Odstraní předmět z lokace podle jeho ID.
     * 
     * @param id ID předmětu k odstranění.
     */
    public void removePredmet(String id) {
        if (lootTable != null) {
            lootTable.remove(id);
        }
    }

    /**
     * Odstraní postavu z lokace podle jejího ID.
     * 
     * @param id ID postavy k odstranění.
     */
    public void removeNpc(String id) {
        if (npcs != null) {
            npcs.remove(id);
        }
    }

    /**
     * Vrátí formátovaný řetězec se seznamem východů z lokace.
     * 
     * @return Seznam východů pro hráče.
     */
    public String getSeznamVychodu() {
        if (neighbors == null || neighbors.isEmpty()) {
            return "Žádné východy.";
        }

        List<String> formatted = new ArrayList<>();
        for (String n : neighbors) {
            if (n.startsWith("loc_")) {
                formatted.add(n.substring(4));
            } else {
                formatted.add(n);
            }
        }
        return "Východy (kam můžeš jít): " + String.join(", ", formatted);
    }

    /**
     * Vrátí formátovaný řetězec se seznamem předmětů v lokaci.
     * 
     * @return Seznam předmětů pro hráče.
     */
    public String getSeznamPredmetu() {
        if (lootTable == null || lootTable.isEmpty())
            return "";

        List<String> formatted = new ArrayList<>();
        for (String item : lootTable) {
            if (item.startsWith("item_")) {
                formatted.add(item.substring(5));
            } else {
                formatted.add(item);
            }
        }
        return "\nPředměty v okolí: " + String.join(", ", formatted);
    }

    /**
     * Vrátí formátovaný řetězec se seznamem postav v lokaci.
     * 
     * @return Seznam postav pro hráče.
     */
    public String getSeznamPostav() {
        if (npcs == null || npcs.isEmpty()) {
            return "";
        }
        List<String> formatted = new ArrayList<>();
        for (String npcId : npcs) {
            if (npcId.startsWith("char_")) {
                formatted.add(npcId.substring(5));
            } else {
                formatted.add(npcId);
            }
        }
        return "\nPostavy: " + String.join(", ", formatted);
    }

    /**
     * Vrátí instanci sousední lokace pro daný směr (název lokace).
     * 
     * @param smer Název cílové lokace.
     * @return Objekt Lokace, nebo null pokud neexistuje.
     */
    public Lokace getVychod(String smer) {
        return vychody.get(smer);
    }
}
