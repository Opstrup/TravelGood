/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs;

import airline.ws.FlightInformation;
import data.Itinerary;
import dk.dtu.imm.fastmoney.HotelInformation;
import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
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
    
    @Test
    public void P1(){
        ItineraryRepresentation newItinerary = createItinerary();
        
        String itineraryID = newItinerary.getItinerary().getID();
        
        
        
        
    }
    
    
    private ItineraryRepresentation createItinerary(){
            return itinerariesTarget
                    .request()
                    .accept("application/itinerary+json")
                    .put(Entity.entity(new ItineraryRepresentation(), "application/itinerary+json"), ItineraryRepresentation.class);
        }
    
    private List<HotelInformation> searchHotels(String city){
        return hotelTarget.path("/" + city)
                    .queryParam("start", "25-11-2016")
                    .queryParam("end", "31-11-2016")
                    .request("application/json")
                    .accept("application/json")
                    .get(new GenericType<List<HotelInformation>>() {});
    }
    
    private List<FlightInformation> searchFlights(String startAirport, String endAirport){
        return flightsTarget.path("/" + startAirport + "/" + endAirport)
                    .queryParam("departureDate", "26-11-2016")
                    .request("application/json")
                    .accept("application/json")
                    .get(new GenericType<List<FlightInformation>>() {});
    }
    
    private ItineraryRepresentation addHotel(String itineraryID, String hotelID){
        return itinerariesTarget.path("/" + itineraryID + "/hotels/" + hotelID)
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
    
    private ItineraryRepresentation cancelItinerary(String itineraryID){
        return bookingsTarget.path("/"+itineraryID)
                                        .queryParam("name", "Tobiasen Inge")
                                        .queryParam("number", "50408823")
                                        .queryParam("expMonth", 9)
                                        .queryParam("expYear", 10)
                                        .request()
                                        .accept("application/itinerary+json")
                                        .delete(ItineraryRepresentation.class);
    }
    
}
