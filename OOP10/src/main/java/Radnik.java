import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
public class Radnik extends GrađevinskiResurs{
    private static Integer staticId = 0;
    private String prezime;
    private Double satnica;
    public List<Zadatak> dodijeljeniZadaci = new ArrayList<>();

    private String VelikoSlovo(String s) {
        if (s == null || s.isEmpty()) return s;
        return s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
    }

    public Radnik(String ime, String prezime, Double satnica, String lokacija) {
        super(++staticId, ime, true, lokacija);
        this.prezime = VelikoSlovo(prezime);
        this.satnica = satnica;
    }

    public void setSatnica(Double satnica) throws NeispravnaSatnicaException {
        if (satnica < 5.0) {
            throw new NeispravnaSatnicaException("GREŠKA: Satnica ne smije biti ispod 5€!");
        }
        this.satnica = satnica;
    }

    public String toString() {
        List<String> imenaZadataka = new ArrayList<>();
        for (Zadatak z : dodijeljeniZadaci) {
            imenaZadataka.add(z.getIme());
        }
        return "Radnik{" +
                "id=" + getId() +
                ", " + getIme() + " "  + prezime +
                ", satnica=" + satnica +
                "€, zadatak=" + imenaZadataka +
                ", Plaća: "+izrTrosak()+"€";
    }

    public void dodijeliZadatak(Zadatak zadTemp) {
        dodijeljeniZadaci.add(zadTemp);
    }

    @Override
    public Double izrTrosak(){
        double ukupno = 0.0;
        for(Zadatak z : dodijeljeniZadaci) {
            ukupno += z.getProcijenjenoTrajanje() * 8 * this.satnica;
        }
        return ukupno;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Projekt)) return false;
        Projekt projekt = (Projekt) o;
        return getId().equals(projekt.getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }
    public Double getSatnica() {
        return satnica;
    }
}