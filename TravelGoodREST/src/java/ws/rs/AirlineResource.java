package ws.rs;

import airline.ws.FlightInformation;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * @author Vitali
 */
@Path("lameDuck")
public class AirlineResource {

//    @GET
//    @Produces("application/json")    
//    public List<FlightInformation> getFlights () {
//        String startAirport = "copenhagen";
//        String endAirport = "rome";
//        Date departureDate = new GregorianCalendar(2016, 8, 10).getTime();
//        Date startDate = new GregorianCalendar(2016, 8, 10).getTime();
//        return getFlights_1(startAirport, endAirport, departureDate);
//    }
    
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

    private static java.util.List<airline.ws.FlightInformation> getFlights_1(java.lang.String startAirport, java.lang.String endAirport, javax.xml.datatype.XMLGregorianCalendar startDate) {
        airline.ws.AirlineService service = new airline.ws.AirlineService();
        airline.ws.AirlineController port = service.getAirlineControllerPort();
        return port.getFlights(startAirport, endAirport, startDate);
    }
}
