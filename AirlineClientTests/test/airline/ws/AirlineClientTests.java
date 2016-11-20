package airline.ws;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import org.junit.Test;
import static org.junit.Assert.*;

public class AirlineClientTests {
    
    @Test
    public void should_return_flight() {
        //db contains 
        //copenhagen;rome;2016-8-10;2016-9-10;Ryanair;10
        //Date depDate = new GregorianCalendar(2016, 8, 10).getTime();
        XMLGregorianCalendar depDate = null;
        try {
            depDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar(2016, 8, 10));
        } catch (DatatypeConfigurationException ex) {
            Logger.getLogger(AirlineClientTests.class.getName()).log(Level.SEVERE, null, ex);
        }
        String startAirport = "copenhagen";
        String endAirport = "rome";
        
        java.util.List<airline.ws.FlightInformation> result = getFlights(startAirport, endAirport, depDate);
        assert(result.size()>0);
        
    }

    private static java.util.List<airline.ws.FlightInformation> getFlights(java.lang.String startAirport, java.lang.String endAirport, javax.xml.datatype.XMLGregorianCalendar startDate) {
        airline.ws.AirlineService service = new airline.ws.AirlineService();
        airline.ws.AirlineController port = service.getAirlineControllerPort();
        return port.getFlights(startAirport, endAirport, startDate);
    }

    
    
}
