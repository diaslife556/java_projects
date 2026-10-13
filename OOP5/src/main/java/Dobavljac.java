import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Dobavljac {
    public static Integer staticId = 0;
    public Integer id;
    public String name;
    public String kontakt;
    public List<String> listaMaterijala = new ArrayList<>();
    public Boolean dostupnost;
    public String GodinaProizvodnje;
    public String lozinka;
    private String VelikoSlovo(String s) {
        if (s == null || s.isEmpty()) return s;
        return s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
    }

    public Dobavljac(String name, String kontakt, List<String> listaMaterijala, Boolean dostupnost,  String GodinaProizvodnje) {
        staticId++;
        this.id = staticId;
        this.name = VelikoSlovo(name);
        this.kontakt = kontakt;
        this.listaMaterijala = listaMaterijala;
        this.dostupnost = dostupnost;
        this.GodinaProizvodnje = GodinaProizvodnje;
    }

    public List<String> listaMaterijalID() {
        List<String> listaMaterijalID = new ArrayList<>();
        String GodinaID = this.GodinaProizvodnje.substring(2,4);
        for(int i=0 ; i < listaMaterijala.size(); i++){
        String ImeID = listaMaterijala.get(i).substring(0,3).toUpperCase();
        String FullID = ImeID +"-"+ GodinaID +"-"+ id;
        listaMaterijalID.add(FullID);
        }
        return listaMaterijalID;
    }

    public String dajPonudu() {
        return "Dobavljac{" +
                "Ime:'" + name + '\'' +
                ", kontakt='" + kontakt + '\'' +
                '}';
    }

    public void provjeriDostupnost() {
        if (dostupnost != null && dostupnost) {
            System.out.println("Dobavljac je dostupan.");
        }
    }

    public void ispisiMaterijaleIKolicine() {
        System.out.println("Materijali i količine:");
        for (String materijalTemp : listaMaterijala) {
            if (materijalTemp.contains(":")) {
                String[] dijelovi = materijalTemp.split(":", 2);
                String materijal = dijelovi[0].trim();
                String kolicina = dijelovi[1].trim();
                System.out.println("- " + materijal + ": " + kolicina);
            } else {
                System.out.println("- " + materijalTemp + ": (količina nije navedena)");
            }
            System.out.println("==============================================");
        }
    }
    public String getInicijali() {
        String[] dijelovi = name.split("\\s");
        StringBuilder inicijali = new StringBuilder();
        inicijali.append(name.charAt(0));
        for (int i = 0; i < name.length() - 1; i++) {
            if (name.charAt(i) == ' ') {
                inicijali.append(Character.toUpperCase(name.charAt(i + 1)));
            }
        }
        return inicijali.toString();
    }
    public String generirajLozinku() {
        String inicijali = getInicijali();
        String znakovi = "$#%!";
        Random rnd = new Random();
        char znak1 = znakovi.charAt(rnd.nextInt(znakovi.length()));
        char znak2 = znakovi.charAt(rnd.nextInt(znakovi.length()));

        StringBuilder lozinkaTemp = new StringBuilder();
        lozinkaTemp.append(inicijali);
        lozinkaTemp.append("9611");
        lozinkaTemp.append(znak1);
        lozinkaTemp.append(znak2);
        this.lozinka = lozinkaTemp.toString();
        return this.lozinka;
    }

}