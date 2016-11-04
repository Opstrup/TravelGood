package airline.ws;

import org.junit.Test;
import static org.junit.Assert.*;

public class AirlineClientTests {
    
    @Test
    public void dummyTest() {
        String flights = getFlights();
        String expected = "Hello World!";
        
        assertEquals(expected, flights);
    }

    private static String getFlights() {
        airline.ws.AirlineService service = new airline.ws.AirlineService();
        airline.ws.AirlineController port = service.getAirlineControllerPort();
        return port.getFlights();
    }
}
