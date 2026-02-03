import java.time.LocalDate;

public abstract class ProjektniProces {

    protected Integer idProcesa;
    protected LocalDate datum;
    protected String voditeljProjekta;
    protected Projekt povezaniProjekt;

    public ProjektniProces(Integer idProcesa, LocalDate datum,
                           String voditeljProjekta, Projekt povezaniProjekt) {
        this.idProcesa = idProcesa;
        this.datum = datum;
        this.voditeljProjekta = voditeljProjekta;
        this.povezaniProjekt = povezaniProjekt;
    }

    public abstract void dajPopisKoraka();
    public abstract boolean provjeriPotrebnuDokumentaciju() throws PrekoračenBudžetException;
    public abstract void ispišiDatum();
}

