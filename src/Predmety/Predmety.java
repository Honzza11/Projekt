package Predmety;

import com.google.gson.annotations.SerializedName;

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

    public Predmety(String id, String nazev, String type, String popis, String cena, String rarita, String bonus) {
        this.id = id;
        this.nazev = nazev;
        this.type = type;
        this.popis = popis;
        this.cena = cena;
        this.rarita = rarita;
        this.bonus = bonus;
    }

    // Konstruktor pro kopírování s přepsáním názvu
    public Predmety(Predmety other, String newName) {
        this.id = other.id;
        this.nazev = newName;
        this.type = other.type;
        this.popis = other.popis;
        this.cena = other.cena;
        this.rarita = other.rarita;
        this.bonus = other.bonus;
    }

    public String getId() {
        return id;
    }

    public String getNazev() {
        return nazev;
    }

    public String getType() {
        return type;
    }

    public String getPopis() {
        return popis;
    }

    public String getCena() {
        return cena;
    }

    public String getRarita() {
        return rarita;
    }

    public String getBonus() {
        return bonus;
    }

    @Override
    public String toString() {
        return nazev;
    }
}
