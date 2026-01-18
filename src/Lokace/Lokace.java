package Lokace;

import Postavy.Postavy;
import Predmety.Predmety;
import java.util.*;

public abstract class Lokace {
    protected String nazev;
    protected String popis;
    protected Map<String, Lokace> vychody;
    protected List<Predmety> predmety;
    protected List<Postavy> postavy;

    public Lokace(String nazev, String popis) {
        this.nazev = nazev;
        this.popis = popis;
        this.vychody = new HashMap<>();
        this.predmety = new ArrayList<>();
        this.postavy = new ArrayList<>();
    }

    public void pridatVychod(String smer, Lokace lokace) {
        // TODO pridat vychod
        vychody.put(smer, lokace);
    }

    public Lokace getVychod(String smer) {
        // TODO ziskat vychod
        return vychody.get(smer);
    }

    public String getNazev() {
        return nazev;
    }

    public String getPopis() {
        return popis;
    }

    public void pridatPredmet(Predmety predmet) {
        // TODO pridat predmet
        predmety.add(predmet);
    }

    public void odebratPredmet(Predmety predmet) {
        // TODO odebrat predmet
        predmety.remove(predmet);
    }

    public Predmety najdiPredmet(String nazev) {
        // TODO najit predmet podle nazvu
        return null;
    }

    public void pridatPostavu(Postavy postava) {
        // TODO pridat postavu
        postavy.add(postava);
    }

    public Postavy najdiPostavu(String jmeno) {
        // TODO najit postavu podle jmena
        return null;
    }

    public Collection<Lokace> getVychody() {
        return vychody.values();
    }

    public String getSeznamVychodu() {
        // TODO vratit seznam vychodu jako retezec
        return null;
    }
}
