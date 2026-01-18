package Ukoly;

public class Ukoly {
    private String nazev;
    private String popis;
    private int odmena;
    private boolean splneno;

    public Ukoly(String nazev, String popis, int odmena) {
        this.nazev = nazev;
        this.popis = popis;
        this.odmena = odmena;
        this.splneno = false;
    }

    public String getNazev() {
        return nazev;
    }

    public String getPopis() {
        return popis;
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
        return nazev + (splneno ? " [SPLNENO]" : "");
    }
}
