package Ukoly;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Třída reprezentující úkol (quest) ve hře.
 * 
 * @author Honza
 */
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

    /**
     * Konstruktor pro vytvoření nového úkolu.
     * 
     * @param id                              unikátní identifikátor úkolu
     * @param nazev                           název úkolu
     * @param popis                           podrobný popis úkolu
     * @param giverCharacterId                ID postavy, která úkol dává
     * @param requiredItems                   seznam ID předmětů potřebných ke
     *                                        splnění
     * @param requiredPublicationsRadio       počet potřebných publikací v rádiu
     * @param requiredFreestyleLevelsDefeated počet potřebných vítězství ve
     *                                        freestyle levelu
     * @param odmena                          odměna (reputace) za splnění
     */
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

    /**
     * Vrátí ID úkolu.
     * 
     * @return ID
     */
    public String getId() {
        return id;
    }

    /**
     * Vrátí název úkolu.
     * 
     * @return název
     */
    public String getNazev() {
        return nazev;
    }

    /**
     * Vrátí popis úkolu.
     * 
     * @return popis
     */
    public String getPopis() {
        return popis;
    }

    /**
     * Vrátí ID postavy, která úkol zadala.
     * 
     * @return ID zadavatele
     */
    public String getGiverCharacterId() {
        return giverCharacterId;
    }

    /**
     * Vrátí seznam požadovaných předmětů.
     * 
     * @return seznam ID předmětů
     */
    public List<String> getRequiredItems() {
        return requiredItems;
    }

    /**
     * Vrátí počet požadovaných publikací v rádiu.
     * 
     * @return počet publikací
     */
    public int getRequiredPublicationsRadio() {
        return requiredPublicationsRadio;
    }

    /**
     * Vrátí počet požadovaných vítězství ve freestylu.
     * 
     * @return počet levelů
     */
    public int getRequiredFreestyleLevelsDefeated() {
        return requiredFreestyleLevelsDefeated;
    }

    /**
     * Vrátí odměnu za splnění úkolu.
     * 
     * @return výše odměny
     */
    public int getOdmena() {
        return odmena;
    }

    /**
     * Zkontroluje, zda je úkol splněn.
     * 
     * @return true, pokud je splněno
     */
    public boolean isSplneno() {
        return splneno;
    }

    /**
     * Nastaví stav splnění úkolu.
     * 
     * @param splneno nový stav
     */
    public void setSplneno(boolean splneno) {
        this.splneno = splneno;
    }

    /**
     * Vrátí textovou reprezentaci úkolu (název a stav).
     * 
     * @return název a stav
     */
    @Override
    public String toString() {
        return nazev + (splneno ? " [SPLNENO]" : " [AKTIVNI]");
    }
}
