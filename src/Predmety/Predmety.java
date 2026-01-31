package Predmety;

public class Predmety {
    protected String id;
    protected String nazev;
    protected String type;
    protected String popis;
    protected int cena;
    protected String rarita;
    protected double bonus;

    public Predmety(String id, String nazev, String type, String popis, int cena, String rarita, double bonus) {
        this.id = id;
        this.nazev = nazev;
        this.type = type;
        this.popis = popis;
        this.cena = cena;
        this.rarita = rarita;
        this.bonus = bonus;
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

    public int getCena() {
        return cena;
    }

    public String getRarita() {
        return rarita;
    }

    public double getBonus() {
        return bonus;
    }

    @Override
    public String toString() {
        return nazev;
    }
}
