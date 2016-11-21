package ws.rs;

import airline.ws.FlightInformation;
import java.util.GregorianCalendar;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;


@Path("airline")
public class AirlineResource {

    @GET
    @Produces("application/json")    
    public List<FlightInformation> getFlightsForItinerary () throws DatatypeConfigurationException {
        String startAirport = "copenhagen";
        String endAirport = "rome";
        XMLGregorianCalendar departureDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar(2016, 8, 10));
        List<FlightInformation> flights = getFlights(startAirport, endAirport, departureDate);

        return flights;
    }
    
    @POST
    @Produces("text/plain")
    public boolean bookFlight () {
        return true;
    }
    
    @PUT
    @Produces("text/plain")
    public String cancleFlight () {
        return "Returning 50% of the booking by ID.";
    }    

    private static java.util.List<airline.ws.FlightInformation> getFlights(java.lang.String startAirport, java.lang.String endAirport, java.lang.Object startDate) {
        airline.ws.AirlineService service = new airline.ws.AirlineService();
        airline.ws.AirlineController port = service.getAirlineControllerPort();
        return port.getFlights(startAirport, endAirport, startDate);
    }
}
