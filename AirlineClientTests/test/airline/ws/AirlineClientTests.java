package airline.ws;

import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class AirlineClientTests {
    
    @Test
    public void should_return_flights() {
        XMLGregorianCalendar depDate = null;
        try {
            depDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar(2016, 11, 26));
        } catch (DatatypeConfigurationException ex) {
            Logger.getLogger(AirlineClientTests.class.getName()).log(Level.SEVERE, null, ex);
        }
        String startAirport = "Copenhagen";
        String endAirport = "Rome";
        
        java.util.List<airline.ws.FlightInformation> result = getFlights(startAirport, endAirport, depDate);
        assertTrue(result.size() > 0); 
    }
    
//    @Test
//    public void bookFlightTest () {
//        
//    }

    private static java.util.List<airline.ws.FlightInformation> getFlights(java.lang.String startAirport, java.lang.String endAirport, java.lang.Object startDate) {
        airline.ws.AirlineService service = new airline.ws.AirlineService();
        airline.ws.AirlineController port = service.getAirlineControllerPort();
        return port.getFlights(startAirport, endAirport, startDate);
    }
    
}
