package Postavy;

import com.google.gson.annotations.SerializedName;

/**
 * Třída reprezentující postavu (NPC) ve hře.
 * 
 * @author Honza
 */
public class Postavy {
    @SerializedName("id")
    private String id;

    @SerializedName("name")
    protected String jmeno;

    @SerializedName("notes")
    protected String popis;

    @SerializedName("role")
    protected String role;

    /**
     * Konstruktor pro vytvoření nové postavy.
     * 
     * @param jmeno jméno postavy
     * @param popis popis nebo poznámky k postavě
     */
    public Postavy(String jmeno, String popis) {
        this.jmeno = jmeno;
        this.popis = popis;
    }

    /**
     * Vrátí ID postavy.
     * 
     * @return ID postavy
     */
    public String getId() {
        return id;
    }

    /**
     * Vrátí jméno postavy.
     * 
     * @return jméno
     */
    public String getJmeno() {
        return jmeno;
    }

    /**
     * Vrátí popis postavy.
     * 
     * @return popis
     */
    public String getPopis() {
        return popis;
    }

    /**
     * Vrátí úvodní text, který postava řekne při začátku rozhovoru.
     * 
     * @return text rozhovoru
     */
    public String mluv() {
        return "Ahoj, jsem " + jmeno + ". Čím ti můžu pomoct?";
    }

    /**
     * Vrátí textovou reprezentaci postavy (její jméno).
     * 
     * @return jméno postavy
     */
    @Override
    public String toString() {
        return jmeno;
    }
}
