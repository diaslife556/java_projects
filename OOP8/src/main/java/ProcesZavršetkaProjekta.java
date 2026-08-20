import java.time.LocalDate;

public class ProcesZavršetkaProjekta extends ProjektniProces{
    public LocalDate datumZavršetka;
    public ProcesZavršetkaProjekta(String voditeljProjekta, Projekt povezaniProjekt) {
        super(
                povezaniProjekt.getId(),
                povezaniProjekt.getDatum(),
                voditeljProjekta,
                povezaniProjekt
        );
    }
    @Override
    public void ispišiDatum() {
        datumZavršetka = povezaniProjekt.getDatum();
        datumZavršetka = datumZavršetka.plusDays(povezaniProjekt.getZadaciProjekta().get(0).getProcijenjenoTrajanje());
        System.out.println("Datum Završetka je "+datumZavršetka);
    }
    public void dajPopisKoraka(){
        System.out.println("Potrebni koraci:");
        for (Zadatak z : povezaniProjekt.getZadaciProjekta()) {
            System.out.println("- " + z.getIme());
        }
    };
    @Override
    public boolean provjeriPotrebnuDokumentaciju() {
        if (povezaniProjekt.getTrošak() > povezaniProjekt.getBudget()) {
            System.out.println("Trošak projekta je veći od budžeta!");
            return false;
        }
        for (Zadatak z : povezaniProjekt.getZadaciProjekta()) {
            if (z.getStatus().equals("NijePočeo")) {
                System.out.println("Zadatak '" + z.getIme() + "' još nije započet!");
                return false;
            }
        }
        return true;
    }
    public void završnaInspekcija(){

    }



}
