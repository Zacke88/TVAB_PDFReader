import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        PDFManager pdf = new PDFManager();
        try {
            pdf.readPDF();
            pdf.saveToExcel();
        } catch (FileNotFoundException e) {
            System.out.println("COULD NOT WRITE TO FILE");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("COULD NOT READ PDF");
            e.printStackTrace();
        }
    }
}
