package buses;

import java.time.LocalTime;
import java.util.Comparator;


public class Bus {

    private String companyName;

    private LocalTime departureTime;

    private LocalTime arrivalTime;

    public Bus(){

    }

    public Bus(String companyName, LocalTime departureTime, LocalTime arrivalTime) {
        this.companyName = companyName;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }

    public String getCompanyName() {
        return companyName;
    }

    private LocalTime getDepartureTime() {
        return departureTime;
    }

    private LocalTime getArrivalTime() {
        return arrivalTime;
    }

    private int getDepartureTimeInMinutes() {
        return departureTime.getHour() * 60 + departureTime.getMinute();
    }


    private int getArrivalTimeInMinutes() {
        return arrivalTime.getHour() * 60 + arrivalTime.getMinute();
    }

    public int getDifferenceInMinutes() {
        if (departureTime.getHour() <= arrivalTime.getHour()) {
            return getArrivalTimeInMinutes() - getDepartureTimeInMinutes();
        } else {
            return getArrivalTimeInMinutes() + 24 * 60 - getDepartureTimeInMinutes();
        }
    }

    public static final Comparator<Bus> COMPARE_BY_DEPTIME = Comparator.comparingInt(Bus::getDepartureTimeInMinutes);

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;

        if (obj == null) return false;

        if (getClass() != obj.getClass()) return false;

        Bus bus = (Bus) obj;

        return companyName.equals(bus.companyName) && departureTime == bus.departureTime && arrivalTime == bus.arrivalTime  && (companyName.equals(bus.companyName) ||
                companyName.equals(bus.getCompanyName())) && (departureTime == bus.departureTime ||
                departureTime != null && departureTime.equals(bus.getDepartureTime())) && (arrivalTime == bus.arrivalTime ||
                arrivalTime != null && arrivalTime.equals(bus.getArrivalTime()));

    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;

        result = prime * result + ((companyName == null) ? 0 : companyName.hashCode());
        result = prime * result + ((departureTime == null) ? 0 : departureTime.hashCode());
        result = prime * result + ((arrivalTime == null) ? 0 : arrivalTime.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return (companyName + ' ' + departureTime + ' ' + arrivalTime);
    }



    public static Bus stringToRide(String str) {
        String[] substr = str.split(" ");
        String companyName = substr[0];
        LocalTime departureTime = LocalTime.parse(substr[1]);
        LocalTime arrivalTime = LocalTime.parse(substr[2]);
        return new Bus(companyName, departureTime, arrivalTime);
    }



    public boolean inefficient(Bus bus) {
        boolean flag = (departureTime.getHour() <= arrivalTime.getHour());
        boolean status = (bus.getDepartureTime().getHour() <= bus.getArrivalTime().getHour());

        if ((flag && status) || (!flag && !status)) {
            if(getDepartureTimeInMinutes() == bus.getDepartureTimeInMinutes() &&
                    getArrivalTimeInMinutes() > bus.getArrivalTimeInMinutes())
                return true;
            if(getDepartureTimeInMinutes() < bus.getDepartureTimeInMinutes() &&
                    getArrivalTimeInMinutes() == bus.getArrivalTimeInMinutes())
                return true;
            if(getDepartureTimeInMinutes() < bus.getDepartureTimeInMinutes() &&
                    getArrivalTimeInMinutes() > bus.getArrivalTimeInMinutes())
                return true;
        }
        if(!flag && status){
            if(getDepartureTimeInMinutes() == bus.getDepartureTimeInMinutes() &&
                    getArrivalTimeInMinutes() < bus.getArrivalTimeInMinutes())
                return true;
            if(getDepartureTimeInMinutes() < bus.getDepartureTimeInMinutes() &&
                    getArrivalTimeInMinutes() < bus.getArrivalTimeInMinutes())
                return true;
        }
        return getDepartureTimeInMinutes() == bus.getDepartureTimeInMinutes() &&
                getArrivalTimeInMinutes() == bus.getArrivalTimeInMinutes() &&
                companyName.equals("Grotty");
    }
}