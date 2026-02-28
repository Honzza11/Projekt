package Predmety;

import com.google.gson.annotations.SerializedName;

/**
 * Třída reprezentující předmět ve hře.
 * 
 * @author Honza
 */
public class Predmety {
    @SerializedName("id")
    protected String id;

    @SerializedName("name")
    protected String nazev;

    @SerializedName("type")
    protected String type;

    @SerializedName("description")
    protected String popis;

    @SerializedName("cena")
    protected String cena;

    @SerializedName("rarity")
    protected String rarita;

    @SerializedName("bonus")
    protected String bonus;

    /**
     * Konstruktor pro vytvoření nového předmětu se všemi atributy.
     * 
     * @param id     unikátní identifikátor předmětu
     * @param nazev  název předmětu
     * @param type   typ předmětu (např. mikrofon, beat, text)
     * @param popis  popis předmětu
     * @param cena   cena předmětu (ve formátu String z JSONu)
     * @param rarita rarita předmětu
     * @param bonus  bonus, který předmět poskytuje
     */
    public Predmety(String id, String nazev, String type, String popis, String cena, String rarita, String bonus) {
        this.id = id;
        this.nazev = nazev;
        this.type = type;
        this.popis = popis;
        this.cena = cena;
        this.rarita = rarita;
        this.bonus = bonus;
    }

    /**
     * Konstruktor pro vytvoření kopie předmětu s novým názvem (používá se pro
     * unikátní skladby).
     * 
     * @param other   původní předmět (šablona)
     * @param newName nový název
     */
    public Predmety(Predmety other, String newName) {
        this.id = other.id;
        this.nazev = newName;
        this.type = other.type;
        this.popis = other.popis;
        this.cena = other.cena;
        this.rarita = other.rarita;
        this.bonus = other.bonus;
    }

    /**
     * Vrátí ID předmětu.
     * 
     * @return ID
     */
    public String getId() {
        return id;
    }

    /**
     * Vrátí název předmětu.
     * 
     * @return název
     */
    public String getNazev() {
        return nazev;
    }

    /**
     * Vrátí typ předmětu.
     * 
     * @return typ
     */
    public String getType() {
        return type;
    }

    /**
     * Vrátí popis předmětu.
     * 
     * @return popis
     */
    public String getPopis() {
        return popis;
    }

    /**
     * Vrátí cenu předmětu.
     * 
     * @return cena
     */
    public String getCena() {
        return cena;
    }

    /**
     * Vrátí raritu předmětu.
     * 
     * @return rarita
     */
    public String getRarita() {
        return rarita;
    }

    /**
     * Vrátí bonus předmětu.
     * 
     * @return bonus
     */
    public String getBonus() {
        return bonus;
    }

    /**
     * Vrátí textovou reprezentaci předmětu (jeho název).
     * 
     * @return název předmětu
     */
    @Override
    public String toString() {
        return nazev;
    }
}
