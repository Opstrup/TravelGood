package ws.rs;

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

    @GET
    @Produces("application/json")
    public String getFlights(){
        return "This airlin.";
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
    
}
