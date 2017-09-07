/**
 * Created by zakri on 2017-09-06.
 */

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class PDFManager {

    String path = "C:/Users/zakri/Desktop/pdf/";
    String filename = "AvfallAllaP3";
    String pdfExtension =".pdf";
    String textFile = "rapport.txt";
    String period = "";
    int spaces = 20;
    StringBuffer dataString = new StringBuffer();
    ArrayList array = new ArrayList<String>();


    public void readPDF() throws IOException {

        File pdf = new File(path+filename+pdfExtension);
        String string;
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

    public void saveToExcel() throws IOException {

        //formatDataString();

        PrintWriter out = new PrintWriter(new FileWriter(textFile, true));
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date());
        out.println(date);
        out.println("Data:");
        out.println(dataString);
        out.println("Perioder över gräns:");
        out.println(period);
        out.println("");
        out.close();

    }

    public void formatDataString() {
        String string = String.valueOf(dataString);
        String parsedStr = string.replaceAll("(.{44})", "$1\n");
        System.out.println(parsedStr);
    }
}
