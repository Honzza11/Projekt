package Commands;

/**
 * Příkaz pro zobrazení obsahu hráčova inventáře.
 * 
 * @author Honza
 */
public class Inventar implements Command {
    private Hrac hrac;

    /**
     * Konstruktor pro příkaz Inventar.
     * 
     * @param hrac instance hráče
     */
    public Inventar(Hrac hrac) {
        this.hrac = hrac;
    }

    /**
     * Provede výpis inventáře.
     * 
     * @param args parametry příkazu (nejsou využity)
     * @return textový výpis inventáře
     */
    @Override
    public String execute(String[] args) {
        return hrac.inventar();
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
