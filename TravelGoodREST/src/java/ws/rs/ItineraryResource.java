package ws.rs;

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
import ws.rs.HotelResource;

@Path("itinerary")
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
    public Itinerary createItinerary() {
        int itineraryId = itineraryDb.size();
        Itinerary newItinerary = new Itinerary(String.valueOf(itineraryId));
        itineraryDb.add(newItinerary);
        
        return newItinerary;
    }
    
    @POST
    @Path("/{itineraryId}/book")
    @Produces("application/json")
    public Itinerary bookItinerary(@PathParam("itineraryId") String id, @QueryParam ("name") String name, @QueryParam("number") String number,@QueryParam("expMonth") int expMonth,@QueryParam("expYear") int expYear) throws hotel.ws.CreditCardFaultMessage, airline.ws.CreditCardFaultMessage{
        hotel.ws.CreditCardInfoType ccInfo = new hotel.ws.CreditCardInfoType();
        hotel.ws.CreditCardInfoType.ExpirationDate expDate = new hotel.ws.CreditCardInfoType.ExpirationDate();
        
        expDate.setMonth(expMonth);
        expDate.setYear(expYear);
        
        ccInfo.setExpirationDate(expDate);
        ccInfo.setName(name);
        ccInfo.setNumber(number);
        
        
        dk.dtu.imm.fastmoney.types.CreditCardInfoType ccInfoFast = new dk.dtu.imm.fastmoney.types.CreditCardInfoType();
        dk.dtu.imm.fastmoney.types.CreditCardInfoType.ExpirationDate expDateFast = new dk.dtu.imm.fastmoney.types.CreditCardInfoType.ExpirationDate();
        
        expDateFast.setMonth(expMonth);
        expDateFast.setYear(expYear);
        
        ccInfoFast.setExpirationDate(expDateFast);
        ccInfoFast.setName(name);
        ccInfoFast.setNumber(number);
        
        
        for(Itinerary itinerary: itineraryDb){
            for(HotelInformation hotelInfo: itinerary.hotels){
                if(bookHotel(hotelInfo.getBookingNumber(), ccInfo)){
                    hotelInfo.setStatus("Confirmed");
                }
            }
            for(FlightInformation flightInfo : itinerary.flights){
                if(bookFlight(flightInfo.getBookingNumber(), ccInfoFast)){
                    flightInfo.setStatus("Confirmed");
                }
            }
            
            itinerary.status = Itinerary.BookingStatus.BOOKED;
            return itinerary;
        }      
        return null;
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
    @Path("/{itineraryId}/hotel/{bookingNumber}")
    @Produces("application/json")
    public Itinerary addHotelToItinerary(
            @PathParam("itineraryId") String itineraryID,
            @PathParam("bookingNumber") String bookingNumber){
        
        for(Itinerary itinerary : itineraryDb)
            if(itinerary.ID.equals(itineraryID)){
                for(HotelInformation hotelInfo: HotelResource.getSearchedHotels())
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
    
    @PUT
    @Path("/{itineraryId}/flight/{bookingNumber}")
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
            return null;
        }
        return null;
    }
    
    
    @DELETE
    @Path("/reset")
    public void resetItineraryDB(){
        itineraryDb = new ArrayList();
    }

    private static boolean bookHotel(int bookingNumber, hotel.ws.CreditCardInfoType creditCardInformation) throws CreditCardFaultMessage {
        hotel.ws.HotelService service = new hotel.ws.HotelService();
        hotel.ws.HotelController port = service.getHotelControllerPort();
        return port.bookHotel(bookingNumber, creditCardInformation);
    }

    private static boolean bookFlight(int bookingNumber, dk.dtu.imm.fastmoney.types.CreditCardInfoType creditCardInformation) throws airline.ws.CreditCardFaultMessage {
        airline.ws.AirlineService service = new airline.ws.AirlineService();
        airline.ws.AirlineController port = service.getAirlineControllerPort();
        return port.bookFlight(bookingNumber, creditCardInformation);
    }
    
}
    
