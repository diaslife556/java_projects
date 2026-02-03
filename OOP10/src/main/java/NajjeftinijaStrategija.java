import java.util.List;

public class NajjeftinijaStrategija implements NabavaStrategija{
    @Override
    public Dobavljac odaberi(List<Dobavljac> dobavljaci) {
        Dobavljac najjeftiniji = dobavljaci.get(0);
        double minTrosak = najjeftiniji.izrTrosak();

        for (Dobavljac d : dobavljaci) {
            if (d.izrTrosak() < minTrosak) {
                minTrosak = d.izrTrosak();
                najjeftiniji = d;
            }
        }

        return najjeftiniji;
    }
}
