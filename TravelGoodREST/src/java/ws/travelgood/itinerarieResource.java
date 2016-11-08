package ws.travelgood;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("itinerarie")
public class itinerarieResource {
    
    @GET
    public String getIntinerarie() {
        return "hello";
    }
}
