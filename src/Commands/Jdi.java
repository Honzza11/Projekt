package Commands;

/**
 * Příkaz pro přesun hráče mezi lokacemi.
 * 
 * @author Honza
 */
public class Jdi implements Command {
    private Hrac hrac;

    /**
     * Konstruktor pro příkaz Jdi.
     * 
     * @param hrac instance hráče
     */
    public Jdi(Hrac hrac) {
        this.hrac = hrac;
    }

    /**
     * Provede přesun hráče do cílové lokace.
     * 
     * @param args parametry příkazu (název nebo ID cílové lokace)
     * @return výsledek přesunu
     */
    @Override
    public String execute(String[] args) {
        if (args.length == 0) {
            return "Kam chces jit?";
        }
        return hrac.jdi(args[0]);
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
