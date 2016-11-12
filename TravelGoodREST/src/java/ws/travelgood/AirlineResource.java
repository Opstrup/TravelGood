package ws.travelgood;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;

/**
 * REST Web Service
 * @author Vitali
 */
@Path("airline")
public class AirlineResource {
    
    @Context
    private UriInfo context;
    
     /**
     * Retrieves representation of an instance of ws.pr.AirlineResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getFlights(){
        return "Airline data of your request are...";
    }
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public boolean bookFlight () {
        //Booking number..
        return true;
    }
    
    /**
     * PUT method for updating or creating an instance of AirlineResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Produces(MediaType.TEXT_PLAIN)
    public String cancleFlight () {
        //Update booking.
        return "Returning 50% of the booking by ID.";
    }
    
}
