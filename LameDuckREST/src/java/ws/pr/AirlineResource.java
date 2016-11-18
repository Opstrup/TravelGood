package ws.pr;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

/**
 * @author Vitali
 */
@Path("airline")
public class AirlineResource {
    
    @Context
    private UriInfo context;
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getFlights(){
        return "Airline data of your request are...";
    }
    @POST
    public boolean bookFlight () {
        //Booking number..
        return true;
    }
    @GET
    public String cancleFlight () {
        return "Returning 50% of the booking by ID.";
    }
    
}
