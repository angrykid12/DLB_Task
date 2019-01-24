package ride;

import buses.Bus;

import java.util.*;

public class Ride {

    public static List<Bus> efficientService(List<Bus> buses) {
        List<Bus> efficientBuses = removeIneffectiveService(buses);
        efficientBuses.sort(Bus.COMPARE_BY_DEPTIME);
        return efficientBuses;
    }


    private static List<Bus> removeIneffectiveService(List<Bus> buses) {
        buses.removeIf(ride -> ride.getDifferenceInMinutes() > 60);
        return removeSlowRides(buses);
    }

    private static List<Bus> removeSlowRides(List<Bus> buses) {
        List<Bus> newBuses = new LinkedList<>(buses);
        for(Bus bus : buses){
            for(Bus compareBus : buses){
                if(bus == compareBus){
                    continue;
                }
                if(bus.inefficient(compareBus)) {
                    newBuses.remove(bus);
                }
            }
        }
        return newBuses;
    }
}
