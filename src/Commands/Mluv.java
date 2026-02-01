package Commands;

public class Mluv implements Command {
    private Hrac hrac;

    public Mluv(Hrac hrac) {
        this.hrac = hrac;
    }

    @Override
    public String execute(String[] args) {
        if (args.length == 0 || (args.length == 1 && args[0].isEmpty())) {
            return "S kým chceš mluvit?";
        }
        return hrac.mluv(args[0]);
    }

    @Override
    public boolean exit() {
        return false;
    }
}
