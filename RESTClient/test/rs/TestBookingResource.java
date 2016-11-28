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
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import org.junit.Test;
import static org.junit.Assert.*;
import representation.ItineraryRepresentation;

/**
 *
 * @author lucacambiaghi
 */
public class TestBookingResource {
    
    Client client = ClientBuilder.newClient(); 
    WebTarget itinerariesTarget = client.target("http://localhost:8080/ws.pr/webresources/itineraries");
    WebTarget hotelTarget = client.target("http://localhost:8080/ws.pr/webresources/hotels");
    WebTarget flightsTarget = client.target("http://localhost:8080/ws.pr/webresources/flights");
    WebTarget bookingsTarget = client.target("http://localhost:8080/ws.pr/webresources/bookings");
    
    @Test
    public void should_book_itinerary(){
        ItineraryRepresentation newItinerary = createItinerary();
        
        String itineraryID = newItinerary.getItinerary().getID();
        
        List<HotelInformation> hotels = searchHotels();
        
        HotelInformation hotel = hotels.get(0);
        String hotelID = valueOf(hotel.getBookingNumber());
        
        ItineraryRepresentation updated = addHotel(itineraryID, hotelID);
 
        ItineraryRepresentation booked = bookItinerary(itineraryID);
        
        assertEquals(booked.getItinerary().getStatus(), Itinerary.BookingStatus.BOOKED);
        
    } 
    
    //@Test
    public void should_cancel_itinerary(){
        ItineraryRepresentation newItinerary = createItinerary();
        
        String itineraryID = newItinerary.getItinerary().getID();
        
        List<HotelInformation> hotels = searchHotels();
        
        HotelInformation hotel = hotels.get(0);
        String hotelID = valueOf(hotel.getBookingNumber());
        
        ItineraryRepresentation updated = addHotel(itineraryID, hotelID);
 
        ItineraryRepresentation booked = bookItinerary(itineraryID);
      
        
        
    }
    
    public ItineraryRepresentation createItinerary(){
            return itinerariesTarget
                    .request()
                    .accept("application/itinerary+json")
                    .put(Entity.entity(new ItineraryRepresentation(), "application/itinerary+json"), ItineraryRepresentation.class);
        }
    
    public List<HotelInformation> searchHotels(){
        return hotelTarget.path("/Copenhagen")
                    .queryParam("start", "25-11-2016")
                    .queryParam("end", "31-11-2016")
                    .request("application/json")
                    .accept("application/json")
                    .get(new GenericType<List<HotelInformation>>() {});
    }
    
    public ItineraryRepresentation addHotel(String itineraryID, String hotelID){
        return itinerariesTarget.path("/" + itineraryID + "/hotels/" + hotelID)
                    .request()
                    .accept("application/itinerary+json")
                    .put(Entity.entity(new Itinerary(), "application/itinerary+json"), ItineraryRepresentation.class);
    }
    
    public ItineraryRepresentation bookItinerary(String itineraryID){
        return bookingsTarget.path("/"+itineraryID)
                                        .queryParam("name", "Tobiasen Inge")
                                        .queryParam("number", "50408823")
                                        .queryParam("expMonth", 9)
                                        .queryParam("expYear", 10)
                                        .request()
                                        .accept("application/itinerary+json")
                                        .post(Entity.entity(new Itinerary(), "application/itinerary+json"), ItineraryRepresentation.class);
    }
    
}
