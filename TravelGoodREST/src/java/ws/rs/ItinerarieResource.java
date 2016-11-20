package ws.rs;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author Vitali
 */
@Path("Itinerarie")
public class ItinerarieResource {
    
    @GET
//    @Produces(MediaType.APPLICATION_JSON)
    @Produces("application/json")
    public Itinerary createIntinerarie() {
//        Integer id = 0;
        Itinerary newIntinerary = new Itinerary(5);
//        newIntinerary.SetID(id);
        return newIntinerary;
//        return newIntinerary;
    }

//    @PUT
//    @Produces("text/plain")
//    public String bookIntinerarie() {
//        return "Itinerary 1 is now bokked.";
//    }
//    
//    @PUT
//    @Produces("text/plain")
//        public String cancelIntinerarie() {
//        return "You get 50% discount of cancelation";
//    }
}
