package Commands;

public class Pouzij implements Command {
    private Hrac hrac;

    public Pouzij(Hrac hrac) {
        this.hrac = hrac;
    }

    @Override
    public String execute(String[] args) {
        // TODO provedeni prikazu pouzij
        return null;
    } 

    @Override
    public boolean exit() {
        return false;
    }
}
