import java.io.Serializable;

public abstract class GrađevinskiResurs implements Serializable {
    private Integer id;
    private String ime;
    private Boolean status;
    private String lokacija;

    private String VelikoSlovo(String s) {
        if (s == null || s.isEmpty()) return s;
        return s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
    }

    public GrađevinskiResurs(Integer id, String ime, Boolean status, String lokacija) {
        this.id = id;
        this.ime = VelikoSlovo(ime);
        this.status = status;
        this.lokacija = lokacija;
    }

    public abstract Double izrTrosak();

    public Integer getId() {
        return id;
    }

    public String getIme() {
        return ime;
    }

    public Boolean getStatus() {
        return status;
    }

    public abstract String toString();
}
