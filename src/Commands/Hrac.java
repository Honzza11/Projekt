package Commands;

import Lokace.*;
import Predmety.Predmety;
import Ukoly.*;
import Postavy.*;
import HerniMechaniky.GameData;
import java.util.*;

public class Hrac {
    private String jmeno;
    private double penize;
    private int reputace;
    private List<Predmety> inventar;
    private List<Ukoly> seznamUkolu;
    private Lokace aktualniLokace;

    private GameData gameData;

    public Hrac(String jmeno, Lokace startovniLokace, GameData gameData) {
        this.jmeno = jmeno;
        this.aktualniLokace = startovniLokace;
        this.gameData = gameData;
        this.penize = 0;
        this.reputace = 0;
        this.inventar = new ArrayList<>();
        this.seznamUkolu = new ArrayList<>();
    }

    public Lokace getAktualniLokace() {
        return aktualniLokace;
    }

    public GameData getGameData() {
        return gameData;
    }

    public String jdi(String cilovaLokaceInput) {
        if (aktualniLokace.getNeighbors() != null) {
            String inputLower = cilovaLokaceInput.toLowerCase();
            for (String neighborId : aktualniLokace.getNeighbors()) {
                Lokace nova = gameData.findLocation(neighborId);
                if (nova == null)
                    continue;

                boolean matchLink = neighborId.toLowerCase().contains(inputLower);
                boolean matchName = nova.getNazev().toLowerCase().contains(inputLower);
                boolean matchId = nova.getId() != null && nova.getId().toLowerCase().contains(inputLower);

                if (matchLink || matchName || matchId) {
                    aktualniLokace = nova;
                    return "Jdes do " + nova.getNazev() + ".\n" + nova.getPopis() + "\n" + nova.getSeznamVychodu()
                            + nova.getSeznamPredmetu() + nova.getSeznamPostav();
                }
            }
        }
        return "Tam se odsud jit neda. (Zadavej ID nebo cast ID, napr 'ulice')";
    }

    public String vezmi(String nazevPredmetu) {
        if (aktualniLokace.getLootTable() == null) {
            return "Tady nic není.";
        }

        for (String itemId : aktualniLokace.getLootTable()) {
            Predmety predmet = gameData.findItem(itemId);
            if (predmet != null) {
                if (predmet.getNazev().equalsIgnoreCase(nazevPredmetu)
                        || predmet.getId().equalsIgnoreCase(nazevPredmetu)) {
                    inventar.add(predmet);
                    aktualniLokace.removePredmet(itemId);
                    return "Sebral jsi: " + predmet.getNazev();
                }
            }
        }
        return "Předmět '" + nazevPredmetu + "' tu není.";
    }

    public String pouzij(String nazevPredmetu) {
        // TODO implementace pouziti predmetu
        return null;
    }

    public String mluv(String jmenoPostavy) {
        if (aktualniLokace.getNpcs() == null || aktualniLokace.getNpcs().isEmpty()) {
            return "Nikdo tu není.";
        }

        for (String npcId : aktualniLokace.getNpcs()) {
            Postavy postava = gameData.findCharacter(npcId);
            if (postava != null) {
                if (postava.getJmeno().equalsIgnoreCase(jmenoPostavy) || npcId.equals(jmenoPostavy)
                        || npcId.contains(jmenoPostavy)) {
                    return postava.mluv();
                }
            }
        }
        return "Postava '" + jmenoPostavy + "' tu není.";
    }

    public String inventar() {
        if (inventar.isEmpty()) {
            return "Tvůj inventář je prázdný.";
        }
        StringBuilder sb = new StringBuilder("V batohu máš:\n");
        for (Predmety p : inventar) {
            sb.append("- ").append(p.getNazev()).append("\n");
        }
        return sb.toString();
    }

    public String showUkoly() {
        // TODO vypis ukolu
        return null;
    }

    public String kup(String vec) {
        // TODO implementace nakupu
        return null;
    }

    public String nahravej() {
        // TODO implementace nahravani
        return null;
    }

    public String publikuj() {
        // TODO implementace publikovani
        return null;
    }

    public String freestyle() {
        // TODO implementace freestyle
        return null;
    }

    @Override
    public String toString() {
        return inventar();
    }
}
