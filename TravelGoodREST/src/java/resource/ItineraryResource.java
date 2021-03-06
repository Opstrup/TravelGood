package resource;

import data.Itinerary;
import airline.ws.FlightInformation;
import hotel.ws.HotelInformation;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import representation.ItineraryRepresentation;
import representation.Link;
import representation.Representation;
import resource.HotelResource;

@Path("itineraries")
public class ItineraryResource {
    
    public static List<Itinerary> itineraryDb = new ArrayList<>();
    
    @GET
    @Produces("application/itinerary+json")
    public List<ItineraryRepresentation> getItineraries() {
        List<ItineraryRepresentation> itReps = new ArrayList<>();
        for(Itinerary itinerary: itineraryDb){
            ItineraryRepresentation itRep = new ItineraryRepresentation();
            itRep.setItinerary(itinerary);
            itReps.add(itRep);
            //links
        }
        return itReps;
    }
    
    @PUT
    @Produces("application/itinerary+json")
    public ItineraryRepresentation createItinerary(ItineraryRepresentation ignored) {
        int itineraryId = itineraryDb.size();
        
        Itinerary newItinerary = new Itinerary(String.valueOf(itineraryId));
        itineraryDb.add(newItinerary);
        
        ItineraryRepresentation itRep = new ItineraryRepresentation();
        itRep.setItinerary(newItinerary);
        
        addHotelsLink(itRep);
        //more links
        
        return itRep;
    }
    
    @GET 
    @Path("/{itineraryId}")
    @Produces("application/json")
    public Itinerary getItinerary(@PathParam("itineraryId") String id) {
        
        Itinerary itinerary;
        
        try{
            itinerary = this.itineraryDb.get(Integer.parseInt(id));
        } catch(Exception e){
            throw new BadRequestException(Response.
               status(Response.Status.BAD_REQUEST).
               entity("Itinerary not found").
               build());
        }
        
        return itinerary;
    }
    
    @DELETE
    @Path("/{itineraryId}")
    @Produces("application/itinerary+json")
    public ItineraryRepresentation cancelPlannedItinerary(
            Itinerary ignored,
            @PathParam("itineraryId") String id){
        Itinerary itineraryToBeRemoved = null;
        for(Itinerary itinerary: itineraryDb){
            if(itinerary.ID.equals(id)){
               itineraryToBeRemoved = itinerary;
            }
        }
        
        if(itineraryToBeRemoved==null)
            throw new BadRequestException(Response.
               status(Response.Status.BAD_REQUEST).
               entity("Id is not valid").
               build());
        
        itineraryToBeRemoved.setStatus(Itinerary.BookingStatus.CANCELLED);
//        itineraryDb.remove(itineraryToBeRemoved);
        
        ItineraryRepresentation itRep = new ItineraryRepresentation();
        itRep.setItinerary(itineraryToBeRemoved);
        return itRep;
    }
   
    @PUT
    @Path("/{itineraryId}/hotels/{bookingNumber}")
    @Produces("application/itinerary+json")
    public ItineraryRepresentation addHotelToItinerary(
            Itinerary ignored,
            @PathParam("itineraryId") String itineraryID,
            @PathParam("bookingNumber") String bookingNumber){
        
        ItineraryRepresentation itRep = new ItineraryRepresentation();
        
        for(Itinerary itinerary : itineraryDb){
            if(itinerary.ID.equals(itineraryID)){
                for(HotelInformation hotelInfo: HotelResource.getSearchedHotels())
                    if(hotelInfo.getBookingNumber() == Integer.parseInt(bookingNumber)){
                        itinerary.hotels.add(hotelInfo);
                        itRep.setItinerary(itinerary);
                        return itRep;
                    }
                
                throw new BadRequestException(Response.
                    status(Response.Status.BAD_REQUEST).
                    entity("hotelID not valid").
                    build());
            }
        }
        
        throw new BadRequestException(Response.
                    status(Response.Status.BAD_REQUEST).
                    entity("itineraryID not valid").
                    build());
               
    }
    
    @PUT
    @Path("/{itineraryId}/flights/{bookingNumber}")
    @Produces("application/itinerary+json")
    public ItineraryRepresentation addFlightToItinerary(
            Itinerary ignored,
            @PathParam ("itineraryId") String itineraryID,
            @PathParam ("bookingNumber") String bookingNumber){
        
        ItineraryRepresentation itRep = new ItineraryRepresentation();
        
        for(Itinerary itinerary:itineraryDb){
            if(itinerary.ID.equals(itineraryID)){
                for(FlightInformation flightInfo: AirlineResource.getSearchedFlights())
                    if(flightInfo.getBookingNumber() == Integer.parseInt(bookingNumber)){
                        itinerary.flights.add(flightInfo);
                        itRep.setItinerary(itinerary);
                        return itRep;
                    }
                throw new BadRequestException(Response.
                    status(Response.Status.BAD_REQUEST).
                    entity("flightID not valid").
                    build());
            }
        }
        
        throw new BadRequestException(Response.
                    status(Response.Status.BAD_REQUEST).
                    entity("itineraryID not valid").
                    build());
    }    
    
    @PUT
    @Path("/reset")
    public Response resetItineraryDB(String ignored){
        itineraryDb = new ArrayList<>();
        return Response.ok().build();
    }
    
    private static void addHotelsLink(Representation response) {
        Link link = new Link();
        link.setUri(String.format("http://localhost:8080/ws.pr/webresources/hotels/"));
        link.setRel("http://travelgood.ws/relations/searchHotels");
        response.getLinks().add(link);
    }
    
}
    
