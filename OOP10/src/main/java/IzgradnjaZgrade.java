import java.util.List;

public class IzgradnjaZgrade extends IzgradnjaNekretnine{
    public IzgradnjaZgrade(String naziv, Double budget, List<String> listaDozvola){
        super(naziv, budget, listaDozvola);
    }
    public void projektiranje(){
        System.out.println("Naziv projekta: "+ naziv);
        System.out.println("Budget projekta: "+ budget);
    }
    public void koraciIzgradnje(){
        System.out.println("1. Pribavljanje dozvola, 2.Dovođenje strojeva 3.Marketing/Reklamiranje");
    }
    public void pribavljanjeDozvola(){
        for(int i=0; i<listaDozvola.size(); i++){
            System.out.println(i+1+". Dozvola je "+ listaDozvola.get(i));
        }

    }
}
