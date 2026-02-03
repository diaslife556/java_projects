import java.util.*;
import java.time.LocalDate;

public class OOP10 {
    public static void main(String[] args) throws GrađevinaException {

        //Boje fonta crvena za exceptione
        String RESET = "\u001B[0m";
        String RED = "\u001B[31m";

        //IMPORT IZ .DAT DATOTEKE OD PROŠLOG RUN-A (EXPERIMENTAL)
        //Object[] podaci = ImportExport.Import();
        //List<Projekt> ucitaniProjekti = (List<Projekt>) podaci[0];
        //List<Radnik> ucitaniRadnici = (List<Radnik>) podaci[1];
        //List<Dobavljac> ucitaniDobavljaci = (List<Dobavljac>) podaci[2];

        List<Projekt> listaProjekt = new ArrayList<>();
        listaProjekt.add(new Projekt("Garaža", "Zagreb", 5000.0, LocalDate.of(2026, 8, 21)));
        listaProjekt.add(new Projekt("Novotel", "Osijek",  15500.0, LocalDate.of(2023, 6, 15)));
        listaProjekt.add(new Projekt("Voćarnica", "Županja",  7000.0,  LocalDate.of(2024, 7, 29)));

        List<Dobavljac> listaDobavljac = new ArrayList<>();
        listaDobavljac.add(new Dobavljac("Pero Hrast", "0975654321", List.of("Tapete: 1000 kg"),"Mješalica: 9.0", 3,false,2021, "Vukovar"));
        listaDobavljac.add(new Dobavljac("Ivo Moler", "0975654321", List.of("Beton: 230 vreća"), "Bager: 25.5", 3,true,2023, "Vinkovci"));
        listaDobavljac.add(new Dobavljac("Joža Karton", "032564195",  List.of("Tapete: 250 paketa"), "Kombi: 32.56", 6,true,2022, "Ilok"));
        listaDobavljac.add(new Dobavljac("Jole Živković", "032564332",  List.of("Željezo: 1500 kg"), "Bušilice: 6.79", 2,true,2025, "Otok"));
        List<String> kontaktDuplikati = new ArrayList<>();
        String kontaktRegex = "^(\\+385|0)\\d{8,9}$";

        List<Radnik> listaRadnika = new ArrayList<>();
        listaRadnika.add(new Radnik("PEro", "PerIĆ", 9.50, "Zagreb"));
        listaRadnika.add(new Radnik("Luka", "Lukić", 8.6, "Osijek"));
        listaRadnika.add(new Radnik("Marko", "Marković", 4.5, "Županja"));
        listaRadnika.add(new Radnik("LeO", "LeoNIć", 12.0, "Zagreb"));
        listaRadnika.add(new Radnik("JUlia", "JuLia", 10.50, "Županja"));

        List<Zadatak> listaZadataka = new ArrayList<>();
        listaZadataka.add(new Zadatak("Miješanje cementa", 15, 1500.0, "UTijeku"));
        listaZadataka.add(new Zadatak("Postavljanje tapeta", 9, 2000.0, "Završen"));
        listaZadataka.add(new Zadatak( "Umrežavanje", 8, 3000.0, "NijePočeo"));
        listaZadataka.add(new Zadatak( "Instalacija temelja", 10, 2500.0, "UTijeku"));

        //Polimorfizam
        List<GrađevinskiResurs> resursi = new ArrayList<>();
        resursi.addAll(listaRadnika);
        resursi.addAll(listaDobavljac);
        Random rnd = new Random();
        for (GrađevinskiResurs r : resursi) {
        if (r instanceof Radnik radnik) {
            radnik.dodijeliZadatak(listaZadataka.get(rnd.nextInt(listaZadataka.size())));
        }}

        listaProjekt.get(0).dodijeliZadatakProjektu(listaZadataka.get(1));
        listaProjekt.get(0).dodijeliZadatakProjektu(listaZadataka.get(3));
        listaProjekt.get(1).dodijeliZadatakProjektu(listaZadataka.get(1));
        listaProjekt.get(1).dodijeliZadatakProjektu(listaZadataka.get(2));
        listaProjekt.get(1).dodijeliZadatakProjektu(listaZadataka.get(0));
        listaProjekt.get(1).dodijeliZadatakProjektu(listaZadataka.get(3));
        listaProjekt.get(2).dodijeliZadatakProjektu(listaZadataka.get(0));
        listaProjekt.get(2).dodijeliZadatakProjektu(listaZadataka.get(3));

        List<TroskovnikProjekta> listaTroskovnika = new ArrayList<>();
        listaTroskovnika.add(new TroskovnikProjekta(listaProjekt.get(0), LocalDate.of(2021,9,21) , listaRadnika, listaDobavljac.get(0)));
        listaTroskovnika.add(new TroskovnikProjekta(listaProjekt.get(1), LocalDate.of(2023,2,4), listaRadnika, listaDobavljac.get(1)));
        listaTroskovnika.add(new TroskovnikProjekta(listaProjekt.get(2), LocalDate.of(2025,1,1), listaRadnika, listaDobavljac.get(3)));

        List<ProjektniProces> listaProcesa = new ArrayList<>();
        List<ProcesZavršetkaProjekta> listaZavršenihProcesa = new ArrayList<>();
        listaProcesa.add(new ProcesPokretanjaProjekta("Petar Hauber", listaProjekt.get(0)));
        listaProcesa.add(new ProcesPokretanjaProjekta("Karlo Jelić", listaProjekt.get(1)));
        listaProcesa.add(new ProcesPokretanjaProjekta("Josip Horvat", listaProjekt.get(2)));
        Iterator<ProjektniProces> iterator = listaProcesa.iterator();

        //Ako su zadaci završeni premjesti objekt u klasu završeni
        while (iterator.hasNext()) {
            ProjektniProces p = iterator.next();
            boolean završen = false;

            for (Zadatak z : p.povezaniProjekt.getZadaciProjekta()) {
                if (z.getStatus().equals("Završen")) {
                    završen = true;
                    break;
                }
            }
            if (završen) {
                ProcesZavršetkaProjekta zavrsniProces =
                        new ProcesZavršetkaProjekta(
                                p.voditeljProjekta,
                                p.povezaniProjekt
                        );

                listaZavršenihProcesa.add(zavrsniProces);
                iterator.remove();
            }
        }

        //LOGIN
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
        //ISPIS NAKON PRIJAVE
        System.out.println('\n'+"=====Lista Resursa==========================");
        for(GrađevinskiResurs r : resursi) {
            System.out.println(r.toString());
            if (r instanceof Dobavljac d) {
                if (d.getKontakt().matches(kontaktRegex)){
                    System.out.println("Validan kontakt broj!");
                } else System.out.println("Kontakt broj nije validan!");
                d.provjeriDostupnost();
                System.out.println("ID: "+d.listaMaterijalID());
                d.ispisiMaterijaleIKolicine();
            }
            if(r instanceof Radnik rad){
                try{
                    rad.setSatnica(rad.getSatnica());
                } catch(GrađevinaException e){
                    System.out.println(RED+ e.getMessage()+ RESET);
                }
            }
        }
        //DUPLIKATI KONTAKTA
        for (int i = 0; i < listaDobavljac.size(); i++) {
            Dobavljac prvi = listaDobavljac.get(i);
            if (!prvi.getKontakt().matches(kontaktRegex)) {
                continue;
            }
            for (int j = i + 1; j < listaDobavljac.size(); j++) {
                Dobavljac drugi = listaDobavljac.get(j);
                if (prvi.getKontakt().equals(drugi.getKontakt())) {
                    System.out.println("Duplikat pronađen:");
                    System.out.println(prvi.getIme()+" "+prvi.getKontakt());
                    System.out.println(drugi.getIme()+" "+drugi.getKontakt());
                    System.out.println("==============================================");
                }
            }
        }
        System.out.println('\n'+"=====Lista Projekata==========================");
        for(Projekt p : listaProjekt){
            System.out.println(p.toString());
            System.out.println(p.projektID());
            System.out.println("==============================================");
        }
        System.out.println('\n'+"=====Lista Zadataka===========================");
        for(Zadatak z : listaZadataka){
            System.out.println(z.toString());
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
        System.out.println('\n'+"===Troškovnik svih projekata==================");
        System.out.println("Trošak svih projekata zajedno je: "+ ukupniTroskovi +"€");
        System.out.println("==============================================");

        System.out.println("\n===Lista Pokrenutih Procesa=====================");
        boolean ok;
        for (ProjektniProces proces : listaProcesa) {
            System.out.println("\nVoditelj: " + proces.voditeljProjekta+", Projekt: "+proces.povezaniProjekt.getIme());
            proces.dajPopisKoraka();
            try {
                ok = proces.provjeriPotrebnuDokumentaciju();
                if (ok) {
                    System.out.println("Dokumentacija je ispravna.");
                } else {
                   throw new GrađevinaException("NEISPRAVNA DOKUMENTACIJA");
                }
            } catch (GrađevinaException error) {
                System.out.println(RED+ error.getMessage() + RESET);
            }
            finally{
            proces.ispišiDatum();}
        }
        System.out.println("===Završeni procesi================================");
        for(ProcesZavršetkaProjekta završeni : listaZavršenihProcesa){
            System.out.println("\nVoditelj: " + završeni.voditeljProjekta+", Projekt: "+završeni.povezaniProjekt.getIme());
            završeni.dajPopisKoraka();
            try {
                ok = završeni.provjeriPotrebnuDokumentaciju();
                if (ok) {
                    System.out.println("Dokumentacija je ispravna.");
                } else {
                    throw new GrađevinaException("NEISPRAVNA DOKUMENTACIJA");
                }
            } catch (GrađevinaException error) {
                System.out.println(RED+"GREŠKA: " + error.getMessage()+RESET);
            }
            finally{
                završeni.ispišiDatum();}
        }

        //UPIS PODATAKA U .DAT DATOTEKU
        ImportExport.Export(listaProjekt, listaRadnika, listaDobavljac);

        //PRETRAGA
        Scanner sc = new Scanner(System.in);
        Integer odabir = -1;
        while (odabir != 0) {
            System.out.print("\n\nOdaberi vrstu pretraživanja projekta(1)Naziv (2)Lokacija (3)Budžet (4)Troškovi (5) Najjeftiniji Dobavljač (6) Najkvalitetniji Dobavljač (0)Izlaz: ");
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
                        case 5:
                            NabavaStrategija najjeftinijaStrategija= new NajjeftinijaStrategija();
                            Dobavljac najjeftiniji=najjeftinijaStrategija.odaberi(listaDobavljac);
                            System.out.println("Najjeftiniji troškovnik je: "+ najjeftiniji+ " s troškom: "+ najjeftiniji.izrTrosak()+ "€");
                          break;
                case 6: NabavaStrategija najkvalitetnijaStrategija= new NajboljiKvalitetStrategija();
                Dobavljac najbolji=najkvalitetnijaStrategija.odaberi(listaDobavljac);
                    System.out.println("Najkvalitetniji dobavljač: "+najbolji+ " Kvaliteta: "+ najbolji.getKvaliteta());
                    break;
                case 7:
            }
        }
        List<IzgradnjaNekretnine> listaNekretnina = new ArrayList<>();
        odabir = -1;
        while (odabir != 0) {
            System.out.print("\n\nOdaberi korak izgradnje nekretnine(1) Unos nove nekretnine (2)Projektiranje (3)Izgradnja (4)Provjera dokumentacije (0)Izlaz: ");
            odabir = sc.nextInt();
            sc.nextLine();
            switch(odabir) {
                case 1:
                    String tipNekretnine;
                    String nazivTemp;
                    Double budgetTemp;
                    String dozvoleMerged;
                    List<String> listaDozvolaTemp = new ArrayList<>();
                    System.out.println("Unesi tip: (Kuća/Zgrada)");
                    tipNekretnine = sc.nextLine();
                    System.out.println("Unesi naziv: ");
                    nazivTemp = sc.nextLine();
                    System.out.println("Unesi budget: ");
                    budgetTemp = sc.nextDouble();
                    sc.nextLine();
                    while (listaDozvolaTemp.isEmpty()) {
                        System.out.println("Unesi sve potrebne dozvole:");
                        dozvoleMerged = sc.nextLine();

                        try {
                            if (dozvoleMerged == null || dozvoleMerged.trim().isEmpty()) {
                                throw new NevažećaDozvolaException("Dozvole su obavezne");
                            }

                            String[] dozvole = dozvoleMerged.trim().split("\\s+");
                            listaDozvolaTemp.addAll(Arrays.asList(dozvole));

                        } catch (GrađevinaException error) {
                            System.out.println(RED+"GREŠKA: " + error.getMessage()+RESET);
                        }
                    }

                    IzgradnjaNekretnine novaNekretnina;
                    if (tipNekretnine.equals("Kuća")) {
                        novaNekretnina = new IzgradnjaKuce(nazivTemp, budgetTemp, listaDozvolaTemp);
                        listaNekretnina.add(novaNekretnina);
                    }
                    else if(tipNekretnine.equals("Zgrada")) {
                        novaNekretnina = new IzgradnjaZgrade(nazivTemp, budgetTemp, listaDozvolaTemp);
                        listaNekretnina.add(novaNekretnina);
                    }
                    else {
                        System.out.println("Nepoznat tip nekretnine!");
                        break;
                    }
                    break;
                case 2:
                    for(IzgradnjaNekretnine n : listaNekretnina){
                        n.projektiranje();
                        System.out.println("=======================");
                    }
                    break;
                case 3:
                    for(IzgradnjaNekretnine n : listaNekretnina){
                        System.out.println("Naziv nekretnine: "+ n.naziv);
                        n.koraciIzgradnje();
                        System.out.println("=======================");
                    }
                    break;
                case 4:
                    for(IzgradnjaNekretnine n : listaNekretnina){
                        System.out.println("Naziv nekretnine: "+ n.naziv);
                        n.pribavljanjeDozvola();
                        System.out.println("=======================");
                    }
                    break;
            }

    }
    }
}