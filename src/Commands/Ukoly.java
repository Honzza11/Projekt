package Commands;

public class Ukoly implements Command {
    private Hrac hrac;

    public Ukoly(Hrac hrac) {
        this.hrac = hrac;
    }

    @Override
    public String execute(String[] args) {
        // TODO provedeni prikazu ukoly
        return null;
    }

    @Override
    public boolean exit() {
        return false;
    }
}
