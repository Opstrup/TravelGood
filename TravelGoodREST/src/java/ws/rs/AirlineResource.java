package ws.rs;

import airline.ws.FlightInformation;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;


@Path("airline")  // Defines the path to the base URL.
public class AirlineResource {
    
    private static List<FlightInformation> searchedFlights = new ArrayList();

    @GET
    @Path("/{startAirport}/{endAirport}")
    @Produces("application/json, application/xml")    
    public List<FlightInformation> getFlightsForItinerary (
                @PathParam("startAirport") String startAirport,
                @PathParam("endAirport") String endAirport,
                @QueryParam("departureDate") String departureDate) throws DatatypeConfigurationException {
        
        XMLGregorianCalendar depDate = DateParser.parse(departureDate);
        List<FlightInformation> flights = getFlights(startAirport, endAirport, depDate);
        for(FlightInformation flightInfo : flights){
            searchedFlights.add(flightInfo);
        }
        return flights;
    }
    
    public static List<FlightInformation> getSearchedFlights(){
        return searchedFlights;
    }

    private static java.util.List<airline.ws.FlightInformation> getFlights(java.lang.String startAirport, java.lang.String endAirport, java.lang.Object startDate) {
        airline.ws.AirlineService service = new airline.ws.AirlineService();
        airline.ws.AirlineController port = service.getAirlineControllerPort();
        return port.getFlights(startAirport, endAirport, startDate);
    }

    
}
