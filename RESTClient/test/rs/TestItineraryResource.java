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
    WebTarget itinerariesTarget = client.target("http://localhost:8080/ws.pr/webresources/itineraries");
    WebTarget hotelsTarget = client.target("http://localhost:8080/ws.pr/webresources/hotels");
    WebTarget flightsTarget = client.target("http://localhost:8080/ws.pr/webresources/flights");
    
    @Before
    public void reset_itineraries(){
        itinerariesTarget.path("/reset")
                .request()
                .put(Entity.entity("reset", "application/json"));
    }
    
    @Test
    public void should_create_itinerary(){
        ItineraryRepresentation newItinerary = createItinerary();
        assertNotNull(newItinerary);
    }
    
    @Test
    public void should_link_to_hotels(){
        ItineraryRepresentation newItinerary = createItinerary();
        Link hotelsLink = newItinerary.getLinkByRelation("http://travelgood.ws/relations/searchHotels"); 
        assertNotNull(hotelsLink);
    }
    
    @Test
    public void should_add_hotel(){
        ItineraryRepresentation newItinerary = createItinerary();
        
        String itineraryID = newItinerary.getItinerary().getID();
        
        List<HotelInformation> hotels = hotelsTarget.path("/Copenhagen")
                    .queryParam("start", "25-11-2016")
                    .queryParam("end", "31-11-2016")
                    .request()
                    .accept("application/json")
                    .get(new GenericType<List<HotelInformation>>() {});
        
        HotelInformation hotel = hotels.get(0);
        String hotelID = valueOf(hotel.getBookingNumber());
        
        ItineraryRepresentation updated = itinerariesTarget.path("/" + itineraryID + "/hotels/" + hotelID)
                    .request()
                    .accept("application/itinerary+json")
                    .put(Entity.entity(new Itinerary("ignored"), "application/itinerary+json"), ItineraryRepresentation.class);
        
        assert(updated.getItinerary().getHotels().size()==1);
        
    }
    
    @Test
    public void should_add_flight(){
        ItineraryRepresentation newItinerary = createItinerary();
        
        String itineraryID = newItinerary.getItinerary().getID();
        
        List<FlightInformation> flights = searchFlights("Copenhagen", "Rome"); 
        
        FlightInformation flight = flights.get(0);
        String flightID = valueOf(flight.getBookingNumber());
        
        ItineraryRepresentation updated = addFlightToItinerary(itineraryID, flightID);
        
        assert(updated.getItinerary().getFlights().size()==1);
        
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
    
    private ItineraryRepresentation createItinerary(){
            return itinerariesTarget
                    .request()
                    .accept("application/itinerary+json")
                    .put(Entity.entity(new ItineraryRepresentation(), "application/itinerary+json"), ItineraryRepresentation.class);
        }
    
}
