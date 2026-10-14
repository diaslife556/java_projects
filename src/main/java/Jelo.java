import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Jelo implements Comparable<Jelo>{
    private String ime;
    private Integer kalorije;
    private Boolean vegetarijansko;
    private List<String> sastojci = new ArrayList<>();
    private int brojSastojaka = 0;
    public Jelo(String ime, Integer kalorije, Boolean vegetarijansko) {
        this.ime = ime;
        this.kalorije = kalorije;
        this.vegetarijansko = vegetarijansko;
    }

    public void dodajSastojak(String sastojak) {
        if (brojSastojaka < 20) {
            sastojci.add(sastojak);
            brojSastojaka++;
        } else {
            System.out.println("Ne može se dodati više od 20 sastojaka.");
        }
    }

    public int compareTo(Jelo j){
        return this.kalorije.compareTo(j.kalorije);
    }
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Jelo jelo = (Jelo) o;
        return Objects.equals(getIme(), jelo.getIme()) && Objects.equals(getKalorije(), jelo.getKalorije()) && Objects.equals(getVegetarijansko(), jelo.getVegetarijansko());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIme(), getKalorije(), getVegetarijansko());
    }

    public String getIme() {
        return ime;
    }

    public Integer getKalorije() {
        return kalorije;
    }

    public Boolean getVegetarijansko() {
        return vegetarijansko;
    }

    public List<String> getSastojci() {
        return sastojci;
    }

    public int getBrojSastojaka() {return brojSastojaka;}

    @Override
    public String toString() {
        return "Jelo{" +
                "sastojci=" + sastojci +
                ", vegetarijansko=" + vegetarijansko +
                ", kalorije=" + kalorije +
                ", ime='" + ime + '\'' +
                '}';
    }
}
