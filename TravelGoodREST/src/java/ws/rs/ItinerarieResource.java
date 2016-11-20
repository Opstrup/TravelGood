package ws.rs;

import java.util.Hashtable;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("Itinerarie")
public class ItinerarieResource {
    
    /**
     * Simpel hashmap for storing the itineraries
     */
    public Hashtable<Integer, Itinerary> itineraryDb = new Hashtable<>();
    
    @PUT
    @Produces("application/json")
    public Itinerary createIntinerarie() {
        Itinerary newIntinerary = new Itinerary(5);
        return newIntinerary;
    }
    
    @GET
    @Produces("application/json")
    public Hashtable getItineraies() {
        return this.itineraryDb;
    }
    
    @GET @Path("/{itineraryId}")
    @Produces("application/json")
    public Itinerary getItineraryStatus(@PathParam("itineraryId") String id) {
        Itinerary fakeIntinerary = new Itinerary(5);
        fakeIntinerary.status = Itinerary.BookingStatus.BOOKED;
        this.itineraryDb.put(1, fakeIntinerary);
        
        Itinerary newIntinerary = this.itineraryDb.get(Integer.parseInt(id));
        
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
