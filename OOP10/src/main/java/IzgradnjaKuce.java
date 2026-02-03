import java.util.List;

public class IzgradnjaKuce extends IzgradnjaNekretnine{
    public IzgradnjaKuce(String naziv, Double budget, List<String> listaDozvola){
        super(naziv, budget, listaDozvola);

    }
    public void projektiranje(){
        System.out.println("Naziv projekta: "+ naziv);
        System.out.println("Budget projekta: "+ budget);
    }
    public void koraciIzgradnje(){
        System.out.println("1. Pribavljanje dozvola,2.Kupovina piva, 3.Dovođenje strojeva\n 4.Pećenje praseta 5. Postavljanje temelja 6. Slaganje zidova");
    }
    public void pribavljanjeDozvola(){
        for(int i=0; i<listaDozvola.size(); i++){
            System.out.println(i+". Dozvola je "+ listaDozvola.get(i));
        }

    }
}
