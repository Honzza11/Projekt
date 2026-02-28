package Commands;

import java.util.*;

import Lokace.*;
import Predmety.Predmety;
import Postavy.*;
import Ukoly.Ukol;
import HerniMechaniky.GameData;
import java.util.*;

/**
 * Třída reprezentující hráče ve hře. Spravuje inventář, peníze, reputaci a
 * pohyb v lokacích.
 * 
 * @author Honza
 */
public class Hrac {
    private String jmeno;
    private double penize;
    private int reputace;
    private List<Predmety> inventar;
    private Predmety vybaveneMikrofon;
    private Lokace aktualniLokace;
    private String aktivniDialogNpcId;
    private List<Ukol> mojeUkoly;
    private int pocetPublikaciRadio;
    private Set<Integer> porazeneFreestyleLevely;

    private GameData gameData;

    /**
     * Konstruktor pro vytvoření nového hráče.
     * 
     * @param jmeno           jméno hráče
     * @param startovniLokace počáteční lokace hráče
     * @param gameData        instance herních dat
     */
    public Hrac(String jmeno, Lokace startovniLokace, GameData gameData) {
        this.jmeno = jmeno;
        this.aktualniLokace = startovniLokace;
        this.gameData = gameData;
        this.penize = 100; // Původní hodnota byla 100000, ale v diskuzi 37a2b6c1 byla snaha o rebalanci
        this.reputace = 0; // Podobně pro reputaci
        this.inventar = new ArrayList<>();
        this.mojeUkoly = new ArrayList<>();
        this.pocetPublikaciRadio = 0;
        this.porazeneFreestyleLevely = new HashSet<>();
    }

    /**
     * Přidá předmět do inventáře hráče.
     * 
     * @param predmet předmět k přidání
     */
    public void pridejPredmet(Predmety predmet) {
        inventar.add(predmet);
    }

    /**
     * Přijme nový úkol.
     * 
     * @param ukol úkol k přijetí
     */
    public void prijmiUkol(Ukol ukol) {
        if (ukol != null) {
            for (Ukol u : mojeUkoly) {
                if (u.getId().equals(ukol.getId()))
                    return;
            }
            mojeUkoly.add(ukol);
        }
    }

    /**
     * Odebere předmět z inventáře hráče.
     * 
     * @param predmet předmět k odebrání
     */
    public void odeberPredmet(Predmety predmet) {
        inventar.remove(predmet);
    }

    /**
     * Zvýší počet publikovaných skladeb v rádiu.
     */
    public void zvyseniPublikaceRadio() {
        this.pocetPublikaciRadio++;
    }

    /**
     * Vrátí počet publikovaných skladeb v rádiu.
     * 
     * @return počet publikací
     */
    public int getPocetPublikaciRadio() {
        return pocetPublikaciRadio;
    }

    /**
     * Zaznamená úroveň poraženého freestyle levelu.
     * 
     * @param level obtížnost levelu
     */
    public void upravPorazeneLevely(int level) {
        porazeneFreestyleLevely.add(level);
    }

    /**
     * Vrátí množinu poražených freestyle levelů.
     * 
     * @return množina levelů
     */
    public Set<Integer> getPorazeneFreestyleLevely() {
        return porazeneFreestyleLevely;
    }

    /**
     * Vrátí aktuální lokaci hráče.
     * 
     * @return aktuální lokace
     */
    public Lokace getAktualniLokace() {
        return aktualniLokace;
    }

    /**
     * Vrátí herní data.
     * 
     * @return herní data
     */
    public GameData getGameData() {
        return gameData;
    }

