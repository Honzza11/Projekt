package Commands;

public class Info implements Command{
    private Hrac hrac;
    public Info(Hrac hrac){
        this.hrac=hrac;
    }
    @Override
    public String execute(String[] args) {
        System.out.println("Tvé jméno: "+hrac.getJmeno());
        System.out.println("Tvé peníze: "+hrac.getPenize()+" $");
        return "Tvá reputace: "+hrac.getReputace();
    }

    @Override
    public boolean exit() {
        return false;
    }
}
