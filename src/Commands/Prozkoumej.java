package Commands;

import Lokace.Lokace;
import Postavy.Postavy;
import HerniMechaniky.GameData;
import java.util.ArrayList;
import java.util.List;

public class Prozkoumej implements Command {
    private Hrac hrac;

    public Prozkoumej(Hrac hrac) {
        this.hrac = hrac;
    }

    @Override
    public String execute(String[] args) {
        Lokace loc = hrac.getAktualniLokace();
        GameData gd = hrac.getGameData();

        if (args.length > 0 && !args[0].trim().isEmpty()) {
            String target = args[0].trim().toLowerCase();
            if (loc.getNpcs() != null) {
                for (String npcId : loc.getNpcs()) {
                    Postavy p = gd.findCharacter(npcId);
                    if (p != null) {
                        if (p.getJmeno().toLowerCase().contains(target) || npcId.toLowerCase().contains(target)) {
                            return p.getJmeno() + ": " + p.getPopis();
                        }
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(loc.getPopis()).append("\n");
        sb.append(loc.getSeznamVychodu()).append("\n");
        sb.append(loc.getSeznamPredmetu());

        if (loc.getNpcs() != null && !loc.getNpcs().isEmpty()) {
            sb.append("\nPostavy: ");
            List<String> names = new ArrayList<>();
            for (String npcId : loc.getNpcs()) {
                Postavy p = gd.findCharacter(npcId);
                if (p != null) {
                    names.add(p.getJmeno());
                } else {
                    names.add(npcId);
                }
            }
            sb.append(String.join(", ", names));
        }

        return sb.toString();
    }

    @Override
    public boolean exit() {
        return false;
    }
}
