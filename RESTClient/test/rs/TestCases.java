/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs;

import airline.ws.FlightInformation;
import data.Itinerary;
import dk.dtu.imm.fastmoney.HotelInformation;
import static java.lang.String.valueOf;
import java.util.List;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import representation.ItineraryRepresentation;


public class TestCases {
    
    Client client = ClientBuilder.newClient(); 
    WebTarget itinerariesTarget = client.target("http://localhost:8080/ws.pr/webresources/itineraries");
    WebTarget hotelTarget = client.target("http://localhost:8080/ws.pr/webresources/hotels");
    WebTarget flightsTarget = client.target("http://localhost:8080/ws.pr/webresources/flights");
    WebTarget bookingsTarget = client.target("http://localhost:8080/ws.pr/webresources/bookings");
    
    @Before
    public void reset_itineraries_and_bookings(){
        itinerariesTarget.path("/reset")
                .request()
                .put(Entity.entity("reset", "application/json"));
        
        bookingsTarget.path("/reset")
                .request()
                .put(Entity.entity("reset", "application/json"));
    }
    
    @After
    public void reset_itineraries_and_bookings_post(){
        itinerariesTarget.path("/reset")
                .request()
                .put(Entity.entity("reset", "application/json"));
        
        bookingsTarget.path("/reset")
                .request()
                .put(Entity.entity("reset", "application/json"));
    }
    
    @Test
    public void P1(){
        ItineraryRepresentation newItinerary = createItinerary();
        String itineraryID = newItinerary.getItinerary().getID();
        //add first flight
        List<FlightInformation> flightInfos = searchFlights("Copenhagen", "Rome");
        String flightID = valueOf(flightInfos.get(0).getBookingNumber());
        ItineraryRepresentation updated = addFlightToItinerary(itineraryID, flightID);
        //add first hotel
        List<HotelInformation> hotelInfos = searchHotels("Paris");
        String hotelID = valueOf(hotelInfos.get(0).getBookingNumber());
        updated = addHotel(itineraryID, hotelID);
        //add second flight
        flightInfos = searchFlights("Copenhagen", "Milan");
        flightID = valueOf(flightInfos.get(0).getBookingNumber());
        updated = addFlightToItinerary(itineraryID, flightID);
        //add third flight
        flightInfos = searchFlights("Copenhagen", "Turin");
        flightID = valueOf(flightInfos.get(0).getBookingNumber());
        updated = addFlightToItinerary(itineraryID, flightID);  
        //add second hotel
        hotelInfos = searchHotels("Copenhagen");
        hotelID = valueOf(hotelInfos.get(0).getBookingNumber());
        updated = addHotel(itineraryID, hotelID);
        //check unconfirmed status
        for(FlightInformation flights : updated.getItinerary().getFlights())
            assertEquals(airline.ws.BookingStatus.UNCONFIRMED,flights.getBookingStatus());
        for(HotelInformation hotels : updated.getItinerary().getHotels())
            assertEquals(dk.dtu.imm.fastmoney.BookingStatus.UNCONFIRMED,hotels.getStatus());
    }
    
    @Test
    public void P2(){
        ItineraryRepresentation newItinerary = createItinerary();
        String itineraryID = newItinerary.getItinerary().getID();
        //add a flight
        List<FlightInformation> flightInfos = searchFlights("Copenhagen", "Rome");
        String flightID = valueOf(flightInfos.get(0).getBookingNumber());
        ItineraryRepresentation updated = addFlightToItinerary(itineraryID, flightID);
        //cancel
        ItineraryRepresentation canceled = cancelPlannedItinerary(itineraryID);
        assertEquals(canceled.getItinerary().getStatus(), data.Itinerary.BookingStatus.CANCELLED);
    }
    
