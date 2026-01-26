package Commands;

public class Konec implements Command {
    @Override
    public String execute(String[] args) {
        return "Hra byla ukoncena";
    }

    @Override
    public boolean exit() {
        return true;
    }
}
