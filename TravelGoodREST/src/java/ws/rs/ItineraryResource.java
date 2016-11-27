package ws.rs;

import airline.ws.Exception_Exception;
import airline.ws.FlightInformation;
import hotel.ws.CreditCardFaultMessage;
import hotel.ws.HotelInformation;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import ws.rs.HotelResource;

@Path("itineraries")
public class ItineraryResource {
    /**
     * Simple list for storing the itineraries
     */
    public static List<Itinerary> itineraryDb = new ArrayList<>();
    
    /**
     * @return a list of all user's itineraries
     */
    @GET
    @Produces("application/json,application/xml")
    public List<Itinerary> getItineraries() {
        return itineraryDb;
    }
    
    /**
     * Adds an itinerary to the database and returns the newly created itinerary
     * @return 
     */
    @PUT
    @Produces("application/json,application/xml")
    public Itinerary createItinerary(Itinerary ignored) {
        int itineraryId = itineraryDb.size();
        Itinerary newItinerary = new Itinerary(String.valueOf(itineraryId));
        itineraryDb.add(newItinerary);
        
        return newItinerary;
    }
    
    @DELETE
    @Path("/{itineraryId}")
    @Produces("application/json")
    public List<Itinerary> cancelPlannedItinerary(@PathParam("itineraryId") String id){
        Itinerary itineraryToBeRemoved = null;
        for(Itinerary itinerary: itineraryDb){
            if(itinerary.ID.equals(id)){
               itineraryToBeRemoved = itinerary;
            }
        }
        itineraryDb.remove(itineraryToBeRemoved);
        return itineraryDb;
    }
    
    /**
     * @param id of the itinerary
     * @return the specified itinerary
     */
    @GET 
    @Path("/{itineraryId}")
    @Produces("application/json")
    public Itinerary getItinerary(@PathParam("itineraryId") String id) {
        Itinerary newItinerary = this.itineraryDb.get(Integer.parseInt(id));
        
        /*
         * TODO:
         * This could fail if no itinerary with the id is found
         * return 404 error code.
         */
        
        return newItinerary;
    }
      
    @PUT
    @Path("/{itineraryId}/hotels/{bookingNumber}")
    @Produces("application/json")
    public Itinerary addHotelToItinerary(
            Itinerary ignored,
            @PathParam("itineraryId") String itineraryID,
            @PathParam("bookingNumber") String bookingNumber){
        
        for(Itinerary itinerary : itineraryDb){
            if(itinerary.ID.equals(itineraryID)){
                for(HotelInformation hotelInfo: HotelResource.getSearchedHotels())
                    if(hotelInfo.getBookingNumber() == Integer.parseInt(bookingNumber)){
                        itinerary.hotels.add(hotelInfo);
                        return itinerary;
                    }
            }
        }
        //no itineraryID: 404
        return null;
               
    }
    
    @PUT
    @Path("/{itineraryId}/flights/{bookingNumber}")
    @Produces("application/json")
    public Itinerary addFlightToItinerary(
            @PathParam ("itineraryId") String itineraryID,
            @PathParam ("bookingNumber") String bookingNumber){
        
        for(Itinerary itinerary:itineraryDb){
            if(itinerary.ID.equals(itineraryID)){
                for(FlightInformation flightInfo: AirlineResource.getSearchedFlights())
                    if(flightInfo.getBookingNumber() == Integer.parseInt(bookingNumber)){
                        itinerary.flights.add(flightInfo);
                        return itinerary;
                    }
            }
        }
        return null;
    }    
    
    @PUT
    @Path("/reset")
    public Response resetItineraryDB(String ignored){
        itineraryDb = new ArrayList<>();
        return Response.ok().build();
    }
    
}
    
