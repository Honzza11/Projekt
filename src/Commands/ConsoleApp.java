package Commands;

import HerniMechaniky.GameData;
import Lokace.Lokace;

import java.util.HashMap;
import java.util.Scanner;

public class ConsoleApp {
    private Scanner scanner;
    private HashMap<String, Command> commands;
    private boolean isExit;
    private GameData gameData;
    private Hrac hrac;

    public ConsoleApp(Hrac hrac) {
        this.scanner = new Scanner(System.in);
        this.commands = new HashMap<>();
        this.isExit = false;

        this.gameData = GameData.loadGameDataFromResources("/GameData.json");
        Lokace start = gameData.findLocation("loc_byt");
        if (start == null)
            start = new Lokace("Fallback", "Fallback");

        this.hrac = new Hrac("Player", start, gameData);
    }

    public ConsoleApp() {
        this.scanner = new Scanner(System.in);
        this.commands = new HashMap<>();
        this.isExit = false;

        this.gameData = GameData.loadGameDataFromResources("/GameData.json");
        Lokace start = gameData.findLocation("loc_byt");
        if (start == null)
            start = new Lokace("Fallback", "Fallback");

        this.hrac = new Hrac("Player", start, gameData);
    }

    public void inicialization() {
        commands.put("jdi", new Jdi(hrac));
        commands.put("vezmi", new Vezmi(hrac));
        commands.put("prozkoumej", new Prozkoumej(hrac));
        commands.put("mluv", new Mluv(hrac));
        commands.put("inventar", new Inventar(hrac));
        commands.put("ukoly", new Ukoly(hrac));
        commands.put("pouzij", new Pouzij(hrac));
        commands.put("kup", new Kup(hrac));
        commands.put("napsat_text", new NapsatText(hrac));
        commands.put("vytvoritskladbu", new VytvoritSkladbu(hrac));
        commands.put("publikuj", new Publikuj(hrac));
        commands.put("freestyle", new Freestyle(hrac));
        commands.put("konec", new Konec());
        commands.put("napoveda", new Napoveda(commands.keySet()));
        commands.put("info", new Info(hrac));
    }

    public void execute() {
        System.out.print(">> ");
        if (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(" ", 2);
            String commandName = parts[0].toLowerCase();
            String[] args = parts.length > 1 ? parts[1].split(" ") : new String[0];
            String arg = parts.length > 1 ? parts[1] : "";

            if (commands.containsKey(commandName)) {
                Command cmd = commands.get(commandName);

                // Zpracování voleb v dialogu (číselný vstup)
                if (hrac.getAktivniDialogNpcId() != null && line.trim().matches("\\d+")) {
                    System.out.println(commands.get("mluv").execute(new String[] { line.trim() }));
                } else {
                    // Resetování stavu dialogu, pokud uživatel napíše jiný příkaz
                    if (!commandName.equals("mluv") && !commandName.equals("kup")) {
                        hrac.setAktivniDialogNpcId(null);
                    }
                    System.out.println(cmd.execute(new String[] { arg }));
                }

                if (cmd.exit()) {
                    isExit = true;
                }
            } else if (hrac.getAktivniDialogNpcId() != null && line.trim().matches("\\d+")) {
                // Zadáno číslo, ale commandName není příkaz - zkusit volbu v dialogu
                System.out.println(commands.get("mluv").execute(new String[] { line.trim() }));
            } else {
                System.out.println("Neznamy prikaz.Zkus zadat prikaz:'napoveda'.");
            }
        }
    }

    public void start() {
        System.out.println("Vítejte ve hře 'Dobrodružství UG Rappera'\nZadejte své rap jméno:");
        String jmeno = scanner.nextLine();
        hrac.setJmeno(jmeno);
        inicialization();
        do {
            execute();
        } while (!isExit);
    }
}
