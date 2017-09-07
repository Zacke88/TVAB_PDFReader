/**
 * Created by zakri on 2017-09-06.
 */

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;


public class PDFManager {

    String path = "C:/Users/zakri/Desktop/pdf/";
    String filename = "AvfallAllaP3";
    String pdfExtension =".pdf";
    String period = "";
    ArrayList array = new ArrayList<String>();


    public void readPDF() throws IOException {

        File pdf = new File(path+filename+pdfExtension);
        String string = "";
        StringBuffer dataString = new StringBuffer();
        Boolean readData = false;
        Boolean dataLine = false;
        PDFTextStripper stripper = new PDFTextStripper();
        BufferedReader reader = new BufferedReader(
                new StringReader(stripper.getText(PDDocument.load(pdf))));

        try {
            while ((string = reader.readLine()) != null) {

                if(dataLine && string.contains("1")) {
                    readData = true;
                }
                else {
                    dataLine = false;
                }

                if(string.contains("Perioder över gräns")) {
                    readData = false;
                    string = string.replaceAll("[^\\d.]", " ");
                    period = string.trim();
                    System.out.println(period);
                }

                if(readData) {
                    dataString.append(string);
                    dataString.append(" ");
                }

                if(string.contains("30 min")) {
                    dataLine = true;
                }
            }
            System.out.println(dataString);

        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void saveToExcel() {

    }
}
