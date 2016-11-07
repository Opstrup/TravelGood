package airline.ws;

import java.util.*;

import org.junit.Test;
import static org.junit.Assert.*;

public class AirlineTests extends airline.ws.AirlineModel{

    public AirlineTests(){
        this.flightInformationDB = new ArrayList<airline.ws.FlightInformation>();

        Date departureDate = new GregorianCalendar(2014, Calendar.FEBRUARY, 11).getTime();
        Date arrivalDate = (Date) departureDate.clone();

        airline.ws.Flight testFlight = new airline.ws.Flight("Paris", "Copenhagen", departureDate, arrivalDate, "Ryanair", 10);
        airline.ws.FlightInformation testFlightInfo = new airline.ws.FlightInformation(1, 100, "Lameduck", testFlight);
        this.flightInformationDB.add(testFlightInfo);
    }


    @Test
    public void getFlightsTest() {

        AirlineTests model = new AirlineTests();

        Date departureDate = new GregorianCalendar(2014, Calendar.FEBRUARY, 11).getTime();
        Date arrivalDate = (Date) departureDate.clone();

        airline.ws.Flight expectedFlight = new airline.ws.Flight("Paris", "Copenhagen", departureDate, arrivalDate, "Ryanair", 10);
        airline.ws.FlightInformation expectedFlightInfo = new airline.ws.FlightInformation(1, 100, "Lameduck", expectedFlight);

        List<airline.ws.FlightInformation> actualResult = model.getFlights("Paris", "Copenhagen", departureDate);

        assert actualResult.size() == 1;

        airline.ws.FlightInformation actualFlightInfo = actualResult.get(0);

        assertEquals(expectedFlightInfo.getBookingNumber(), actualFlightInfo.getBookingNumber());

    }
}
