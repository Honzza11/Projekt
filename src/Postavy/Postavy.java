package Postavy;

import com.google.gson.annotations.SerializedName;

public class Postavy {
    @SerializedName("id")
    private String id;

    @SerializedName("name")
    protected String jmeno;

    @SerializedName("notes")
    protected String popis;

    @SerializedName("role")
    protected String role;

    public Postavy(String jmeno, String popis) {
        this.jmeno = jmeno;
        this.popis = popis;
    }

    public String getId() {
        return id;
    }

    public String getJmeno() {
        return jmeno;
    }

    public String getPopis() {
        return popis;
    }

    public String mluv() {
        return "Ahoj, jsem " + jmeno + ". Čím ti můžu pomoct?";
    }

    @Override
    public String toString() {
        return jmeno;
    }
}
