package Commands;

public class Inventar implements Command {
    private Hrac hrac;

    public Inventar(Hrac hrac) {
        this.hrac = hrac;
    }

    @Override
    public String execute(String[] args) {
        // TODO provedeni prikazu inventar
        return null;
    }

    @Override
    public boolean exit() {
        return false;
    }
}
