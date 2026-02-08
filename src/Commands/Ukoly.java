package Commands;

import Ukoly.Ukol;
import java.util.List;

public class Ukoly implements Command {
    private Hrac hrac;

    public Ukoly(Hrac hrac) {
        this.hrac = hrac;
    }

    @Override
    public String execute(String[] args) {
        List<Ukol> seznam = hrac.getMojeUkoly();
        if (seznam.isEmpty()) {
            return "Zatím nemáš žádné úkoly.";
        }

        StringBuilder sb = new StringBuilder("Tvé úkoly:\n");
        for (Ukol u : seznam) {
            sb.append("- ").append(u.toString()).append("\n");
            sb.append("  Popis: ").append(u.getPopis()).append("\n");
        }
        return sb.toString();
    }

    @Override
    public boolean exit() {
        return false;
    }
}
