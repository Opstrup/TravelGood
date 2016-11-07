package airline.ws;

import java.util.Date;
import org.junit.Test;
import static org.junit.Assert.*;

public class AirlineTests {
    
    /*
     * Simepl unit test of the flight class 
     */
    @Test
    public void creationOfAFlightTest() {
        Date depature = new Date(2016, 11, 10);
        Flight UUT = new Flight("Paris", "Copenhagen", depature, 10);
        assertEquals(UUT.getDestination(), "Paris");
    }
}
