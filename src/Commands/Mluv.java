package Commands;

import java.util.*;
import Ukoly.Ukol;

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

        String input = args[0].trim();
        String activeNpc = hrac.getAktivniDialogNpcId();

        // 1. Zprocesovani volby
        if (input.matches("\\d+") && activeNpc != null) {
            int choice = Integer.parseInt(input);
            if (activeNpc.contains("producent")) {
                if (choice == 1) {
                    return getProducentBeats();
                } else if (choice == 2) {
                    return getProducentRady();
                } else if (choice == 3) {
                    return hrac.mluv(activeNpc);
                }
            } else if (activeNpc.contains("moderator")) {
                if (choice == 1) {
                    return "Moderátor Rádia: Tak ukaž, co v tobě je! Stačí napsat 'publikuj [název]' a uvidíme.";
                } else if (choice == 2) {
                    return hrac.mluv(activeNpc);
                }
            } else if (activeNpc.contains("freestyle_rapper")) {
                if (choice == 1) {
                    return getFreestyleLevelsStatus();
                } else if (choice == 2) {
                    return hrac.mluv(activeNpc);
                }
            } else if (activeNpc.contains("obchodnik")) {
                if (choice == 1) {
                    return getObchodnikNabidka();
                }
            } else if (activeNpc.contains("promoter")) {
                if (choice == 1) {
                    return "Klubový Promotér: Rolling Loud ATL 2030? To je ten největší stage na světě. Pokud se tam chceš dostat, musíš mi dokázat, že jsi absolutní špička.";
                } else if (choice == 2) {
                    return hrac.mluv(activeNpc);
                }
            }
            return "Neplatná volba. Zkus to znovu.";
        }

        // 2. Start konverzace
        if (input.toLowerCase().contains("producent")) {
            hrac.setAktivniDialogNpcId("char_producent");
            String msg = "TDF Producent: Čau! Co tě zajímá?\n" +
                    "1) Chci vidět tvé beaty\n" +
                    "2) Kde se tady dají vydat skladby?";

            // Dynamické přidání úkolu
            if (hrac.getGameData().quests != null) {
                for (Ukol q : hrac.getGameData().quests) {
                    if (q.getGiverCharacterId().equals("char_producent")) {
                        Ukol hracuvUkol = null;
                        for (Ukol mq : hrac.getMojeUkoly()) {
                            if (mq.getId().equals(q.getId())) {
                                hracuvUkol = mq;
                                break;
                            }
                        }

                        if (hracuvUkol == null) {
                            msg += "\n3) " + q.getNazev();
                        } else if (!hracuvUkol.isSplneno()) {
                            msg += "\n3) " + q.getNazev() + " (v průběhu)";
                        }
                        break;
                    }
                }
            }
            return msg;
        }

        if (input.toLowerCase().contains("obchod") || input.toLowerCase().contains("obchodnik")) {
            hrac.setAktivniDialogNpcId("npc_obchodnik");
            return "Obchodník: Vítej! Co pro tebe můžu udělat?\n" +
                    "1) Chci vidět nabídku mikrofonů";
        }

        if (input.toLowerCase().contains("moderator") || input.toLowerCase().contains("radio")) {
            hrac.setAktivniDialogNpcId("char_moderator");
            String msg = "Moderátor Rádia: Čau! Chceš se stát hvězdou?\n" +
                    "1) Jak můžu publikovat skladbu?";

            // Dynamické přidání úkolu
            if (hrac.getGameData().quests != null) {
                for (Ukol q : hrac.getGameData().quests) {
                    if (q.getGiverCharacterId().equals("char_moderator")) {
                        Ukol hracuvUkol = null;
                        for (Ukol mq : hrac.getMojeUkoly()) {
                            if (mq.getId().equals(q.getId())) {
                                hracuvUkol = mq;
                                break;
                            }
                        }

                        if (hracuvUkol == null) {
                            msg += "\n2) " + q.getNazev();
                        } else if (!hracuvUkol.isSplneno()) {
                            msg += "\n2) " + q.getNazev() + " (v průběhu)";
                        }
                        break;
                    }
                }
            }
            return msg;
        }

        if (input.toLowerCase().contains("freestyle") || input.toLowerCase().contains("rapper")) {
            hrac.setAktivniDialogNpcId("char_freestyle_rapper");
            String msg = "MC Freestyle: Čau! Chceš zkusit battle?\n" +
                    "1) Ukázání levelů freestyle battlů";

            // Dynamické přidání úkolu
            if (hrac.getGameData().quests != null) {
                for (Ukol q : hrac.getGameData().quests) {
                    if (q.getGiverCharacterId().equals("char_freestyle_rapper")) {
                        Ukol hracuvUkol = null;
                        for (Ukol mq : hrac.getMojeUkoly()) {
                            if (mq.getId().equals(q.getId())) {
                                hracuvUkol = mq;
                                break;
                            }
                        }

                        if (hracuvUkol == null) {
                            msg += "\n2) " + q.getNazev();
                        } else if (!hracuvUkol.isSplneno()) {
                            msg += "\n2) " + q.getNazev() + " (v průběhu)";
                        }
                        break;
                    }
                }
            }
            return msg;
        }

        if (input.toLowerCase().contains("promoter") || input.toLowerCase().contains("klub")) {
            hrac.setAktivniDialogNpcId("char_promoter");
            String msg = "Klubový Promotér: Čau, vypadáš jako někdo, kdo hledá velkou příležitost.\n" +
                    "1) Otázka na Rolling Loud ATL 2030";

            // Dynamické přidání úkolu
            if (hrac.getGameData().quests != null) {
                for (Ukol q : hrac.getGameData().quests) {
                    if (q.getGiverCharacterId().equals("char_promoter")) {
                        Ukol hracuvUkol = null;
                        for (Ukol mq : hrac.getMojeUkoly()) {
                            if (mq.getId().equals(q.getId())) {
                                hracuvUkol = mq;
                                break;
                            }
                        }

                        if (hracuvUkol == null) {
                            msg += "\n2) " + q.getNazev();
                        } else if (!hracuvUkol.isSplneno()) {
                            msg += "\n2) " + q.getNazev() + " (v průběhu)";
                        }
                        break;
                    }
                }
            }
            return msg;
        }

        return hrac.mluv(input);
    }

    private String getFreestyleLevelsStatus() {
        StringBuilder sb = new StringBuilder("Status levelů freestyle battlů:\n");
        Set<Integer> defeated = hrac.getPorazeneFreestyleLevely();
        for (int i = 1; i <= 5; i++) {
            sb.append("- Level ").append(i);
            if (defeated.contains(i)) {
                sb.append(" [PORAŽENO]");
            } else {
                sb.append(" [Zatím neporaženo]");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    private String getProducentBeats() {
        return "Nabídka beatů (napiš 'kup [nazev]'):\n" +
                "- Beat [COMMON] - 10$\n" +
                "- Rare Beat [RARE] - 400$ (Rep 250+)\n" +
                "- Epic Beat [EPIC] - 1200$ (Rep 800+)\n" +
                "- Legendary Beat [LEGENDARY] - 3000$ (Rep 2500+)\n" +
                "- Mythic Beat [MYTHIC] - 7500$ (Rep 5000+)";
    }

    private String getProducentRady() {
        return "TDF Producent: Jasně, poradím ti. Pokud už máš hotovou skladbu, můžeš ji zkusit udat v Rádiu (loc_radio) nebo ve Studiu (loc_studio).\n"
                +
                "V rádiu získáš víc slávy, ve studiu ti dají víc peněz. Stačí tam zajít a napsat 'publikuj [název_skladby]'.";
    }

    private String getObchodnikNabidka() {
        return "Obchodník: Mám tyhle mikrofony (napiš 'kup [nazev]'):\n" +
                "- Polorozbitý mikrofon (Common) - 150$ (+110%)\n" +
                "- Dětský mikrofon (Uncommon) - 500$ (+120%)\n" +
                "- Herní mikrofon (Rare) - 2000$ (+150%)\n" +
                "- Mid-range mikrofon (Epic) - 5000$ (+185%)\n" +
                "- Profesionální studiový mikrofon (Legendary) - 12000$ (+225%)";
    }

    @Override
    public boolean exit() {
        return false;
    }
}
