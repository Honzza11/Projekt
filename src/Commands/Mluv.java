package Commands;

public class Mluv implements Command {
    private Hrac hrac;

    public Mluv(Hrac hrac) {
        this.hrac = hrac;
    }

    @Override
    public String execute(String[] args) {
        // TODO provedeni prikazu mluv
        return null;
    }

    @Override
    public boolean exit() {
        return false;
    }
}
