package Predmety;

public class Mikrofon extends Predmety {
    private double bonus;

    public Mikrofon(String id, String nazev, String type, String popis, int cena, String rarita, double bonus) {
        super(id, nazev, type, popis, cena, rarita, bonus);
    }

    public double getBonus() {
        return bonus;
    }
}
