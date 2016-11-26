package ws.rs;

import hotel.ws.HotelInformation;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import ws.rs.HotelResource;

@Path("itinerary")
public class ItineraryResource {
    /**
     * Simple list for storing the itineraries
     */
    public static List<Itinerary> itineraryDb = new ArrayList<>();
    
    @PUT
    @Produces("application/json")
    public Itinerary createItinerary() {
        int itineraryId = itineraryDb.size();
        Itinerary newItinerary = new Itinerary(String.valueOf(itineraryId));
        itineraryDb.add(newItinerary);
        
        return newItinerary;
    }
    
    @GET
    @Produces("application/json")
    public List<Itinerary> getItineraries() {
        return itineraryDb;
    }
    
    @GET 
    @Path("/{itineraryId}")
    @Produces("application/json")
    public Itinerary getItineraryStatus(@PathParam("itineraryId") String id) {
        Itinerary newItinerary = this.itineraryDb.get(Integer.parseInt(id));
        
        /*
         * TODO:
         * This could fail if no itinerary with the id is found
         * return 404 error code.
         */
        
        return newItinerary;
    }
      
    @POST
    @Path("/{itineraryID}/hotel/{bookingNumber}")
    @Produces("application/json")
    public Itinerary addHotelToItinerary(
            @PathParam("itineraryId") String itineraryID,
            @PathParam("bookingNumber") String bookingNumber){
        
        for(Itinerary itinerary : itineraryDb)
            if(itinerary.ID.equals(itineraryID)){
                for(HotelInformation hotelInfo : HotelResource.getSearchedHotels())
                    if(hotelInfo.getBookingNumber() == Integer.parseInt(bookingNumber)){
                        itinerary.hotels.add(hotelInfo);
                        return itinerary;
                    }
                //No hotelInfoID 404
                return null;
            }
            
        //no itineraryID: 404
        return null;
               
    }
    
}
    
