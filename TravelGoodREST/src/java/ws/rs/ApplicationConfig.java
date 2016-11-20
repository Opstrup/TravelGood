package ws.rs;

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
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(ws.rs.AirlineResource.class);
        resources.add(ws.rs.ItinerarieResource.class);
    }
    
}
