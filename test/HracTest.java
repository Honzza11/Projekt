import Commands.Hrac;
import Lokace.Lokace;
import Predmety.Predmety;
import Ukoly.Ukol;
import HerniMechaniky.GameData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testovací třída pro ověření funkčnosti třídy Hrac.
 * 
 * @author Honza
 */
class HracTest {
    private Hrac hrac;
    private Lokace startovna;
    private GameData gd;

    @BeforeEach
    void setUp() {
        gd = new GameData();
        gd.locations = new ArrayList<>();
        gd.items = new ArrayList<>();
        gd.quests = new ArrayList<>();

        startovna = new Lokace("Start", "Popis startu");
        // Poznámka: V reálné hře se ID nastavuje přes GSON z JSONu.
        // Tady pro testy budeme spoléhat na názvy, pokud ID zůstane null,
        // nebo ho v Hrac.java ošetříme.

        gd.locations.add(startovna);
        hrac = new Hrac("Tester", startovna, gd);
    }

    /**
     * Testuje změnu jména hráče.
     */
    @Test
    void testJmeno() {
        hrac.setJmeno("Pepa");
        assertEquals("Pepa", hrac.getJmeno());
    }

    /**
     * Testuje správu peněz a reputace.
     */
    @Test
    void testFinanceAReputace() {
        hrac.setPenize(500);
        assertEquals(500, hrac.getPenize());
        hrac.setReputace(100);
        assertEquals(100, hrac.getReputace());
    }

    /**
     * Testuje přidávání předmětů do inventáře.
     */
    @Test
    void testInventar() {
        Predmety p = new Predmety("item_test", "Test", "ITEM", "Popis", "10", "COMMON", "0");
        hrac.pridejPredmet(p);
        assertTrue(hrac.getInventarList().contains(p));

        hrac.odeberPredmet(p);
        assertFalse(hrac.getInventarList().contains(p));
    }

    /**
     * Testuje přijetí úkolu a prevenci duplicit.
     */
    @Test
    void testUkoly() {
        Ukol u = new Ukol("q_test", "Ukol", "Popis", "npc", java.util.Collections.emptyList(), 0, 0, 50);
        hrac.prijmiUkol(u);
        assertEquals(1, hrac.getMojeUkoly().size());

        // Druhé přijetí stejného úkolu
        hrac.prijmiUkol(u);
        assertEquals(1, hrac.getMojeUkoly().size());
    }

    /**
     * Testuje kontrolu klíče od studia.
     */
    @Test
    void testMaKlicOdStudia() {
        assertFalse(hrac.maKlicOdStudia());
        Predmety klic = new Predmety("item_klic", "Klic", "ITEM", "Popis", "0", "COMMON", "0");
        hrac.pridejPredmet(klic);
        assertTrue(hrac.maKlicOdStudia());
    }

    /**
     * Testuje sledování pokroku (publikace v rádiu a vyhrané freestyle levely).
     */
    @Test
    void testSledovaniPokroku() {
        assertEquals(0, hrac.getPocetPublikaciRadio());
        hrac.zvyseniPublikaceRadio();
        assertEquals(1, hrac.getPocetPublikaciRadio());

        assertTrue(hrac.getPorazeneFreestyleLevely().isEmpty());
        hrac.upravPorazeneLevely(1);
        hrac.upravPorazeneLevely(2);
        assertEquals(2, hrac.getPorazeneFreestyleLevely().size());
        assertTrue(hrac.getPorazeneFreestyleLevely().contains(1));
    }
}