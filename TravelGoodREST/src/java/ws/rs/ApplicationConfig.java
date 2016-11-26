package ws.rs;

import airline.ws.FlightInformation;
import java.util.List;
import java.util.Set;
import javax.ws.rs.core.Application;

/**
 * @author Vitali
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        
        // Register costume resource classes
        resources.add(Itinerary.class);
        resources.add(List.class);
        resources.add(FlightInformation.class);
        addRestResourceClasses(resources);
        return resources;
    }

    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(ws.rs.AirlineResource.class);
        resources.add(ws.rs.HotelResource.class);
        resources.add(ws.rs.ItinerarieResource.class);
        resources.add(ws.rs.ItineraryResource.class);
    }
    
}
