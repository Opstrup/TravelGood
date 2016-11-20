package airline.ws;

import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import org.hamcrest.core.Is;
import org.junit.Test;
import static org.junit.Assert.*;

public class AirlineTests{
        
    @Test
    public void should_return_arrayList_test() throws IOException {
        AirlineModel UUT = new AirlineModel("flightDB.txt");
        List <FlightInformation> flightInfoList = new ArrayList<>();
        
        assertTrue(UUT.getFlights(null, null, null) instanceof ArrayList);
    }
    
    @Test
    public void should_return_empty_arrayList_test() throws IOException {
        AirlineModel UUT = new AirlineModel("flightDB.txt");
        assertTrue(UUT.getFlights(null, null, null).isEmpty());
    }
    
    @Test
    public void should_return_arrayList_with_one_element_test() throws IOException {
        // db contains: copenhagen;rome;2016-08-10;2016-09-10;Ryanair;10
        AirlineModel UUT = new AirlineModel("flightDB.txt");
        XMLGregorianCalendar departureDate = null;
        
        try {
            departureDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar(2016, 8, 10));
        } catch (DatatypeConfigurationException ex) {
            Logger.getLogger(AirlineTests.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        List<FlightInformation> result = UUT.getFlights("copenhagen", "rome", departureDate);
        assertTrue(result.size() > 0);
    }
}
