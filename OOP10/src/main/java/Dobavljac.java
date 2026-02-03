import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Dobavljac extends GrađevinskiResurs {
    private static Integer staticId = 0;
    private String kontakt;
    private List<String> listaMaterijala;
    private String stroj;
    private Integer GodinaProizvodnje;
    private List<String> lozinka =  new ArrayList<>();
    private Integer kvaliteta;

    public Dobavljac(String ime, String kontakt, List<String> listaMaterijala, String stroj, Integer kvaliteta, Boolean status,  Integer GodinaProizvodnje, String lokacija) {
        super(++staticId, ime, status, lokacija);
        this.kontakt = kontakt;
        this.listaMaterijala = listaMaterijala;
        this.stroj = stroj;
        this.kvaliteta = kvaliteta;
        setGodinaProizvodnje(GodinaProizvodnje);
    }

    public void setGodinaProizvodnje(Integer godinaProizvodnje) {
        if((godinaProizvodnje) > 2025){
            throw new IllegalArgumentException("Godina ne može biti veća od 2025.");
        } this.GodinaProizvodnje = godinaProizvodnje;
    }

    public List<String> getLozinka() {
        return lozinka;
    }

    public List<String> listaMaterijalID() {
        List<String> listaMaterijalID = new ArrayList<>();
        String GodinaID = String.valueOf(this.GodinaProizvodnje%100);
        for(int i=0 ; i < listaMaterijala.size(); i++){
        String ImeID = listaMaterijala.get(i).substring(0,3).toUpperCase();
        String FullID = ImeID +"-"+ GodinaID +"-"+ getId();
        listaMaterijalID.add(FullID);
        }
        return listaMaterijalID;
    }

    public void provjeriDostupnost() {
        if (getStatus() != null && getStatus()) {
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
        String[] dijelovi = getIme().split("\\s");
        StringBuilder inicijali = new StringBuilder();
        inicijali.append(getIme().charAt(0));
        for (int i = 0; i < getIme().length() - 1; i++) {
            if (getIme().charAt(i) == ' ') {
                inicijali.append(Character.toUpperCase(getIme().charAt(i + 1)));
            }
        }
        return inicijali.toString();
    }
    public String generirajLozinku() {
        String inicijali = getInicijali();
        String znakovi = "$#%!";  // Specijalni znakovi
        Random rnd = new Random();
        char znak1 = znakovi.charAt(rnd.nextInt(znakovi.length()));
        char znak2 = znakovi.charAt(rnd.nextInt(znakovi.length()));

        StringBuilder lozinkaTemp = new StringBuilder();
        lozinkaTemp.append(inicijali);
        lozinkaTemp.append("9611");
        lozinkaTemp.append(znak1);
        lozinkaTemp.append(znak2);
        String novaLozinka = lozinkaTemp.toString();

        this.lozinka.add(novaLozinka);
        return novaLozinka;
    }
    @Override
    public Double izrTrosak(){
        Double TrosakPoSatu=0.0;
        String[] StrojPodijeljen= stroj.split(": ",2);
        Double cijenaStroj=Double.parseDouble(StrojPodijeljen[1]);
        String materijal = listaMaterijala.get(0);
        String MaterijalPodijeljen= materijal.split(": ")[1].split(" ")[0];
        Double cijenaMaterijal =  Double.parseDouble(MaterijalPodijeljen) * 0.1;
        TrosakPoSatu=cijenaStroj+cijenaMaterijal;
        return TrosakPoSatu;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Projekt)) return false;
        Projekt projekt = (Projekt) o;
        return staticId.equals(projekt.getId());
    }

    @Override
    public int hashCode() {
        return staticId.hashCode();
    }

    @Override
    public String toString() {
        return "=============================================="+"\nDobavljac{" + getIme()+
                ", kontakt='" + kontakt  +
                ", stroj='" + stroj + "€/h" ;
    }
    public String getKontakt() {
        return kontakt;
    }
    public Integer getKvaliteta() {
        return kvaliteta;
    }
}