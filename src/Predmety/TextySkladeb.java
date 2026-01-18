package Predmety;

public class TextySkladeb extends Predmety {
    private String rarita;

    public TextySkladeb(String nazev, String popis, int cena, String rarita) {
        super(nazev, popis, cena);
        this.rarita = rarita;
    }

    public String getRarita() {
        return rarita;
    }
}
