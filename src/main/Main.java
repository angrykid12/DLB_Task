package main;

import buses.Bus;
import file.File;
import ride.Ride;
import java.io.IOException;
import java.util.List;


public class Main {
    public static void main(String args[]) throws IOException {

        String inputFileName = args[0];
        List<Bus> current_list = File.getRidesFromFile(inputFileName);
        List<Bus> final_list = Ride.efficientService(current_list);
        File.writeRidesToFile(final_list, "output.txt");

    }
}
