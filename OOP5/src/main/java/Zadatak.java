import java.math.BigDecimal;
public class Zadatak {
    public static Integer staticId = 0;
    public Integer id;
    public String name;
    public Integer procijenjenoTrajanje;
    public Double budget;

    public Zadatak( String name, Integer procijenjenjenoTrajanje, Double budget) {
        staticId++;
        this.id = staticId;
        this.name = name;
        this.procijenjenoTrajanje =  procijenjenjenoTrajanje;
        this.budget = budget;
    }

    public String toString() {
        return "Zadatak{" +
                "id=" + id +
                ", name='" + name + '\'' + ", Procijenjeno Trajanje= '" + procijenjenoTrajanje + " dana"+'\'' +
                ", budžet='" + budget +
                '}';
    }
}