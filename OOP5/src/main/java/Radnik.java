import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
public class Radnik {
    public static Integer staticId = 0;
    public Integer id;
    public String name;
    public String prezime;
    public Double satnica;
    List<Zadatak> dodijeljeniZadaci = new ArrayList<>();

    private String VelikoSlovo(String s) {
        if (s == null || s.isEmpty()) return s;
        return s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
    }

    public Radnik(String name, String prezime, Double satnica) {
        staticId++;
        this.id = staticId;
        this.name = VelikoSlovo(name);
        this.prezime = VelikoSlovo(prezime);
        this.satnica = satnica;
    }

    public String toString() {
        List<String> imenaZadataka = new ArrayList<>();
        for (Zadatak z : dodijeljeniZadaci) {
            imenaZadataka.add(z.name);
        }
        return "Radnik{" +
                "id=" + id +
                "Ime='" + name + '\'' +
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
            ukupno += z.procijenjenoTrajanje * 8 * this.satnica;
        }
        return ukupno;
    }
}