import java.io.IOException;

public class Main {

    public static void main(String[] args) {


        PDFManager pdf = new PDFManager();
        try {
            pdf.readPDF();
        } catch (IOException e) {
            System.out.println("COULD NOT READ PDF");
            e.printStackTrace();
        }
        //System.out.println("Hello World!");
    }
}