    /**
     * Pokusí se přesunout hráče do zadané lokace.
     * 
     * @param cilovaLokaceInput název nebo ID cílové lokace
     * @return zpráva o výsledku přesunu
     */
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
                    if (nova.getId().equals("loc_studio")) {
                        boolean maKlic = false;
                        for (Predmety p : inventar) {
                            if (p.getId().equals("item_klic")) {
                                maKlic = true;
                                break;
                            }
                        }
                        if (!maKlic) {
                            return "Studio je zamčené. Potřebuješ klíč, abys mohl vejít.";
                        }
                    }
                    aktualniLokace = nova;
                    return "Jdes do lokace: " + nova.getNazev() + ".\n" + nova.getPopis() + "\n"
                            + nova.getSeznamVychodu()
                            + nova.getSeznamPredmetu() + nova.getSeznamPostav();
                }
            }
        }
        return "Tam se odsud jit neda. (Zadavej ID nebo cast ID, napr 'ulice')";
    }

    /**
     * Nastaví jméno hráče.
     * 
     * @param jmeno nové jméno
     */
    public void setJmeno(String jmeno) {
        this.jmeno = jmeno;
    }

    /**
     * Vrátí jméno hráče.
     * 
     * @return jméno
     */
    public String getJmeno() {
        return jmeno;
    }

    /**
     * Pokusí se sebrat předmět z aktuální lokace.
     * 
     * @param nazevPredmetu název nebo ID předmětu
     * @return zpráva o výsledku akce
     */
    public String vezmi(String nazevPredmetu) {
        if (aktualniLokace.getLootTable() == null) {
            return "Tady nic není.";
        }

        String inputLower = nazevPredmetu.toLowerCase();
        for (String itemId : aktualniLokace.getLootTable()) {
            Predmety predmet = gameData.findItem(itemId);
            if (predmet != null) {
                boolean matchName = predmet.getNazev().toLowerCase().contains(inputLower);
                boolean matchId = predmet.getId().toLowerCase().contains(inputLower);

                if (matchName || matchId) {
                    inventar.add(predmet);
                    aktualniLokace.removePredmet(itemId);
                    return "Sebral jsi: " + predmet.getNazev();
                }
            }
        }
        return "Předmět '" + nazevPredmetu + "' tu není.";
    }

    /**
     * Zkontroluje, zda má hráč klíč od studia.
     * 
     * @return true, pokud má klíč
     */
    public boolean maKlicOdStudia() {
        for (Predmety p : inventar) {
            if (p.getId().equals("item_klic")) {
                return true;
            }
        }
        return false;
    }

    /**
     * Spustí rozhovor s postavou.
     * 
     * @param jmenoPostavy název nebo ID postavy
     * @return text rozhovoru nebo zpráva o nepřítomnosti
     */
    public String mluv(String jmenoPostavy) {
        if (aktualniLokace.getNpcs() == null || aktualniLokace.getNpcs().isEmpty()) {
            return "Nikdo tu není.";
        }

        for (String npcId : aktualniLokace.getNpcs()) {
            Postavy postava = gameData.findCharacter(npcId);
            if (postava != null) {
                if (postava.getJmeno().equalsIgnoreCase(jmenoPostavy) || npcId.equalsIgnoreCase(jmenoPostavy)
                        || npcId.toLowerCase().contains(jmenoPostavy.toLowerCase())
                        || (postava.getId() != null && postava.getId().equalsIgnoreCase(jmenoPostavy))) {
                    String baseMluv = postava.mluv();

                    // Kontrola úkolů
                    if (gameData.quests != null) {
                        for (Ukol q : gameData.quests) {
                            if (q.getGiverCharacterId().equals(postava.getId())) {
                                // Má hráč už tento úkol?
                                Ukol hracuvUkol = null;
                                for (Ukol mq : mojeUkoly) {
                                    if (mq.getId().equals(q.getId())) {
                                        hracuvUkol = mq;
                                        break;
                                    }
                                }

                                if (hracuvUkol == null) {
                                    // Přijetí nového úkolu
                                    prijmiUkol(q);
                                    return baseMluv + "\n[NOVÝ ÚKOL: " + q.getNazev() + "]\n" + q.getPopis();
                                } else if (!hracuvUkol.isSplneno()) {
                                    // Kontrola splnění úkolu
                                    boolean vsechnyPredmety = true;
                                    if (hracuvUkol.getRequiredItems() != null) {
                                        for (String reqItemId : hracuvUkol.getRequiredItems()) {
                                            boolean maPredmet = false;
                                            for (Predmety p : inventar) {
                                                if (p.getId().equals(reqItemId)) {
                                                    maPredmet = true;
                                                    break;
                                                }
                                            }
                                            if (!maPredmet) {
                                                vsechnyPredmety = false;
                                                break;
                                            }
                                        }
                                    }

                                    if (vsechnyPredmety) {
                                        // Kontrola počtu publikací v rádiu
                                        if (hracuvUkol.getRequiredPublicationsRadio() > 0) {
                                            if (pocetPublikaciRadio >= hracuvUkol.getRequiredPublicationsRadio()) {
                                                hracuvUkol.setSplneno(true);
                                                return baseMluv + "\n[ÚKOL SPLNĚN: " + hracuvUkol.getNazev()
                                                        + "]\nModerátor Rádia: Skvělá práce! Jsi opravdová hvězda éteru.";
                                            } else {
                                                return baseMluv + "\n[STAV ÚKOLU: " + hracuvUkol.getNazev()
                                                        + "]\nPokrok: " + pocetPublikaciRadio + "/"
                                                        + hracuvUkol.getRequiredPublicationsRadio()
                                                        + " publikací v rádiu.";
                                            }
                                        }

                                        // Kontrola poražených freestyle levelů
                                        if (hracuvUkol.getRequiredFreestyleLevelsDefeated() > 0) {
                                            if (porazeneFreestyleLevely.size() >= hracuvUkol
                                                    .getRequiredFreestyleLevelsDefeated()) {
                                                hracuvUkol.setSplneno(true);
                                                setReputace(getReputace() + hracuvUkol.getOdmena());
                                                return baseMluv + "\n[ÚKOL SPLNĚN: " + hracuvUkol.getNazev()
                                                        + "]\nMC Freestyle: Jsi skutečný král ulic! Tady máš uznání, které si zasloužíš. (+"
                                                        + hracuvUkol.getOdmena() + " reputace)";
                                            } else {
                                                return baseMluv + "\n[STAV ÚKOLU: " + hracuvUkol.getNazev()
                                                        + "]\nPokrok: " + porazeneFreestyleLevely.size() + "/"
                                                        + hracuvUkol.getRequiredFreestyleLevelsDefeated()
                                                        + " poražených levelů.";
                                            }
                                        }

                                        // Kontrola finálního úkolu
                                        if (hracuvUkol.getId().equals("q_final_show")) {
                                            boolean vsechnyHpUkoly = false;
                                            boolean maMythic = false;
                                            boolean maRadio = false;
                                            boolean maFree = false;

                                            for (Ukol mq : mojeUkoly) {
                                                if (mq.getId().equals("q_mythic_song") && mq.isSplneno())
                                                    maMythic = true;
                                                if (mq.getId().equals("q_radio_publications") && mq.isSplneno())
                                                    maRadio = true;
                                                if (mq.getId().equals("q_freestyle_legend") && mq.isSplneno())
                                                    maFree = true;
                                            }
                                            vsechnyHpUkoly = maMythic && maRadio && maFree;

                                            boolean maNejlepsiMikrofon = (vybaveneMikrofon != null
                                                    && vybaveneMikrofon.getId().equals("item_mikrofon_legendary"));
                                            boolean dostatecnaReputace = (reputace >= 10000);

                                            if (vsechnyHpUkoly && maNejlepsiMikrofon && dostatecnaReputace) {
                                                hracuvUkol.setSplneno(true);
                                                return baseMluv + "\n[ÚKOL SPLNĚN: " + hracuvUkol.getNazev()
                                                        + "]\nKlubový Promotér: Neuvěřitelné. Opravdu jsi to dokázal. Tady máš pass do zákulisí Rolling Loud ATL 2030. Celý svět na tebe čeká!";
                                            } else {
                                                String chybi = "\nKlubový Promotér: Ještě na to nemáš. Musíš splnit tyto požadavky:";
                                                if (!vsechnyHpUkoly)
                                                    chybi += "\n- Splnit úkoly od Producenta, Moderátora a MC Freestyle";
                                                if (!maNejlepsiMikrofon)
                                                    chybi += "\n- Mít nasazený Profesionální studiový mikrofon (Legendary)";
                                                if (!dostatecnaReputace)
                                                    chybi += "\n- Mít alespoň 10000 reputace (máš " + reputace + ")";
                                                return baseMluv + "\n[STAV ÚKOLU: " + hracuvUkol.getNazev() + "]"
                                                        + chybi;
                                            }
                                        }

                                        hracuvUkol.setSplneno(true);
                                        return baseMluv + "\n[ÚKOL SPLNĚN: " + hracuvUkol.getNazev()
                                                + "]\nPostava: Skvělá práce! Díky!";
                                    }
                                }
                            }
                        }
                    }
                    return baseMluv;
                }
            }
        }
        return "Postava '" + jmenoPostavy + "' tu není.";
    }

    /**
     * Vrátí textový popis obsahu inventáře.
     * 
     * @return popis inventáře
     */
    public String inventar() {
        if (inventar.isEmpty() && vybaveneMikrofon == null) {
            return "Tvůj inventář je prázdný.";
        }
        StringBuilder sb = new StringBuilder("V batohu máš:\n");
        if (vybaveneMikrofon != null) {
            sb.append("Equipment: ").append(vybaveneMikrofon.getNazev()).append("\n");
        }
        for (Predmety p : inventar) {
            String typeLabel = p.getType() != null ? p.getType().toUpperCase() : "ITEM";
            if (p.getId().contains("beat"))
                typeLabel = "BEAT";
            else if (p.getId().contains("text"))
                typeLabel = "TEXT";
            else if (p.getId().contains("skladba"))
                typeLabel = "SKLADBA";

            String displayName = p.getNazev() + "-[" + typeLabel + "]";

            if (p.getRarita() != null) {
                displayName += " [" + p.getRarita() + "]";
            }
            sb.append("- ").append(displayName).append("\n");
        }
        return sb.toString();
    }

    /**
     * Vrátí aktuálně vybavený mikrofon.
     * 
     * @return vybavený mikrofon
     */
    public Predmety getVybaveneMikrofon() {
        return vybaveneMikrofon;
    }

    /**
     * Nastaví vybavený mikrofon.
     * 
     * @param vybaveneMikrofon nový mikrofon
     */
    public void setVybaveneMikrofon(Predmety vybaveneMikrofon) {
        this.vybaveneMikrofon = vybaveneMikrofon;
    }

    /**
     * Vrátí seznam předmětů v inventáři.
     * 
     * @return seznam předmětů
     */
    public List<Predmety> getInventarList() {
        return inventar;
    }

    /**
     * Vrátí aktuální stav peněz.
     * 
     * @return stav peněz
     */
    public double getPenize() {
        return penize;
    }

    /**
     * Nastaví stav peněz.
     * 
     * @param penize nový stav peněz
     */
    public void setPenize(double penize) {
        this.penize = penize;
    }

    /**
     * Vrátí aktuální reputaci.
     * 
     * @return reputace
     */
    public int getReputace() {
        return reputace;
    }

    /**
     * Nastaví reputaci.
     * 
     * @param reputace nová reputace
     */
    public void setReputace(int reputace) {
        this.reputace = reputace;
    }

    /**
     * Vrátí ID NPC, se kterým hráč právě mluví.
     * 
     * @return ID NPC
     */
    public String getAktivniDialogNpcId() {
        return aktivniDialogNpcId;
    }

    /**
     * Nastaví ID NPC pro aktivní dialog.
     * 
     * @param aktivniDialogNpcId ID NPC
     */
    public void setAktivniDialogNpcId(String aktivniDialogNpcId) {
        this.aktivniDialogNpcId = aktivniDialogNpcId;
    }

    /**
     * Vrátí seznam úkolů hráče.
     * 
     * @return seznam úkolů
     */
    public List<Ukol> getMojeUkoly() {
        return mojeUkoly;
    }

    /**
     * Vrací textovou reprezentaci objektu (obsah inventáře).
     * 
     * @return textová reprezentace
     */
    @Override
    public String toString() {
        return inventar();
    }
}
