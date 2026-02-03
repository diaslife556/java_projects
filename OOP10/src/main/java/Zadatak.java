import java.io.Serializable;
import java.math.BigDecimal;
public class Zadatak implements Serializable {
    private static Integer staticId = 0;
    private Integer id;
    private String ime;
    private Integer procijenjenoTrajanje;
    private Double budget;
    private String status;

    public Zadatak( String ime, Integer procijenjenjenoTrajanje, Double budget, String status) {
        staticId++;
        this.id = staticId;
        this.ime = ime;
        this.procijenjenoTrajanje =  procijenjenjenoTrajanje;
        this.budget = budget;
        setStatus(status);
    }

    public String toString() {
        return "Zadatak{" +
                "id=" + id +
                ", name='" + ime + '\'' + ", Procijenjeno Trajanje= '" + procijenjenoTrajanje + " dana"+'\'' +
                ", budžet='" + budget +
                '}';
    }

    public void setStatus(String status) {
        if (status.matches("NijePočeo|UTijeku|Završen")) {
        }else {
            throw new IllegalArgumentException("Netočan unos statusa za: "+ime);
        }
        this.status = status;
    }

    public Integer getId() {
        return id;
    }
    public String getIme() {
        return ime;
    }
    public Integer getProcijenjenoTrajanje() {
        return procijenjenoTrajanje;
    }
    public String getStatus() {return status;}
    public Double getBudget() {return budget;}
}