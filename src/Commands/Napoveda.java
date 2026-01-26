package Commands;

import java.util.Scanner;
import java.util.Set;

public class Napoveda implements Command {
    Scanner scanner= new Scanner(System.in);
    private Set<String> commands;

    public Napoveda(Set<String> commands) {
        this.commands = commands;
    }

    @Override
    public String execute(String[] args) {
        System.out.println("Jaky typ napovedy chces?:\n"+"1.Prikaz\n"+"2.Hra");
        String volba = scanner.nextLine();
        if(volba.equals("1")){
            return "jdi,vezmi,mluv,";
        }else if(volba.equals("2")){
            return "Skladby muzes vytvaret ve studiu";
        }
            return null;
    }

    @Override
    public boolean exit() {
        return false;
    }
}
