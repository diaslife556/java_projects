import java.util.List;

public class NajboljiKvalitetStrategija implements NabavaStrategija{
    @Override
    public Dobavljac odaberi(List<Dobavljac> dobavljaci) {

        Dobavljac najbolji = dobavljaci.get(0);
        int maxKvaliteta = najbolji.getKvaliteta();

        for (Dobavljac d : dobavljaci) {
            if (d.getKvaliteta() > maxKvaliteta) {
                maxKvaliteta = d.getKvaliteta();
                najbolji = d;
            }
        }
        return najbolji;
    }
}
