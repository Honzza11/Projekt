package Commands;

public class Inventar implements Command {
    private Hrac hrac;

    public Inventar(Hrac hrac) {
        this.hrac = hrac;
    }

    @Override
    public String execute(String[] args) {
        return hrac.inventar();
    }

    @Override
    public boolean exit() {
        return false;
    }
}
