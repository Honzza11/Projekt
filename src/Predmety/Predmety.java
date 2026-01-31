package Predmety;

public abstract class Predmety {
    protected String id;
    protected String nazev;
    protected String type;
    protected String popis;
    protected int cena;
    protected String rarita;

    public Predmety(String id, String nazev, String type, String popis, int cena, String rarita) {
        this.id = id;
        this.nazev = nazev;
        this.type = type;
        this.popis = popis;
        this.cena = cena;
        this.rarita = rarita;
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

    @Override
    public String toString() {
        return nazev;
    }
}
