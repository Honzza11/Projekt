package Lokace;

import Postavy.Postavy;
import Predmety.Predmety;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Lokace {

    private String id;

    @SerializedName("name")
    private String nazev;

    @SerializedName("description")
    private String description;

    @SerializedName("neighbors")
    private ArrayList<String> neighbors;

    private transient Map<String, Lokace> vychody = new HashMap<>();
    protected List<Predmety> predmety = new ArrayList<>();
    protected List<Postavy> postavy = new ArrayList<>();

    public Lokace(String nazev, String popis) {
        this.nazev = nazev;
        this.description = popis;
    }

    public String getId() {
        return id;
    }

    public String getNazev() {
        return nazev;
    }

    public String getPopis() {
        return description;
    }

    public ArrayList<String> getNeighbors() {
        return neighbors;
    }

    @SerializedName("lootTable")
    private ArrayList<String> lootTable;

    @SerializedName("npcs")
    private ArrayList<String> npcs;

    public ArrayList<String> getLootTable() {
        return lootTable;
    }

    public ArrayList<String> getNpcs() {
        return npcs;
    }

    public void removePredmet(String id) {
        if (lootTable != null) {
            lootTable.remove(id);
        }
    }

    public void removeNpc(String id) {
        if (npcs != null) {
            npcs.remove(id);
        }
    }

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
        return "\nPredmety v okoli: " + String.join(", ", formatted);
    }

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

    public Lokace getVychod(String smer) {
        return vychody.get(smer);
    }
}
