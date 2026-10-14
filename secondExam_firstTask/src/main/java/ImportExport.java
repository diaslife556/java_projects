import java.io.*;
public class ImportExport {
    private static final String FILE_NAME = "jelovnici.ser";

    public static void Export(Jelovnik jelovnik) {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(FILE_NAME))) {

            oos.writeObject(jelovnik);
            System.out.println("Podaci uspješno spremljeni.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public static Jelovnik importJelovnik() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return null;
        }
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(file))) {

            return (Jelovnik) ois.readObject();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

}