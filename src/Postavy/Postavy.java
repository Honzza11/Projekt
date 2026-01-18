package Postavy;

public abstract class Postavy {
    protected String jmeno;
    protected String popis;

    public Postavy(String jmeno, String popis) {
        this.jmeno = jmeno;
        this.popis = popis;
    }

    public String getJmeno() {
        return jmeno;
    }

    public String getPopis() {
        return popis;
    }

    public abstract String mluv();

    @Override
    public String toString() {
        return jmeno;
    }
}
