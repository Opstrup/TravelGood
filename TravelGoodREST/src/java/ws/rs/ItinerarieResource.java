package ws.rs;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * @author Vitali
 */
@Path("Itinerarie")
public class ItinerarieResource {
    
    @GET
    @Produces("text/plain")
    public String createIntinerarie() {
        return "This is your itinerary 1.";
    }
    
    @PUT
    @Produces("text/plain")
    public String bookIntinerarie() {
        return "Itinerary 1 is now bokked.";
    }
    
    @PUT
    @Produces("text/plain")
        public String cancelIntinerarie() {
        return "You get 50% discount of cancelation";
    }
}
