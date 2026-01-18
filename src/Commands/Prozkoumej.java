package Commands;

public class Prozkoumej implements Command {
    private Hrac hrac;

    public Prozkoumej(Hrac hrac) {
        this.hrac = hrac;
    }

    @Override
    public String execute(String[] args) {
        // TODO provedeni prikazu prozkoumej
        return null;
    }

    @Override
    public boolean exit() {
        return false;
    }
}
