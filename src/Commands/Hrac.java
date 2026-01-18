package Commands;

import Lokace.*;
import Predmety.*;
import Ukoly.*;
import Postavy.*;
import java.util.*;

public class Hrac {
    private String jmeno;
    private double penize;
    private int reputace;
    private List<Predmety> inventar;
    private List<Ukoly> seznamUkolu;
    private Lokace aktualniLokace;

    public Hrac(String jmeno, Lokace startovniLokace) {
        this.jmeno = jmeno;
        this.aktualniLokace = startovniLokace;
        this.penize = 0;
        this.reputace = 0;
        this.inventar = new ArrayList<>();
        this.seznamUkolu = new ArrayList<>();
    }

    public Lokace getAktualniLokace() {
        return aktualniLokace;
    }

    public String jdi(String smer) {
        // TODO implementace pohybu hrace
        return null;
    }

    public String prozkoumej() {
        // TODO implementace pruzkumu lokace
        return null;
    }

    public String vezmi(String nazevPredmetu) {
        // TODO implementace sebrani predmetu
        return null;
    }

    public String pouzij(String nazevPredmetu) {
        // TODO implementace pouziti predmetu
        return null;
    }

    public String mluv(String jmenoPostavy) {
        // TODO implementace rozhovoru
        return null;
    }

    public String inventory() {
        // TODO vypis inventare
        return null;
    }

    public String showUkoly() {
        // TODO vypis ukolu
        return null;
    }

    public String kup(String vec) {
        // TODO implementace nakupu
        return null;
    }

    public String nahravej() {
        // TODO implementace nahravani
        return null;
    }

    public String publikuj() {
        // TODO implementace publikovani
        return null;
    }

    public String freestyle() {
        // TODO implementace freestyle
        return null;
    }
}
