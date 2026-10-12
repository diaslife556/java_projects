import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.util.Random;
import java.util.Scanner;

public class CM6 {
    public static void main(String[] args) {

        List<Projekt> listaProjekt = new ArrayList<>();
        listaProjekt.add(new Projekt("Garaža", "Zagreb", 1000000.0));
        listaProjekt.add(new Projekt("Novotel", "Osijek",  2500000.0));
        listaProjekt.add(new Projekt("Voćarnica", "Županja",  50000.0));

        List<Dobavljac> listaDobavljac = new ArrayList<>();
        listaDobavljac.add(new Dobavljac("Pero Hrast", "0975654321", List.of("Tapete: 1000 kg"),"Mješalica: 9.0", false,2021, "Vukovar"));
        listaDobavljac.add(new Dobavljac("Ivo Moler", "0975654321", List.of("Beton: 230 vreća"), "Bager: 25.5", true,2023, "Vinkovci"));
        listaDobavljac.add(new Dobavljac("Joža Karton", "032564195",  List.of("Tapete: 250 paketa"), "Kombi: 32.56", true,2022, "Ilok"));
        listaDobavljac.add(new Dobavljac("Jole Živković", "032564332",  List.of("Željezo: 1500 kg"), "Bušilice: 6.79", true,2025, "Otok"));
        List<String> kontaktDuplikati = new ArrayList<>();
        String kontaktRegex = "^(\\+385|0)\\d{8,9}$";

        List<Radnik> listaRadnika = new ArrayList<>();
        listaRadnika.add(new Radnik("PEro", "PerIĆ", 9.50, "Zagreb"));
        listaRadnika.add(new Radnik("Luka", "Lukić", 8.6, "Osijek"));
        listaRadnika.add(new Radnik("Marko", "Marković", 5.5, "Županja"));
        listaRadnika.add(new Radnik("LeO", "LeoNIć", 12.0, "Zagreb"));
        listaRadnika.add(new Radnik("JUlia", "JuLia", 10.50, "Županja"));

        List<Zadatak> listaZadataka = new ArrayList<>();
        listaZadataka.add(new Zadatak("Miješanje cementa", 15, 1500.0, "UTijeku"));
        listaZadataka.add(new Zadatak("Postavljanje tapeta", 4, 2000.0, "Završen"));
        listaZadataka.add(new Zadatak( "Umrežavanje", 9, 3000.0, "NijePočeo"));

        //Polimorfizam
        List<GrađevinskiResurs> resursi = new ArrayList<>();
        resursi.addAll(listaRadnika);
        resursi.addAll(listaDobavljac);
        Random rnd = new Random();
        for (GrađevinskiResurs r : resursi) {
        if (r instanceof Radnik radnik) {
            radnik.dodijeliZadatak(listaZadataka.get(rnd.nextInt(listaZadataka.size())));
        }}

        //Prijašnja verzija bez polimorfizma
        //listaRadnika.get(1).dodijeliZadatak(listaZadataka.get(2));
        //listaRadnika.get(2).dodijeliZadatak(listaZadataka.get(1));
        //listaRadnika.get(3).dodijeliZadatak(listaZadataka.get(2));
        //listaRadnika.get(4).dodijeliZadatak(listaZadataka.get(1));

        listaProjekt.get(0).dodijeliZadatakProjektu(listaZadataka.get(1));
        listaProjekt.get(1).dodijeliZadatakProjektu(listaZadataka.get(2));
        listaProjekt.get(1).dodijeliZadatakProjektu(listaZadataka.get(0));
        listaProjekt.get(2).dodijeliZadatakProjektu(listaZadataka.get(1));

        List<TroskovnikProjekta> listaTroskovnika = new ArrayList<>();
        listaTroskovnika.add(new TroskovnikProjekta(listaProjekt.get(0), LocalDate.of(2021,9,21) , listaRadnika, listaDobavljac.get(0)));
        listaTroskovnika.add(new TroskovnikProjekta(listaProjekt.get(1), LocalDate.of(2023,2,4), listaRadnika, listaDobavljac.get(1)));
        listaTroskovnika.add(new TroskovnikProjekta(listaProjekt.get(2), LocalDate.of(2025,1,1), listaRadnika, listaDobavljac.get(3)));
        Boolean login= false;

        for(Dobavljac d : listaDobavljac){
            d.generirajLozinku();
            System.out.println(d.getIme() + " Pass:" + d.getLozinka());
        }
        Scanner scPass = new Scanner(System.in);
        while(!login){
            Boolean pronađenPass = false;
            String lozinka;
            System.out.print("Unesite lozinku: ");
            lozinka=scPass.next();
            for(Dobavljac d : listaDobavljac) {
                if (d.getLozinka().contains(lozinka)) {
                    System.out.println("Dobrodošli!");
                    login=true;
                    pronađenPass=true;
                    break;
                }
            } if(!pronađenPass){
                System.out.println("Netočna lozinka!");
            }
        }
        System.out.println('\n'+"=====Lista Dobavljača==========================");
        for(Dobavljac d : listaDobavljac){
            System.out.println(d.dajPonudu());
            if (d.getKontakt().matches(kontaktRegex)){
                System.out.println("Validan kontakt broj!");
            } else System.out.println("Kontakt broj nije validan!");
            d.provjeriDostupnost();
            System.out.println("ID: "+d.listaMaterijalID());
            d.ispisiMaterijaleIKolicine();
        }
        for(Integer i = 0; i<listaDobavljac.size(); i++){
            String Duplikat ="";
            Dobavljac prviDobavljac = listaDobavljac.get(i);
                for(Integer j = 0; j<listaDobavljac.size(); j++){
                    Dobavljac drugiDobavljac = listaDobavljac.get(j);
                    if (drugiDobavljac.getKontakt().matches(prviDobavljac.getKontakt())){
                        Duplikat += drugiDobavljac;
                    }
                }
        }

        System.out.println('\n'+"=====Lista Projekata==========================");
        for(Projekt p : listaProjekt){
            System.out.println(p.toString());
            System.out.println(p.projektID());
            System.out.println("==============================================");
        }
        System.out.println('\n'+"=====Lista Radnika============================");
        for(Radnik r : listaRadnika){
            System.out.println(r.toString());
        }
        System.out.println('\n'+"=====Lista Zadataka===========================");
        for(Zadatak z : listaZadataka){
            System.out.println(z.toString());
        }
        System.out.println('\n' + "===Plaće radnika==============================");
        for (Radnik r : listaRadnika){
            System.out.printf("Radnik: %s %s , Plaća: %.2f€\n", r.getIme(), r.getPrezime(), r.izracunajPlacu());
        }

        Double ukupniTroskovi = 0.0;
        System.out.println('\n'+"===Troskovi projekata=========================");
        for (Projekt p : listaProjekt){
            Double trosak = p.izrPotrosnjuBudzeta(listaRadnika);
            ukupniTroskovi+=trosak;
            System.out.println("Ukupni trošak projekta " + p.getIme() + ": " + trosak + "€");
        }
        for(TroskovnikProjekta t: listaTroskovnika){
            Double TrosakDobavljaca= t.TrosakDobavljaca();
            ukupniTroskovi+=TrosakDobavljaca;
            System.out.println("Ukupni trošak resursa projekta "+t.getProjekt().getIme()+ " je: "+ TrosakDobavljaca+ "€");
        }

        System.out.println('\n'+"===Troskovnik svih projekata==================");
        System.out.println("Trosak svih projekata zajedno je: "+ ukupniTroskovi);
        System.out.println("==============================================");

        //PRETRAGA
        Scanner sc = new Scanner(System.in);
        Integer odabir = -1;
        while (odabir != 0) {
            System.out.print("\n\nOdaberi vrstu pretraživanja projekta(1)Naziv (2)Lokacija (3)Budžet (4)Troškovi (0)Izlaz: ");
            odabir = sc.nextInt();
            sc.nextLine();
            Boolean pronađen=false;
            switch(odabir) {
                case 1:
                    System.out.print("Unesi naziv projekta: ");
                    String naziv = sc.next();
                    for (Projekt p : listaProjekt){
                        if (p.getIme().equals(naziv)){
                            System.out.print(p.toString());
                            pronađen=true;
                        }
                    }
                    if (!pronađen){
                        System.out.println("Nije pronađen projekt tog imena");
                    }
                    break;
                case 2:
                    System.out.print("Unesi lokaciju projekta: ");
                    String lokacija = sc.next();
                    for(Projekt p : listaProjekt){
                        if (p.getlokacija().equals(lokacija)){
                            System.out.print(p.toString());
                            pronađen=true;
                        }
                    }
                    if (!pronađen){
                        System.out.println("Nije pronađen projekt na toj lokaciji");
                    }
                    break;
                case 3:
                    System.out.print("Unesi Budžet projekta (Format '100.0') : ");
                    Double budzetOdabran = sc.nextDouble();
                    for(Projekt p : listaProjekt){
                        if (p.getBudget().equals(budzetOdabran)){
                            System.out.print(p.toString());
                            pronađen=true;
                        }
                    }
                    if (!pronađen){
                        System.out.println("Nije pronađen projekt jednakog budžeta");
                    }
                    break;
                case 4:
                    System.out.print("Unesi trošak projekta: ");
                    Double trosakOdabran = sc.nextDouble();
                    for (TroskovnikProjekta  t : listaTroskovnika){
                        if(t.getUkupniTrosak().equals(trosakOdabran)) {
                            System.out.print(t.toString());
                            pronađen = true;
                            }
                        }
                        if (!pronađen){
                            System.out.print("Nije pronađen projekt tolikog troška");
                        }
                    break;
            }
        }
    }
    }