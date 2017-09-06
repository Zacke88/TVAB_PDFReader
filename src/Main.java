import java.io.IOException;

public class Main {

    public static void main(String[] args) {


        PDFManager pdf = new PDFManager();
        try {
            pdf.readPDF();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //System.out.println("Hello World!");
    }
}
