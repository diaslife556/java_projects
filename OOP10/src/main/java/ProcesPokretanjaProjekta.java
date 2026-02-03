import java.time.LocalDate;

public class ProcesPokretanjaProjekta extends ProjektniProces{
    public ProcesPokretanjaProjekta(String voditeljProjekta, Projekt povezaniProjekt) {
        super(
                povezaniProjekt.getId(),
                povezaniProjekt.getDatum(),
                voditeljProjekta,
                povezaniProjekt
        );
    }

    public void ispišiDatum() {
        System.out.println("Datum početka projekta je: " + povezaniProjekt.getDatum());
    }

    @Override
    public void dajPopisKoraka() {
        System.out.println("Potrebni koraci:");
        for (Zadatak z : povezaniProjekt.getZadaciProjekta()) {
            System.out.println("- " + z.getIme());
        }
    }
    @Override
    public boolean provjeriPotrebnuDokumentaciju() throws PrekoračenBudžetException{
        if (povezaniProjekt.getTrošak() > povezaniProjekt.getBudget()) {
            throw new PrekoračenBudžetException(
                    "Trošak (" + povezaniProjekt.getTrošak() +
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

}
