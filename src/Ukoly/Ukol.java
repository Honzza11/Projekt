package Ukoly;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class Ukol {
    @SerializedName("id")
    private String id;

    @SerializedName("title")
    private String nazev;

    @SerializedName("description")
    private String popis;

    @SerializedName("giverCharacterId")
    private String giverCharacterId;

    @SerializedName("requiredItems")
    private List<String> requiredItems;

    @SerializedName("requiredPublicationsRadio")
    private int requiredPublicationsRadio;

    @SerializedName("requiredFreestyleLevelsDefeated")
    private int requiredFreestyleLevelsDefeated;

    private int odmena;
    private boolean splneno;

    public Ukol(String id, String nazev, String popis, String giverCharacterId, List<String> requiredItems,
            int requiredPublicationsRadio, int requiredFreestyleLevelsDefeated, int odmena) {
        this.id = id;
        this.nazev = nazev;
        this.popis = popis;
        this.giverCharacterId = giverCharacterId;
        this.requiredItems = requiredItems;
        this.requiredPublicationsRadio = requiredPublicationsRadio;
        this.requiredFreestyleLevelsDefeated = requiredFreestyleLevelsDefeated;
        this.odmena = odmena;
        this.splneno = false;
    }

    public String getId() {
        return id;
    }

    public String getNazev() {
        return nazev;
    }

    public String getPopis() {
        return popis;
    }

    public String getGiverCharacterId() {
        return giverCharacterId;
    }

    public List<String> getRequiredItems() {
        return requiredItems;
    }

    public int getRequiredPublicationsRadio() {
        return requiredPublicationsRadio;
    }

    public int getRequiredFreestyleLevelsDefeated() {
        return requiredFreestyleLevelsDefeated;
    }

    public int getOdmena() {
        return odmena;
    }

    public boolean isSplneno() {
        return splneno;
    }

    public void setSplneno(boolean splneno) {
        this.splneno = splneno;
    }

    @Override
    public String toString() {
        return nazev + (splneno ? " [SPLNENO]" : " [AKTIVNI]");
    }
}
