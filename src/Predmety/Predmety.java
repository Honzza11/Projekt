package Predmety;

public abstract class Predmety {
    protected String nazev;
    protected String popis;
    protected int cena;

    public Predmety(String nazev, String popis, int cena) {
        this.nazev = nazev;
        this.popis = popis;
        this.cena = cena;
    }

    public String getNazev() {
        return nazev;
    }

    public String getPopis() {
        return popis;
    }

    public int getCena() {
        return cena;
    }

    @Override
    public String toString() {
        return nazev;
    }
}
