package Commands;

public class Vezmi implements Command {
    private Hrac hrac;

    public Vezmi(Hrac hrac) {
        this.hrac = hrac;
    }

    @Override
    public String execute(String[] args) {
        if (args.length == 0) {
            return "Co mám vzít? Musíš zadat název předmětu.";
        }
        String nazevPredmetu = args[0];
        return hrac.vezmi(nazevPredmetu);
    }

    @Override
    public boolean exit() {
        return false;
    }
}
