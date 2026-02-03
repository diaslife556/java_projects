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
        System.out.println("Predviđeni datum završetka je "+datumZavršetka);
    }
    public void dajPopisKoraka(){
        System.out.println("Potrebni koraci:");
        for (Zadatak z : povezaniProjekt.getZadaciProjekta()) {
            System.out.println("- " + z.getIme());
        }
    };
    @Override
    public boolean provjeriPotrebnuDokumentaciju() throws PrekoračenBudžetException {
        if (povezaniProjekt.getTrošak() > povezaniProjekt.getBudget()) {
            throw new PrekoračenBudžetException(
                    "GREŠKA: Trošak (" + povezaniProjekt.getTrošak() +
                            ") premašuje budžet (" + povezaniProjekt.getBudget() + ")"
            );
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
