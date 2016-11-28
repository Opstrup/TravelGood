/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resource;

import data.Itinerary;
import airline.ws.FlightBookException_Exception;
import airline.ws.FlightCancelException;
import airline.ws.FlightCancelException_Exception;
import airline.ws.FlightInformation;
import hotel.ws.BookingStatus;
import hotel.ws.CreditCardInfoType;
import hotel.ws.HotelBookException_Exception;
import hotel.ws.HotelCancelException_Exception;
import hotel.ws.HotelInformation;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import static resource.ItineraryResource.itineraryDb;
import representation.ItineraryRepresentation;

@Path("bookings")
public class BookedItineraryResource {
    
    public static List<Itinerary> bookedItineraries = new ArrayList<>();
    
    @POST
    @Path("/{itineraryId}/")
    @Produces("application/itinerary+json")
    public ItineraryRepresentation bookItinerary(@PathParam("itineraryId") String id,
            @QueryParam ("name") String name,
            @QueryParam("number") String number,
            @QueryParam("expMonth") int expMonth,
            @QueryParam("expYear") int expYear){
        
        CreditCardInfoType ccInfo = createCcInfo(expMonth, expYear, name, number);
        dk.dtu.imm.fastmoney.types.CreditCardInfoType ccInfoFast = createCCInfoFast(expMonth, expYear, name, number);
        
        //try to book all bookings. if one fails, catch exception
        for(Itinerary itinerary: itineraryDb)   
            if(itinerary.ID.equals(id) && itinerary.getStatus() == Itinerary.BookingStatus.UNCONFIRMED){
                try{
                    for(HotelInformation hotelInfo: itinerary.hotels)
                        if(bookHotel(hotelInfo.getBookingNumber(), ccInfo))
                            hotelInfo.setStatus(hotel.ws.BookingStatus.BOOKED);

                    for(FlightInformation flightInfo : itinerary.flights)
                        if(bookFlight(flightInfo.getBookingNumber(), ccInfoFast))
                            flightInfo.setStatus(airline.ws.BookingStatus.BOOKED);

                }catch(FlightBookException_Exception | HotelBookException_Exception e){
                    cancelBookings(itinerary, ccInfoFast);
                    //throw exception: booking failed
                }
                
                itinerary.setStatus(Itinerary.BookingStatus.BOOKED);
                ItineraryRepresentation itRep = new ItineraryRepresentation();
                itRep.setItinerary(itinerary);
                return itRep;

            }
        //404 it not found
        return null;
        
    }
    
    @DELETE
    @Path("/{itineraryId}/")
    @Produces("application/itinerary+json")
    public ItineraryRepresentation cancelBookedItinerary(
        @PathParam("itineraryID") String itineraryID,
        @QueryParam ("name") String name,
        @QueryParam("number") String number,
        @QueryParam("expMonth") int expMonth,
        @QueryParam("expYear") int expYear){
        
        CreditCardInfoType ccInfo = createCcInfo(expMonth, expYear, name, number);
        dk.dtu.imm.fastmoney.types.CreditCardInfoType ccInfoFast = createCCInfoFast(expMonth, expYear, name, number);
        
        for(Itinerary itinerary: bookedItineraries)
            if(itinerary.ID.equals(itineraryID)){
                
                //can throw exception
                cancelBookings(itinerary, ccInfoFast);
                
                //cancellation successful
                itinerary.status=Itinerary.BookingStatus.CANCELLED;
                ItineraryRepresentation itRep = new ItineraryRepresentation();
                itRep.setItinerary(itinerary);
                return itRep;
            }
        
        //404 itinerary not found
        return null;
        
    }
    
    private void cancelBookings(Itinerary itinerary, dk.dtu.imm.fastmoney.types.CreditCardInfoType ccInfoFast) {
        try{
            for(FlightInformation flightInfo : itinerary.flights){
                if(flightInfo.getStatus() == airline.ws.BookingStatus.BOOKED)
                cancelFlight(flightInfo.getBookingNumber(),flightInfo.getFlight().getFlightPrice(),ccInfoFast);
                flightInfo.setStatus(airline.ws.BookingStatus.CANCELLED);
            }
            
            for(HotelInformation hotelInfo: itinerary.hotels){
                cancelHotel(hotelInfo.getBookingNumber());
                hotelInfo.setStatus(BookingStatus.CANCELLED);
            }
        } catch (FlightCancelException_Exception | HotelCancelException_Exception e) { 
        // continue cancelling others and throw exception
        } 
    }
    
    private CreditCardInfoType createCcInfo(int expMonth, int expYear, String name, String number) {
        hotel.ws.CreditCardInfoType ccInfo = new hotel.ws.CreditCardInfoType();
        hotel.ws.CreditCardInfoType.ExpirationDate expDate = new hotel.ws.CreditCardInfoType.ExpirationDate();
        
        expDate.setMonth(expMonth);
        expDate.setYear(expYear);
        
        ccInfo.setExpirationDate(expDate);
        ccInfo.setName(name);
        ccInfo.setNumber(number);
        
        return ccInfo;
    }

    private dk.dtu.imm.fastmoney.types.CreditCardInfoType createCCInfoFast(int expMonth, int expYear, String name, String number) {
        
        dk.dtu.imm.fastmoney.types.CreditCardInfoType ccInfoFast = new dk.dtu.imm.fastmoney.types.CreditCardInfoType();
        dk.dtu.imm.fastmoney.types.CreditCardInfoType.ExpirationDate expDateFast = new dk.dtu.imm.fastmoney.types.CreditCardInfoType.ExpirationDate();
        
        expDateFast.setMonth(expMonth);
        expDateFast.setYear(expYear);
        
        ccInfoFast.setExpirationDate(expDateFast);
        ccInfoFast.setName(name);
        ccInfoFast.setNumber(number);
        
        return ccInfoFast;
    }

    private static boolean bookFlight(int bookingNumber, dk.dtu.imm.fastmoney.types.CreditCardInfoType creditCardInformation) throws FlightBookException_Exception {
        airline.ws.AirlineService service = new airline.ws.AirlineService();
        airline.ws.AirlineController port = service.getAirlineControllerPort();
        return port.bookFlight(bookingNumber, creditCardInformation);
    }

    private static boolean cancelFlight(int bookingNumber, int flightPrice, dk.dtu.imm.fastmoney.types.CreditCardInfoType creditCardInformation) throws FlightCancelException_Exception {
        airline.ws.AirlineService service = new airline.ws.AirlineService();
        airline.ws.AirlineController port = service.getAirlineControllerPort();
        return port.cancelFlight(bookingNumber, flightPrice, creditCardInformation);
    }

    private static boolean bookHotel(int bookingNumber, hotel.ws.CreditCardInfoType creditCardInformation) throws HotelBookException_Exception {
        hotel.ws.HotelService service = new hotel.ws.HotelService();
        hotel.ws.HotelController port = service.getHotelControllerPort();
        return port.bookHotel(bookingNumber, creditCardInformation);
    }

    private static void cancelHotel(int bookingNumber) throws HotelCancelException_Exception {
        hotel.ws.HotelService service = new hotel.ws.HotelService();
        hotel.ws.HotelController port = service.getHotelControllerPort();
        port.cancelHotel(bookingNumber);
    }

    
}