    @Test
    public void B(){
        ItineraryRepresentation newItinerary = createItinerary();
        String itineraryID = newItinerary.getItinerary().getID();
        //add first flight
        List<FlightInformation> flightInfos = searchFlights("Copenhagen", "Rome");
        String flightID = valueOf(flightInfos.get(0).getBookingNumber());
        ItineraryRepresentation updated = addFlightToItinerary(itineraryID, flightID);
        //add second flight
        flightInfos = searchFlights("BookingFailure", "Rome");
        flightID = valueOf(flightInfos.get(0).getBookingNumber());
        updated = addFlightToItinerary(itineraryID, flightID);
        //add first hotel
        List<HotelInformation> hotelInfos = searchHotels("Paris");
        String hotelID = valueOf(hotelInfos.get(0).getBookingNumber());
        updated = addHotel(itineraryID, hotelID);
        //check unconfirmed status
        for(FlightInformation flights : updated.getItinerary().getFlights())
            assertEquals(airline.ws.BookingStatus.UNCONFIRMED,flights.getBookingStatus());
        for(HotelInformation hotels : updated.getItinerary().getHotels())
            assertEquals(dk.dtu.imm.fastmoney.BookingStatus.UNCONFIRMED,hotels.getStatus());
        //book
        ItineraryRepresentation booked;
        try{
            booked = bookItinerary(itineraryID);
        }catch(Exception e){
            assertTrue(e instanceof InternalServerErrorException);
        }
        //check bookings
        ItineraryRepresentation cancelled = getBooking(itineraryID);
        List <FlightInformation> flights = cancelled.getItinerary().getFlights();
        assertEquals(airline.ws.BookingStatus.CANCELLED, flights.get(0).getStatus());
        assertEquals(airline.ws.BookingStatus.UNCONFIRMED, flights.get(1).getStatus());
        List<HotelInformation> hotels = cancelled.getItinerary().getHotels();
        assertEquals(dk.dtu.imm.fastmoney.BookingStatus.UNCONFIRMED, hotels.get(0).getStatus());
    }
    
    @Test
    public void C1(){
        ItineraryRepresentation newItinerary = createItinerary();
        String itineraryID = newItinerary.getItinerary().getID();
        //add first flight
        List<FlightInformation> flightInfos = searchFlights("Copenhagen", "Rome");
        String flightID = valueOf(flightInfos.get(0).getBookingNumber());
        ItineraryRepresentation updated = addFlightToItinerary(itineraryID, flightID);
        //add second flight
        flightInfos = searchFlights("Copenhagen", "Milan");
        flightID = valueOf(flightInfos.get(0).getBookingNumber());
        updated = addFlightToItinerary(itineraryID, flightID);
        //add first hotel
        List<HotelInformation> hotelInfos = searchHotels("Paris");
        String hotelID = valueOf(hotelInfos.get(0).getBookingNumber());
        updated = addHotel(itineraryID, hotelID);
        //book
        ItineraryRepresentation booked = bookItinerary(itineraryID);
        //check confirmed status
        for(FlightInformation flights : booked.getItinerary().getFlights())
            assertEquals(airline.ws.BookingStatus.BOOKED,flights.getStatus());
        for(HotelInformation hotels : booked.getItinerary().getHotels())
            assertEquals(dk.dtu.imm.fastmoney.BookingStatus.BOOKED,hotels.getStatus());
        //cancel
        ItineraryRepresentation cancelled = cancelBookedItinerary(itineraryID);
        //check cancelled status
        for(FlightInformation flights : cancelled.getItinerary().getFlights())
            assertEquals(airline.ws.BookingStatus.CANCELLED,flights.getStatus());
        for(HotelInformation hotels : cancelled.getItinerary().getHotels())
            assertEquals(dk.dtu.imm.fastmoney.BookingStatus.CANCELLED,hotels.getStatus());
    }
    
    @Test
    public void C2(){
        ItineraryRepresentation newItinerary = createItinerary();
        String itineraryID = newItinerary.getItinerary().getID();
        //add first flight
        List<FlightInformation> flightInfos = searchFlights("Copenhagen", "Rome");
        String flightID = valueOf(flightInfos.get(0).getBookingNumber());
        ItineraryRepresentation updated = addFlightToItinerary(itineraryID, flightID);
        //add second flight
        flightInfos = searchFlights("CancelingFailure", "Rome");
        flightID = valueOf(flightInfos.get(0).getBookingNumber());
        updated = addFlightToItinerary(itineraryID, flightID);
        //add first hotel
        List<HotelInformation> hotelInfos = searchHotels("Paris");
        String hotelID = valueOf(hotelInfos.get(0).getBookingNumber());
        updated = addHotel(itineraryID, hotelID);
        //book
        ItineraryRepresentation booked = bookItinerary(itineraryID);
        //check confirmed status
        for(FlightInformation flights : booked.getItinerary().getFlights())
            assertEquals(airline.ws.BookingStatus.BOOKED,flights.getStatus());
        for(HotelInformation hotels : booked.getItinerary().getHotels())
            assertEquals(dk.dtu.imm.fastmoney.BookingStatus.BOOKED,hotels.getStatus());
        //cancel
        ItineraryRepresentation cancelled;
        try{
            cancelled = cancelBookedItinerary(itineraryID);
        } catch(Exception e){
            assertTrue(e instanceof InternalServerErrorException);
        }
        //check bookings
        ItineraryRepresentation failedCancel = getBooking(itineraryID);
        List <FlightInformation> flights = failedCancel.getItinerary().getFlights();
        assertEquals(airline.ws.BookingStatus.CANCELLED, flights.get(0).getStatus());
        assertEquals(airline.ws.BookingStatus.BOOKED, flights.get(1).getStatus());
        List<HotelInformation> hotels = failedCancel.getItinerary().getHotels();
        assertEquals(dk.dtu.imm.fastmoney.BookingStatus.CANCELLED, hotels.get(0).getStatus());
    }
    
