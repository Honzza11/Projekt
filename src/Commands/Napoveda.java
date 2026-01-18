package Commands;

import java.util.Set;

public class Napoveda implements Command {
    private Set<String> commands;

    public Napoveda(Set<String> commands) {
        this.commands = commands;
    }

    @Override
    public String execute(String[] args) {
        // TODO provedeni prikazu napoveda
        return null;
    }

    @Override
    public boolean exit() {
        return false;
    }
}
