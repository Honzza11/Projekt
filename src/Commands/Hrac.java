package Commands;

import Lokace.*;
import Predmety.Predmety;
import Postavy.*;
import HerniMechaniky.GameData;
import java.util.*;

public class Hrac {
    private String jmeno;
    private double penize;
    private int reputace;
    private List<Predmety> inventar;
    private Predmety vybaveneMikrofon;
    private Lokace aktualniLokace;
    private String aktivniDialogNpcId;

    private GameData gameData;

    public Hrac(String jmeno, Lokace startovniLokace, GameData gameData) {
        this.jmeno = jmeno;
        this.aktualniLokace = startovniLokace;
        this.gameData = gameData;
        this.penize = 100000;
        this.reputace = 10000;
        this.inventar = new ArrayList<>();
    }

    public void pridejPredmet(Predmety predmet) {
        inventar.add(predmet);
    }

    public void odeberPredmet(Predmety predmet) {
        inventar.remove(predmet);
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
                    return "Jdes do lokace: " + nova.getNazev() + ".\n" + nova.getPopis() + "\n"
                            + nova.getSeznamVychodu()
                            + nova.getSeznamPredmetu() + nova.getSeznamPostav();
                }
            }
        }
        return "Tam se odsud jit neda. (Zadavej ID nebo cast ID, napr 'ulice')";
    }

    public void setJmeno(String jmeno) {
        this.jmeno = jmeno;
    }

    public String getJmeno() {
        return jmeno;
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
        if (inventar.isEmpty() && vybaveneMikrofon == null) {
            return "Tvůj inventář je prázdný.";
        }
        StringBuilder sb = new StringBuilder("V batohu máš:\n");
        if (vybaveneMikrofon != null) {
            sb.append("Equipment: ").append(vybaveneMikrofon.getNazev()).append("\n");
        }
        for (Predmety p : inventar) {
            String displayName = p.getNazev();
            if ((p.getId().contains("skladba") || p.getId().contains("beat") || p.getId().contains("text"))
                    && p.getRarita() != null) {
                displayName += " [" + p.getRarita() + "]";
            }
            sb.append("- ").append(displayName).append("\n");
        }
        return sb.toString();
    }

    public Predmety getVybaveneMikrofon() {
        return vybaveneMikrofon;
    }

    public void setVybaveneMikrofon(Predmety vybaveneMikrofon) {
        this.vybaveneMikrofon = vybaveneMikrofon;
    }

    public List<Predmety> getInventarList() {
        return inventar;
    }

    public double getPenize() {
        return penize;
    }

    public void setPenize(double penize) {
        this.penize = penize;
    }

    public int getReputace() {
        return reputace;
    }

    public void setReputace(int reputace) {
        this.reputace = reputace;
    }

    public String getAktivniDialogNpcId() {
        return aktivniDialogNpcId;
    }

    public void setAktivniDialogNpcId(String aktivniDialogNpcId) {
        this.aktivniDialogNpcId = aktivniDialogNpcId;
    }
    @Override
    public String toString() {
        return inventar();
    }
}
