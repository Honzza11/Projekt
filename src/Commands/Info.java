package Commands;

/**
 * Příkaz pro zobrazení informací o hráči (jméno, peníze, reputace).
 * 
 * @author Honza
 */
public class Info implements Command {
    private Hrac hrac;

    /**
     * Konstruktor pro příkaz Info.
     * 
     * @param hrac instance hráče
     */
    public Info(Hrac hrac) {
        this.hrac = hrac;
    }

    /**
     * Provede vypsání informací o hráči do konzole.
     * 
     * @param args parametry příkazu (nejsou využity)
     * @return textová reprezentace reputace (ostatní info se vypisuje přímo)
     */
    @Override
    public String execute(String[] args) {
        System.out.println("Tvé jméno: " + hrac.getJmeno());
        System.out.println("Tvé peníze: " + hrac.getPenize() + " $");
        return "Tvá reputace: " + hrac.getReputace();
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
