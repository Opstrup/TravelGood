package ws.rs.resource;

import ws.rs.data.Itinerary;
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
        resources.add(ws.rs.resource.AirlineResource.class);
        resources.add(ws.rs.resource.BookedItineraryResource.class);
        resources.add(ws.rs.resource.HotelResource.class);
        resources.add(ws.rs.resource.ItineraryResource.class);
    }
    
}
