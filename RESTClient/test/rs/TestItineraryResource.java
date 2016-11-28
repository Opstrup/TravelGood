/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs;

import data.Itinerary;
import dk.dtu.imm.fastmoney.HotelInformation;
import static java.lang.String.valueOf;
import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;
import representation.ItineraryRepresentation;
import representation.Link;
import representation.Representation;

/**
 *
 * @author lucacambiaghi
 */
public class TestItineraryResource {
    
    Client client = ClientBuilder.newClient(); 
    WebTarget itTarget = client.target("http://localhost:8080/ws.pr/webresources/itineraries");
    WebTarget hotTarget = client.target("http://localhost:8080/ws.pr/webresources/hotels");
    WebTarget bookTarget = client.target("http://localhost:8080/ws.pr/webresources/bookings");
    
    @Before
    public void reset_itineraries(){
        itTarget.path("/reset")
                .request()
                .put(Entity.entity("reset", "application/json"));
    }
    
    @Test
    public void should_create_itinerary(){
        ItineraryRepresentation newItinerary = itTarget
                    .request()
                    .accept("application/itinerary+json")
                    .put(Entity.entity(new ItineraryRepresentation(), "application/itinerary+json"), ItineraryRepresentation.class);
        assertNotNull(newItinerary);
    }
    
    @Test
    public void should_link_to_hotels(){
        Representation newItinerary = itTarget
                    .request()
                    .accept("application/itinerary+json")
                    .put(Entity.entity(new Itinerary(), "application/itinerary+json"), ItineraryRepresentation.class);
        Link hotelsLink = newItinerary.getLinkByRelation("http://travelgood.ws/relations/searchHotels"); 
        assertNotNull(hotelsLink);
    }
    
    @Test
    public void should_add_hotel(){
        ItineraryRepresentation newItinerary = itTarget
                    .request()
                    .accept("application/itinerary+json")
                    .put(Entity.entity(new ItineraryRepresentation(), "application/itinerary+json"), ItineraryRepresentation.class);
        
        String itineraryID = newItinerary.getItinerary().getID();
        
        List<HotelInformation> hotels = hotTarget.path("/Copenhagen")
                    .queryParam("start", "25-11-2016")
                    .queryParam("end", "31-11-2016")
                    .request("application/json")
                    .accept("application/json")
                    .get(new GenericType<List<HotelInformation>>() {});
        
        HotelInformation hotel = hotels.get(0);
        String hotelID = valueOf(hotel.getBookingNumber());
        
        ItineraryRepresentation updated = itTarget.path("/" + itineraryID + "/hotels/" + hotelID)
                    .request()
                    .accept("application/itinerary+json")
                    .put(Entity.entity(new Itinerary(), "application/itinerary+json"), ItineraryRepresentation.class);
        
        assert(updated.getItinerary().getHotels().size()==1);
        
    }
    
    //@Test
    public void bookItinerary(){
        ItineraryRepresentation newItinerary = itTarget
                    .request()
                    .accept("application/itinerary+json")
                    .put(Entity.entity(new Itinerary(), "application/itinerary+json"), ItineraryRepresentation.class);
        
        String itineraryID = newItinerary.getItinerary().getID();
        
        List<HotelInformation> hotels = hotTarget.path("/Copenhagen")
                    .queryParam("start", "25-11-2016")
                    .queryParam("end", "31-11-2016")
                    .request("application/json")
                    .accept("application/json")
                    .get(new GenericType<List<HotelInformation>>() {});
        
        HotelInformation hotel = hotels.get(0);
        String hotelID = valueOf(hotel.getBookingNumber());
        
        ItineraryRepresentation updated = itTarget.path("/" + itineraryID + "/hotels/" + hotelID)
                    .request()
                    .accept("application/itinerary+json")
                    .put(Entity.entity(new Itinerary(), "application/itinerary+json"), ItineraryRepresentation.class);
        
        
        
 
        ItineraryRepresentation booked = bookTarget.path("/"+itineraryID)
                                        .queryParam("name", "Tobiasen Inge")
                                        .queryParam("number", "50408823")
                                        .queryParam("expMonth", 9)
                                        .queryParam("expYear", 10)
                                        .request()
                                        .accept("application/itinerary+json")
                                        .post(Entity.entity(new Itinerary(), "application/itinerary+json"), ItineraryRepresentation.class);
        
        assertEquals(booked.getItinerary().getStatus(), Itinerary.BookingStatus.BOOKED);
    } 
}
