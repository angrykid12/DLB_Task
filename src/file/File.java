package file;

import buses.Bus;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;

public class File {

    public static List<Bus> getRidesFromFile(String inputFileName)  {
        List<Bus> buses = new LinkedList<>();
        try {

            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    new FileInputStream(inputFileName), StandardCharsets.UTF_8));
            String line;
            while ((line = reader.readLine()) != null) {
                buses.add(Bus.stringToRide(line));
            }
            reader.close();


        } catch (IllegalArgumentException |IOException e) {
            e.printStackTrace();
        }

        return buses;
    }


    public static void writeRidesToFile(List<Bus> buses, String outputFileName) {
        try {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(outputFileName), StandardCharsets.UTF_8));

            for (Bus bus : buses) {
                if (bus.getCompanyName().equals("Posh")) {
                    String line = bus.toString();
                    writer.write( line + "\n");
                }
            }
            writer.write("\n");
            for (Bus bus : buses) {
                if (bus.getCompanyName().equals("Grotty")) {
                    String line = bus.toString();
                    writer.write(line + "\n");
                }
            }
            writer.close();
        } catch (IllegalArgumentException|IOException e ) {
            e.printStackTrace();
        }

    }

}
