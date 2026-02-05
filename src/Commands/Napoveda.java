package Commands;

import java.util.Scanner;
import java.util.Set;

public class Napoveda implements Command {
    Scanner scanner = new Scanner(System.in);
    private Set<String> commands;

    public Napoveda(Set<String> commands) {
        this.commands = commands;
    }

    @Override
    public String execute(String[] args) {
        System.out.println("Jaký typ nápovědy chceš?:\n" + "1. Příkazy\n" + "2. Rady do hry");
        String volba = scanner.nextLine();
        if (volba.equals("1")) {
            return "Dostupné příkazy: " + String.join(", ", commands);
        } else if (volba.equals("2")) {
            return "Pro více nápověd zajdi za producentem a ten ti pomůže.";
        }
        return "Neplatná volba.";
    }

    @Override
    public boolean exit() {
        return false;
    }
}
