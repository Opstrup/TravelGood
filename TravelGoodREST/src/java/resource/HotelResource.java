package resource;

import util.DateParser;
import hotel.ws.HotelInformation;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

@Path("hotels")
public class HotelResource {
    
    private static List<HotelInformation> searchedHotels = new ArrayList();

    public static void setSearchedHotels(List<HotelInformation> searchedHotels) {
        HotelResource.searchedHotels = searchedHotels;
    }
    
    @GET
    @Path("/{city}")
    @Produces("application/json")
    public List<HotelInformation> getHotelsForItinerary (
                @PathParam("city") String city, 
                @QueryParam("start") String startDate,
                @QueryParam("end") String endDate){ 
       
        XMLGregorianCalendar stDate = DateParser.parse(startDate);
        XMLGregorianCalendar enDate = DateParser.parse(endDate);
        
        List<HotelInformation> hotels = getHotels(city, stDate, enDate);
        for(HotelInformation hotelInfo : hotels){
            searchedHotels.add(hotelInfo);
        }
        
        return hotels;
    }
    
    private static java.util.List<hotel.ws.HotelInformation> getHotels(java.lang.String city, javax.xml.datatype.XMLGregorianCalendar arrivalDate, javax.xml.datatype.XMLGregorianCalendar departureDate) {
        hotel.ws.HotelService service = new hotel.ws.HotelService();
        hotel.ws.HotelController port = service.getHotelControllerPort();
        return port.getHotels(city, arrivalDate, departureDate);
    }
     
    public static List<HotelInformation> getSearchedHotels() {
        return searchedHotels;
    }
}
