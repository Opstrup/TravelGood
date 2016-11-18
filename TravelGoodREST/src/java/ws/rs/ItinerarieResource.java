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
    @Produces(MediaType.APPLICATION_XML)
    public Response createIntinerarie() {
        Itinerary newIntinerary = new Itinerary();
        return Response.ok(newIntinerary).build();
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
