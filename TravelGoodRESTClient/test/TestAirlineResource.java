import org.junit.Test;
import static org.junit.Assert.*;
import javax.ws.rs.client.*;

/**
 * @author Vitali
 */
public class TestAirlineResource {
    //Build an instance of a Client, from which a WebTarget is created.
    Client client = ClientBuilder.newClient(); 
    
    // Specificly targeting a web resource "airline", in a Client that contains several target methods.
    //                                  |--host--|port|root|App path--|resource path|, which we want to access.
    WebTarget r = client.target("http://localhost:8080/ws.pr/webresources/lameDuck"); 
    
    public TestAirlineResource() {}
    
    // Annotating the test methods as: @Test
    @Test
    public void testGetFlights() {
        String expected = "This airlin.";
        
        String result = r.request().get(String.class);
        assertEquals(expected,result);
    }
    /*
    @Test
    public void testBookFlight() {
    
    }
    @Test
    public void testCancleFlight() {
        String expected = "Cancled.";
        
        r.request().put(Entity.entity(expected,"text/plain"));
        String result = r.request().get(String.class);
        assertEquals(expected,result);
    }*/
}
