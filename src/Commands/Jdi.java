package Commands;

public class Jdi implements Command {
    private Hrac hrac;

    public Jdi(Hrac hrac) {
        this.hrac = hrac;
    }

    @Override
    public String execute(String[] args) {
        // TODO implementace pohybu hrace
        return null;
    }

    @Override
    public boolean exit() {
        return false;
    }
}
