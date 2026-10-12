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

    public void setSatnica(Double satnica) {
        if (satnica < 5.0) {
            throw new IllegalArgumentException("Satnica ne smije biti ispod 5€!");

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
                "Ime='" + getIme() + '\'' +
                ", prezime='" + prezime + '\'' +
                ", satnica=" + satnica +
                ", dodijeljeni zadaci=" + imenaZadataka +
                '}';
    }

    public void dodijeliZadatak(Zadatak zadTemp) {
        dodijeljeniZadaci.add(zadTemp);
    }

    public Double izracunajPlacu(){
        double ukupno = 0.0;
        for(Zadatak z : dodijeljeniZadaci) {
            ukupno += z.getProcijenjenoTrajanje() * 8 * this.satnica;
        }
        return ukupno;
    }

    public String getPrezime() {
        return prezime;
    }
    public Double getSatnica() {
        return satnica;
    }
}