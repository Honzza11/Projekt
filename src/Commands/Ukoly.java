package Commands;

import Ukoly.Ukol;
import java.util.List;

/**
 * Příkaz pro zobrazení seznamu aktuálních úkolů hráče.
 * 
 * @author Honza
 */
public class Ukoly implements Command {
    private Hrac hrac;

    /**
     * Konstruktor pro příkaz Ukoly.
     * 
     * @param hrac instance hráče
     */
    public Ukoly(Hrac hrac) {
        this.hrac = hrac;
    }

    /**
     * Provede vypsání seznamu úkolů.
     * 
     * @param args parametry příkazu (nejsou využity)
     * @return textový seznam úkolů a jejich popisů
     */
    @Override
    public String execute(String[] args) {
        List<Ukol> seznam = hrac.getMojeUkoly();
        if (seznam.isEmpty()) {
            return "Zatím nemáš žádné úkoly.";
        }

        StringBuilder sb = new StringBuilder("Tvé úkoly:\n");
        for (Ukol u : seznam) {
            sb.append("- ").append(u.toString()).append("\n");
            sb.append("  Popis: ").append(u.getPopis()).append("\n");
        }
        return sb.toString();
    }

    /**
     * Určuje, zda příkaz ukončuje hru.
     * 
     * @return false (neukončuje)
     */
    @Override
    public boolean exit() {
        return false;
    }
}
