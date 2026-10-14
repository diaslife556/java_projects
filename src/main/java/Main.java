import java.util.Scanner;

public class Main {
    static void main() {
        Jelovnik glavniJelovnik = new Jelovnik();
        
    Scanner sc = new Scanner(System.in);
    Integer odabir=5;
    String dohvatJela;
    while(odabir>0){
        System.out.println("1. Dodaj Jelo, 2.Dohvati jelo, 3.Ispiši sva jela 0.Izlaz");
        odabir=sc.nextInt();
        sc.nextLine();
        if(odabir==1){
        glavniJelovnik.dodajJelo();
        }
        else if(odabir==2){
            System.out.println("Unesi naziv jela:");
            dohvatJela=sc.next();
            dohvatJela=dohvatJela.trim();
            glavniJelovnik.dohvatiJelo(dohvatJela);
        }
        else if(odabir==3){
            System.out.println("Ispis svih jela: "+ glavniJelovnik.dohvatiSvaJela());
            System.out.println("Jelo s najviše kalorija je "+glavniJelovnik.getListaJela().getLast().getIme()+ ", s kalorijama: "+glavniJelovnik.getListaJela().getLast().getKalorije());
        }else if(odabir==0){
            break;
        }
    }
        ImportExport.Export(glavniJelovnik);
    }
}
