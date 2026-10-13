import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Projekt {

    public static Integer staticId = 0;
    public Integer id;
    public String name;
    public String location;
    public Double budget;
    List<Zadatak> zadaciProjekta = new ArrayList<>();
    Scanner sc = new Scanner(System.in);

    private String VelikoSlovo(String s) {
        if (s == null || s.isEmpty()) return s;
        return s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
    }

    Projekt(String name, String location, Double budget){
        staticId++;
        this.id = staticId;
        this.name = VelikoSlovo(name);
        this.location = location;
        this.budget = budget;
    }

    public String projektID() {
        String ImeID = this.name.substring(0,3).toUpperCase();
        String LocationID = this.location.substring(0,3).toUpperCase();
        String FullID = ImeID +"-"+ LocationID +"-"+ id;
        return "Projekt ID: " + FullID;
    }

    public String projektName() {
        return name;
    }
    public String toString() {
        List<String> imenaZadataka = new ArrayList<>();
        for (Zadatak z : zadaciProjekta) {
            imenaZadataka.add(z.name);
        }
        return "Projekt{" +
                "Naziv:'" + name + '\'' +
                ", lokacija:'" + location + '\'' + ", budžet=" + budget + ", zadaci: "+ imenaZadataka+'}';
    }
    public void dodijeliZadatakProjektu(Zadatak zadTemp){
        zadaciProjekta.add(zadTemp);
    }

    public Double izrPotrosnjuBudzeta(List<Radnik> listaRadnika) {
        double trosakProjekta = this.budget;
        double trosakRadnika = 0.0;
        for (Radnik r : listaRadnika) {
            if (!r.dodijeljeniZadaci.isEmpty()) {
                Zadatak z = r.dodijeljeniZadaci.get(0);
                if (zadaciProjekta.contains(z)) {
                    trosakRadnika += z.procijenjenoTrajanje * 8 * r.satnica;
                }
            }
        }
        double ukupno = trosakProjekta + trosakRadnika;
        return Math.round(ukupno * 100.0) / 100.0;
    }
}