import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.util.Scanner;

public class CM5 {
    public static void main(String[] args) {

        List<Projekt> listaProjekt = new ArrayList<>();
        listaProjekt.add(new Projekt("Garaža", "Zagreb", 1000000.00));
        listaProjekt.add(new Projekt("Novotel", "Osijek",  2500000.00));
        listaProjekt.add(new Projekt("Voćarnica", "Županja",  50000.00 ));

        List<Dobavljac> listaDobavljac = new ArrayList<>();
        listaDobavljac.add(new Dobavljac("Pero Hrast", "0975654321", new ArrayList<>(List.of("Tapete: 1500 kg")), false, "2021"));
        listaDobavljac.add(new Dobavljac("Ivo Moler", "0975654321", new ArrayList<>(List.of("Beton: 230 vreća")), true, "2023"));
        listaDobavljac.add(new Dobavljac("Joža Karton", "032564195",  new ArrayList<>(List.of("Tapete: 250 paketa")), true, "2021"));
        listaDobavljac.add(new Dobavljac("Jole Živković", "032564332",  new ArrayList<>(List.of("Željezo: 1500 kg")), true, "2022"));
        List<String> kontaktDuplikati = new ArrayList<>();
        String kontaktRegex = "^(\\+385|0)\\d{8,9}$";

        List<Radnik> listaRadnika = new ArrayList<>();
        listaRadnika.add(new Radnik("PEro", "PerIĆ", 9.50));
        listaRadnika.add(new Radnik("Luka", "Lukić", 8.6));
        listaRadnika.add(new Radnik("Marko", "Marković", 11.0));
        listaRadnika.add(new Radnik("LeO", "LeoNIć", 12.0));
        listaRadnika.add(new Radnik("JUlia", "JuLia", 10.50));

        List<Zadatak> listaZadataka = new ArrayList<>();
        listaZadataka.add(new Zadatak("Miješanje cementa", 15, 1500.0));
        listaZadataka.add(new Zadatak("Postavljanje tapeta", 4, 2000.0));
        listaZadataka.add(new Zadatak( "Umrežavanje", 9, 3000.0));

        listaRadnika.get(0).dodijeliZadatak(listaZadataka.get(0));
        listaRadnika.get(1).dodijeliZadatak(listaZadataka.get(2));
        listaRadnika.get(2).dodijeliZadatak(listaZadataka.get(1));
        listaRadnika.get(3).dodijeliZadatak(listaZadataka.get(2));
        listaRadnika.get(4).dodijeliZadatak(listaZadataka.get(1));

        listaProjekt.get(0).dodijeliZadatakProjektu(listaZadataka.get(1));
        listaProjekt.get(1).dodijeliZadatakProjektu(listaZadataka.get(2));
        listaProjekt.get(1).dodijeliZadatakProjektu(listaZadataka.get(0));
        listaProjekt.get(2).dodijeliZadatakProjektu(listaZadataka.get(1));

        List<TroskovnikProjekta> listaTroskovnika = new ArrayList<>();
        listaTroskovnika.add(new TroskovnikProjekta(listaProjekt.get(0), LocalDate.of(2021,9,21) , listaRadnika ));
        listaTroskovnika.add(new TroskovnikProjekta(listaProjekt.get(1), LocalDate.of(2023,2,4), listaRadnika));
        listaTroskovnika.add(new TroskovnikProjekta(listaProjekt.get(2), LocalDate.of(2024,1,1), listaRadnika));
        Boolean login= false;
        
        for(Dobavljac d : listaDobavljac){
            d.generirajLozinku();
            System.out.println(d.name + " Pass:" + d.lozinka);
        }
        Scanner scPass = new Scanner(System.in);
        while(!login){
            Boolean pronađenPass = false;
            String lozinka;
            System.out.println("Unesite lozinku: ");
            lozinka=scPass.next();
            for(Dobavljac d : listaDobavljac) {
                if (lozinka.matches(d.lozinka)){
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
            if (d.kontakt.matches(kontaktRegex)){
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
                    if (drugiDobavljac.kontakt.matches(prviDobavljac.kontakt)){
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
            System.out.printf("Radnik: %s %s , Plaća: %.2f€\n", r.name, r.prezime, r.izracunajPlacu());
        }

        Double ukupniTroskovi = 0.0;
        System.out.println('\n'+"===Troskovi projekata=========================");
        for (Projekt p : listaProjekt){
            Double trosak = p.izrPotrosnjuBudzeta(listaRadnika);
            ukupniTroskovi+=trosak;
            System.out.println("Ukupni trošak projekta " + p.name + ": " + trosak + "€");
        }

        System.out.println('\n'+"===Troskovnik svih projekata==================");
        System.out.println("Trosak svih projekata zajedno je: "+ ukupniTroskovi);
        System.out.println("==============================================");

        //PRETRAGA
        Scanner sc = new Scanner(System.in);
        Integer odabir = -1;
        while (odabir != 0) {
            System.out.print("\n\nOdaberi vrstu pretraživanja projekta(/1/Naziv /2/Lokacija /3/Budžet /4/Troškovi /0/Exit: ");
            odabir = sc.nextInt();
            sc.nextLine();
            Boolean pronađen=false;
            switch(odabir) {
                case 1:
                    System.out.print("Unesi naziv projekta: ");
                    String naziv = sc.next();
                    for (Projekt p : listaProjekt){
                        if (p.name.equals(naziv)){
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
                        if (p.location.equals(lokacija)){
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
                        if (p.budget.equals(budzetOdabran)){
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
                        if(t.ukupniTrosak.equals(trosakOdabran)) {
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