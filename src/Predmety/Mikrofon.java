package Predmety;

public class Mikrofon extends Predmety {
    private double bonus;

    public Mikrofon(String nazev, String popis, int cena, double bonus) {
        super(nazev, popis, cena);
        this.bonus = bonus;
    }

    public double getBonus() {
        return bonus;
    }
}
