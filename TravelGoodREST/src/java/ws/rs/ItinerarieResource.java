package ws.rs;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("Itinerarie")
public class ItinerarieResource {
    
    @GET
    @Produces("application/json")
    public Itinerary createIntinerarie() {
        Itinerary newIntinerary = new Itinerary(5);
        return newIntinerary;
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
