package Commands;

public class Vezmi implements Command {
    private Hrac hrac;

    public Vezmi(Hrac hrac) {
        this.hrac = hrac;
    }

    @Override
    public String execute(String[] args) {
        // TODO provedeni prikazu vezmi
        return null;
    }

    @Override
    public boolean exit() {
        return false;
    }
}
