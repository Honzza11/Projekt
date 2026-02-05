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
                }
            } else if (activeNpc.contains("obchodnik")) {
                if (choice == 1) {
                    return getObchodnikNabidka();
                }
            }
            return "Neplatná volba. Zkus to znovu.";
        }

        // 2. Start konverzace
        if (input.toLowerCase().contains("producent")) {
            hrac.setAktivniDialogNpcId("npc_producent");
            return "TDF Producent: Čau! Co tě zajímá?\n" +
                    "1) Chci vidět tvé beaty\n" +
                    "2) Kde se tady dají vydat skladby?";
        }

        if (input.toLowerCase().contains("obchod") || input.toLowerCase().contains("obchodnik")) {
            hrac.setAktivniDialogNpcId("npc_obchodnik");
            return "Obchodník: Vítej! Co pro tebe můžu udělat?\n" +
                    "1) Chci vidět nabídku mikrofonů";
        }

        return hrac.mluv(input);
    }

    private String getProducentBeats() {
        return "Nabídka beatů (napiš 'kup [nazev]'):\n" +
                "- Beat [COMMON] - 10$\n" +
                "- Rare Beat [RARE] - 300$ (Rep 10+)\n" +
                "- Epic Beat [EPIC] - 1000$ (Rep 50+)\n" +
                "- Legendary Beat [LEGENDARY] - 2500$ (Rep 80+)\n" +
                "- Mythic Beat [MYTHIC] - 5000$ (Rep 100+)";
    }

    private String getProducentRady() {
        return "TDF Producent: Jasně, poradím ti. Pokud už máš hotovou skladbu, můžeš ji zkusit udat v Rádiu (loc_radio) nebo ve Studiu (loc_studio).\n"
                +
                "V rádiu získáš víc slávy, ve studiu ti dají víc peněz. Stačí tam zajít a napsat 'publikuj [název_skladby]'.";
    }

    private String getObchodnikNabidka() {
        return "Obchodník: Mám tyhle mikrofony (napiš 'kup [nazev]'):\n" +
                "- Polorozbitý mikrofon (Common) - 25$ (+10%)\n" +
                "- Dětský mikrofon (Uncommon) - 75$ (+20%)\n" +
                "- Herní mikrofon (Rare) - 350$ (+50%)\n" +
                "- Mid-range mikrofon (Epic) - 800$ (+85%)\n" +
                "- Profesionální studiový mikrofon (Legendary) - 1650$ (+125%)";
    }

    @Override
    public boolean exit() {
        return false;
    }
}
