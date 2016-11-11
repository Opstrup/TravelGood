package airline.ws;

import java.util.*;

import org.junit.Test;
import static org.junit.Assert.*;

public class AirlineTests extends airline.ws.AirlineModel{

    public AirlineTests(){
        this.flightsDB = new ArrayList<>();

        Date departureDate = new GregorianCalendar(2014, Calendar.FEBRUARY, 11).getTime();
        Date arrivalDate = (Date) departureDate.clone();

        Flight testFlight = new Flight(101, "Paris", "Copenhagen", departureDate, arrivalDate, "Ryanair", 10);
        this.flightsDB.add(testFlight);
    }


    @Test
    public void getFlightsTest() {

        AirlineTests model = new AirlineTests();

        Date departureDate = new GregorianCalendar(2014, Calendar.FEBRUARY, 11).getTime();
        Date arrivalDate = (Date) departureDate.clone();
        Flight expectedFlight = new Flight(101, "Paris", "Copenhagen", departureDate, arrivalDate, "Ryanair", 10);

        List<FlightInformation> actualResult = model.getFlights("Paris", "Copenhagen", departureDate);

        assert actualResult.size() == 1;

        Flight actualFlight = actualResult.get(0).getFlight();

        assertEquals(expectedFlight.getFlightID(), actualFlight.getFlightID());

    }
}