    private ItineraryRepresentation createItinerary(){
            return itinerariesTarget
                    .request()
                    .accept("application/itinerary+json")
                    .put(Entity.entity(new ItineraryRepresentation(), "application/itinerary+json"), ItineraryRepresentation.class);
        }
    
    private ItineraryRepresentation getBooking(String itineraryID){
        return bookingsTarget.path("/" + itineraryID)
                    .request()
                    .accept("application/itinerary+json")
                    .get(ItineraryRepresentation.class);
    }
    
    private ItineraryRepresentation getItinerary(String itineraryID){
        return itinerariesTarget.path("/" + itineraryID)
                    .request()
                    .accept("application/itinerary+json")
                    .get(ItineraryRepresentation.class);
    }
    
    private List<HotelInformation> searchHotels(String city){
        return hotelTarget.path("/" + city)
                    .queryParam("start", "25-11-2016")
                    .queryParam("end", "31-11-2016")
                    .request("application/json")
                    .accept("application/json")
                    .get(new GenericType<List<HotelInformation>>() {});
    }
    
    private ItineraryRepresentation addHotel(String itineraryID, String hotelID){
        return itinerariesTarget.path("/" + itineraryID + "/hotels/" + hotelID)
                    .request()
                    .accept("application/itinerary+json")
                    .put(Entity.entity(new Itinerary("ignored"), "application/itinerary+json"), ItineraryRepresentation.class);
    }
    
    private List<FlightInformation> searchFlights(String startAirport, String endAirport){
        return flightsTarget.path("/" + startAirport + "/" + endAirport)
                    .queryParam("departureDate", "26-11-2016")
                    .request()
                    .accept("application/json")
                    .get(new GenericType<List<FlightInformation>>() {});
    }
    
    private ItineraryRepresentation addFlightToItinerary(String itineraryID, String flightID){
        return itinerariesTarget.path("/" + itineraryID + "/flights/" + flightID)
                    .request()
                    .accept("application/itinerary+json")
                    .put(Entity.entity(new Itinerary("ignored"), "application/itinerary+json"), ItineraryRepresentation.class);
    }
    
    private ItineraryRepresentation bookItinerary(String itineraryID){
        return bookingsTarget.path("/"+itineraryID)
                                        .queryParam("name", "Tobiasen Inge")
                                        .queryParam("number", "50408823")
                                        .queryParam("expMonth", 9)
                                        .queryParam("expYear", 10)
                                        .request()
                                        .accept("application/itinerary+json")
                                        .post(Entity.entity(new Itinerary("ignored"), "application/itinerary+json"), ItineraryRepresentation.class);
    }
    
    private ItineraryRepresentation cancelBookedItinerary(String itineraryID){
        return bookingsTarget.path("/"+itineraryID)
                                        .queryParam("name", "Tobiasen Inge")
                                        .queryParam("number", "50408823")
                                        .queryParam("expMonth", 9)
                                        .queryParam("expYear", 10)
                                        .request()
                                        .accept("application/itinerary+json")
                                        .delete(ItineraryRepresentation.class);
    }
    
    private ItineraryRepresentation cancelPlannedItinerary(String itineraryID){
        return itinerariesTarget.path("/"+itineraryID)
//                                        .queryParam("name", "Tobiasen Inge")
//                                        .queryParam("number", "50408823")
//                                        .queryParam("expMonth", 9)
//                                        .queryParam("expYear", 10)
                                        .request()
                                        .accept("application/itinerary+json")
                                        .delete(ItineraryRepresentation.class);
    }
    
}
