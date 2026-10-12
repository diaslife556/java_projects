import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class TroskovnikProjekta {
    private Projekt projekt;
    private LocalDate datumPocetka;
    private Double ukupniTrosak;
    private Dobavljac odabranDobavljac;

    public TroskovnikProjekta(Projekt projekt, LocalDate datumPocetka, List<Radnik> listaRadnika, Dobavljac odabranDobavljac) {
        this.projekt = projekt;
        this.datumPocetka = datumPocetka;
        this.ukupniTrosak = projekt.izrPotrosnjuBudzeta(listaRadnika);
        this.odabranDobavljac = odabranDobavljac;
    }

    public Double TrosakDobavljaca(){
        Double ukupniTrosakResursa= odabranDobavljac.izrTrosak()*projekt.zadaciProjekta.get(0).getProcijenjenoTrajanje()*8;
        return ukupniTrosakResursa;
    }

    public String toString() {
        return "TroškovnikProjekta{" +
                "Projekt=" + projekt.getIme() +
                ", Datum početka=" + datumPocetka +
                ", Ukupni Trošak=" + ukupniTrosak +
                "€ ,"+"Odabran dobavljač= "+odabranDobavljac.getIme();
    }

    public Projekt getProjekt() {
        return projekt;
    }
    public LocalDate getDatumPocetka() {
        return datumPocetka;
    }
    public Double getUkupniTrosak() {
        return ukupniTrosak;
    }
}

