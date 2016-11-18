import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestItinerarieResource {
    Client client = ClientBuilder.newClient(); 
    WebTarget r = client.target("http://localhost:8080/ws.pr/webresources/Itinerarie"); 
    
    @Test
    public void hello() {
    
    }
}
