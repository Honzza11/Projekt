package Predmety;

public class Beaty extends Predmety {
    private String rarita;

    public Beaty(String nazev, String popis, int cena, String rarita) {
        super(nazev, popis, cena);
        this.rarita = rarita;
    }

    public String getRarita() {
        return rarita;
    }
}
