package ws.rs;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;

@Path("airline")
public class AirlineResource {

    @GET
    @Produces("application/json")
    public String getFlights(){
        return "Airline data of your request are...";
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
