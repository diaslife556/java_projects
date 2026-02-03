import java.io.*;
import java.util.List;
public class ImportExport {
    public static void Export(
            List<Projekt> projekti,
            List<Radnik> radnici,
            List<Dobavljac> dobavljaci) {

        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream("podaci.dat"))) {

            oos.writeObject(projekti);
            oos.writeObject(radnici);
            oos.writeObject(dobavljaci);

            System.out.println("Podaci uspješno spremljeni.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @SuppressWarnings("unchecked")
    public static Object[] Import() {

        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream("podaci.dat"))) {

            List<Projekt> projekti = (List<Projekt>) ois.readObject();
            List<Radnik> radnici = (List<Radnik>) ois.readObject();
            List<Dobavljac> dobavljaci = (List<Dobavljac>) ois.readObject();

            System.out.println("Podaci uspješno učitani.");

            return new Object[]{projekti, radnici, dobavljaci};

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Nema spremljenih podataka, kreiram prazne liste.");
            return new Object[]{
                    new java.util.ArrayList<Projekt>(),
                    new java.util.ArrayList<Radnik>(),
                    new java.util.ArrayList<Dobavljac>()
            };
        }
    }
}
