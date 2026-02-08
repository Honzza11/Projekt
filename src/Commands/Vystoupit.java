package Commands;

import Ukoly.Ukol;

public class Vystoupit implements Command {
    private Hrac hrac;

    public Vystoupit(Hrac hrac) {
        this.hrac = hrac;
    }

    @Override
    public String execute(String[] args) {
        String locId = hrac.getAktualniLokace().getId();
        if (locId == null || !locId.equals("loc_rolling_loud")) {
            return "Vystoupit můžeš jenom na Rolling Loud Atlanta 2030!";
        }

        Ukol finalQuest = null;
        for (Ukol q : hrac.getMojeUkoly()) {
            if (q.getId().equals("q_final_show") && q.isSplneno()) {
                finalQuest = q;
                break;
            }
        }

        if (finalQuest == null) {
            return "Ještě nemáš pass do zákulisí! Musíš nejdřív splnit úkol u Klubového Promotéra.";
        }

        return "****************************************************\n" +
                "GRATULUJEME! VYSTOUPIL JSI NA ROLLING LOUD ATLANTA!\n" +
                "Stal jsi se globální hvězdou a tvůj příběh z UG rapu\n" +
                "přerostl v celosvětovou legendu.\n" +
                "****************************************************\n" +
                "DOHRÁL JSI HRU. Můžeš hrát dál, pokud chceš pokračovat\n" +
                "v budování své kariéry a reputace.";
    }

    @Override
    public boolean exit() {
        return false;
    }
}
