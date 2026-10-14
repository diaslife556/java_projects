import java.io.Serializable;
import java.util.*;

public class Jelovnik implements Serializable {
    private List<Jelo> listaJela= new ArrayList<>();
    
    public void dodajJelo() {
        Integer odabir=-1;
        String imeTemp;
        Integer kalorijeTemp;
        String vegetarijansko;
        Boolean vegetarijanskoTemp=false;
        String[] sastojciTemp = new String[20];
        String sastojakTemp;
        Scanner sc = new Scanner(System.in);
        System.out.println("Unesite ime:");
        imeTemp=sc.nextLine();
        System.out.println("Unesite kalorije:");
        kalorijeTemp=sc.nextInt();
        System.out.print("Je li vegetarijansko (Y/N):");
        vegetarijansko=sc.next();
        if(vegetarijansko.equals("Y")){
            vegetarijanskoTemp=true;
        }else if (vegetarijansko.equals("N")){
            vegetarijanskoTemp=false;
        }
        Jelo jeloTemp = new Jelo(imeTemp,kalorijeTemp,vegetarijanskoTemp);
        while(!(odabir ==2)) {
            System.out.print("Želite li unijeti sastojak?(1.Da/2.Ne):");
            odabir=sc.nextInt();
            sc.nextLine();
            if(odabir==1){
                System.out.print("Unesite sastojak: ");
                sastojakTemp=sc.nextLine();
                jeloTemp.dodajSastojak(sastojakTemp);
            }else if(odabir==2){
                System.out.println("Jelo unešeno!");
                listaJela.add(jeloTemp);
            }

        }

    }


    public void dohvatiJelo(String imeTemp){
        Boolean pronadeno=false;
        for(Jelo jelo : listaJela){
            if(jelo.getIme().equals(imeTemp)){
                System.out.println("Dohvaćeno jelo: "+jelo.toString());
                pronadeno=true;
            }
        }
        if(pronadeno==false){System.out.println("Nije pronađeno jelo tog imena!");}
    }
    public List<Jelo> dohvatiSvaJela() {
        Collections.sort(listaJela);
        return listaJela;
    }

    public List<Jelo> getListaJela() {
        return listaJela;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Jelovnik jelovnik = (Jelovnik) o;
        return Objects.equals(getListaJela(), jelovnik.getListaJela());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getListaJela());
    }
}
