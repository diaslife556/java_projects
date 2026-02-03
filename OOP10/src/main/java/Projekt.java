import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
public class Projekt implements Serializable {

    private static Integer staticId = 0;
    private Integer id;
    private String ime;
    private String lokacija;
    private Double budget;
    List<Zadatak> zadaciProjekta = new ArrayList<>();
    private LocalDate datum;
    private Double Trošak;


    private String VelikoSlovo(String s) {
        if (s == null || s.isEmpty()) return s;
        return s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
    }
    Projekt(String ime, String lokacija, Double budget, LocalDate datum) {
        staticId++;
        this.id = staticId;
        this.ime = VelikoSlovo(ime);
        this.lokacija = lokacija;
        this.budget = budget;
        this.datum = datum;
    }
    public String projektID() {
        String ImeID = this.ime.substring(0,3).toUpperCase();
        String lokacijaID = this.lokacija.substring(0,3).toUpperCase();
        String FullID = ImeID +"-"+ lokacijaID +"-"+ id;
        return "Projekt ID: " + FullID;
    }
    @Override
    public String toString() {
        List<String> imenaZadataka = new ArrayList<>();
        for (Zadatak z : zadaciProjekta) {
            imenaZadataka.add(z.getIme());
        }
        return "Projekt{" +
                "Naziv:'" + ime + '\'' +
                ", lokacija:'" + lokacija + '\'' + ", budžet=" + budget + ", zadaci: "+ imenaZadataka+'}';
    }
    public void dodijeliZadatakProjektu(Zadatak zadTemp){
        zadaciProjekta.add(zadTemp);
    }

    public Double izrPotrosnjuBudzeta(List<Radnik> listaRadnika) {
        double trosakProjekta = 0.0;
        for (Zadatak z : zadaciProjekta) {
            trosakProjekta += z.getBudget();
        }
        double trosakRadnika = 0.0;
        for (Radnik r : listaRadnika) {
            if (!r.dodijeljeniZadaci.isEmpty()) {
                Zadatak z = r.dodijeljeniZadaci.get(0);
                if (zadaciProjekta.contains(z)) {
                    trosakRadnika += z.getProcijenjenoTrajanje() * 8 * r.getSatnica();
                }
            }
        }
        double ukupno = trosakProjekta + trosakRadnika;
        Trošak = ukupno;
        return Math.round(ukupno * 100.0) / 100.0;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Projekt)) return false;
        Projekt projekt = (Projekt) o;
        return id.equals(projekt.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    public String getIme() {
        return ime;
    }
    public String getlokacija() {
        return lokacija;
    }
    public Double getBudget() {
        return budget;
    }
    public LocalDate getDatum() {
        return datum;
    }
    public Double getTrošak() {
        return Trošak;
    }
    public Integer getId() {
        return id;
    }
    public List<Zadatak> getZadaciProjekta() {
        return zadaciProjekta;
    }

}