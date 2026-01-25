package Commands;

public class Jdi implements Command {
    private Hrac hrac;

    public Jdi(Hrac hrac) {
        this.hrac = hrac;
    }

    @Override
    public String execute(String[] args) {
        if (args.length == 0) {
            return "Kam chces jit?";
        }
        return hrac.jdi(args[0]);
    }

    @Override
    public boolean exit() {
        return false;
    }
}
