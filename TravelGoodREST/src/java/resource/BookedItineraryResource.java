/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resource;

import data.Itinerary;
import airline.ws.CreditCardFaultMessage;
import airline.ws.Exception_Exception;
import airline.ws.FlightInformation;
import hotel.ws.HotelInformation;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import static resource.ItineraryResource.itineraryDb;

@Path("bookings")
public class BookedItineraryResource {
    
    public static List<Itinerary> bookedItineraries = new ArrayList<>();
    
    @POST
    @Path("/{itineraryId}/")
    @Produces("application/json")
    public Itinerary bookItinerary(@PathParam("itineraryId") String id,
            @QueryParam ("name") String name,
            @QueryParam("number") String number,
            @QueryParam("expMonth") int expMonth,
            @QueryParam("expYear") int expYear)
            throws hotel.ws.CreditCardFaultMessage, airline.ws.CreditCardFaultMessage, Exception_Exception, Exception{
        
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
        Itinerary it = null;
        try {
            for(Itinerary itinerary: itineraryDb){     
                if(itinerary.ID.equals(id)){
                    it = itinerary;
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
        }   
        } catch (Exception e) {
            if(it!= null){
                for(HotelInformation hotelInfo: it.hotels){
                    if(hotelInfo.getStatus().equals("Confirmed")){                        
                        try {
                            cancelHotel(hotelInfo.getBookingNumber());
                            hotelInfo.setStatus("Cancelled");
                        } catch (Exception t) {
                            throw new Exception("Failed to cancel hotel reservation");
                        }
                        
                        
                    }
                }
                for(FlightInformation flightInfo: it.flights){
                    if(flightInfo.getStatus().equals("Confirmed")){
                        try {
                            cancelFlight(flightInfo.getBookingNumber(),flightInfo.getFlight().getFlightPrice(),ccInfoFast);
                            flightInfo.setStatus("Cancelled");
                        } catch (Exception t) {
                            throw  new Exception("Failed to cancel flight reservation");
                        }
                    }
                }
            }
        }
              
        return it;
    }

    private static boolean bookFlight(int bookingNumber, dk.dtu.imm.fastmoney.types.CreditCardInfoType creditCardInformation) throws CreditCardFaultMessage {
        airline.ws.AirlineService service = new airline.ws.AirlineService();
        airline.ws.AirlineController port = service.getAirlineControllerPort();
        return port.bookFlight(bookingNumber, creditCardInformation);
    }

    private static void cancelFlight(int bookingNumber, int flightPrice, dk.dtu.imm.fastmoney.types.CreditCardInfoType creditCardInformation) throws Exception_Exception, CreditCardFaultMessage {
        airline.ws.AirlineService service = new airline.ws.AirlineService();
        airline.ws.AirlineController port = service.getAirlineControllerPort();
        port.cancelFlight(bookingNumber, flightPrice, creditCardInformation);
    }

    private static boolean bookHotel(int bookingNumber, hotel.ws.CreditCardInfoType creditCardInformation) throws hotel.ws.Exception_Exception, hotel.ws.CreditCardFaultMessage {
        hotel.ws.HotelService service = new hotel.ws.HotelService();
        hotel.ws.HotelController port = service.getHotelControllerPort();
        return port.bookHotel(bookingNumber, creditCardInformation);
    }

    private static void cancelHotel(int bookingNumber) throws hotel.ws.Exception_Exception {
        hotel.ws.HotelService service = new hotel.ws.HotelService();
        hotel.ws.HotelController port = service.getHotelControllerPort();
        port.cancelHotel(bookingNumber);
    }
    
}
