import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class TroskovnikProjekta {
    public Projekt projekt;
    public LocalDate datumPocetka;
    public Double ukupniTrosak;


    public TroskovnikProjekta(Projekt projekt, LocalDate datumPocetka, List<Radnik> listaRadnika) {
        this.projekt = projekt;
        this.datumPocetka = datumPocetka;
        this.ukupniTrosak = projekt.izrPotrosnjuBudzeta(listaRadnika);
    }

    public String toString() {
        return "TroskovnikProjekta{" +
                "Projekt=" + projekt.name +
                ", Datum pocetka=" + datumPocetka +
                ", Ukupni Trosak=" + ukupniTrosak +
                "€}";
    }

}

