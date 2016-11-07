package airline.ws;

import java.util.Date;
import java.util.Hashtable;
import java.util.List;

public class AirlineModel {
    Hashtable<Integer, Flight> flightDB = new Hashtable<Integer, Flight>();
    
    public List<Flight> getFlights(String destination, String startOfTravel, Date departureDate) {
        // Loop over the hashtable of flights and create a list from the result
        // return list when loop is over
    }
    
}
