package airline.ws;

import java.util.Date;
import java.util.GregorianCalendar;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import org.junit.Test;
import static org.junit.Assert.*;

public class AirlineClientTests {
    
    @Test
    public void should_return_flight() throws DatatypeConfigurationException {
        //db contains 
        //copenhagen;rome;2016-8-10;2016-9-10;Ryanair;10
        //Date depDate = new GregorianCalendar(2016, 8, 10).getTime();
        XMLGregorianCalendar depDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar(2016, 8, 10));
        
        String startAirport = "Copenhagen";
        String endAirport = "Rome";
        
        java.util.List<airline.ws.FlightInformation> result = getFlights(startAirport, endAirport, depDate);
        assertTrue(result.size()>0);
        
    }

    private static java.util.List<airline.ws.FlightInformation> getFlights(java.lang.String startAirport, java.lang.String endAirport, javax.xml.datatype.XMLGregorianCalendar startDate) {
        airline.ws.AirlineService service = new airline.ws.AirlineService();
        airline.ws.AirlineController port = service.getAirlineControllerPort();
        return port.getFlights(startAirport, endAirport, startDate);
    }

    
    
}
