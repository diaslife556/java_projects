import java.util.List;

public abstract class IzgradnjaNekretnine {
    protected String naziv;
    protected Double budget;
    protected List<String> listaDozvola;
    public IzgradnjaNekretnine(String naziv, Double budget, List<String> listaDozvola){
        this.naziv = naziv;
        this.budget = budget;
        this.listaDozvola = listaDozvola;
    }
    public final void izgradnja(){
        projektiranje();
        koraciIzgradnje();
        pribavljanjeDozvola();
    }
    public void projektiranje() {
    }
    public void koraciIzgradnje() {
    }
    protected abstract void pribavljanjeDozvola();
}
