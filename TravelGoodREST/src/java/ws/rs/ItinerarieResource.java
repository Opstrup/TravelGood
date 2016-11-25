package ws.rs;

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("itinerarie")
public class ItinerarieResource {
    
    /**
     * Simple list for storing the itineraries
     */
    public static List<Itinerary> itineraryDb = new ArrayList<>();
    
    @PUT
    @Produces("application/json")
    public Itinerary createIntinerarie() {
        int intineraryId = itineraryDb.size();
        Itinerary newIntinerary = new Itinerary(intineraryId);
        itineraryDb.add(newIntinerary);
        return newIntinerary;
    }
    
    @GET
    @Produces("application/json")
    public List<Itinerary> getItineraies() {
        return itineraryDb;
    }
    
    @GET 
    @Path("/{itineraryId}")
    @Produces("application/json")
    public Itinerary getItineraryStatus(@PathParam("itineraryId") String id) {
        Itinerary newIntinerary = this.itineraryDb.get(Integer.parseInt(id));
        
        /*
         * TODO:
         * This could fail if no itinerary with the id is found
         * return 404 error code.
         */
        
        return newIntinerary;
    }
}
