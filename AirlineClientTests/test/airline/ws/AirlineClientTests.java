package airline.ws;

import dk.dtu.imm.fastmoney.types.CreditCardInfoType;
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
    
    @Test
    public void should_validate_card_book_flight () throws CreditCardFaultMessage {
        XMLGregorianCalendar depDate = null;
        try {
            depDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar(2016, 11, 26));
        } catch (DatatypeConfigurationException ex) {
            Logger.getLogger(AirlineClientTests.class.getName()).log(Level.SEVERE, null, ex);
        }
        String startAirport = "Copenhagen";
        String endAirport = "Rome";
        
        java.util.List<airline.ws.FlightInformation> result = getFlights(startAirport, endAirport, depDate);
        FlightInformation testFlightInformation = result.get(0);
        dk.dtu.imm.fastmoney.types.CreditCardInfoType ccit = new CreditCardInfoType();
        dk.dtu.imm.fastmoney.types.CreditCardInfoType.ExpirationDate expDate = new dk.dtu.imm.fastmoney.types.CreditCardInfoType.ExpirationDate();
        expDate.setMonth(5);
        expDate.setYear(9);
                
        // Valid card info
        ccit.setExpirationDate(expDate);
        ccit.setName("Thor-Jensen Claus");
        ccit.setNumber("50408825");
        
        assertTrue(bookFlight(testFlightInformation.bookingNumber, ccit));
    }
    
    @Test (expected=CreditCardFaultMessage.class) 
    public void should_not_validate_card_book_flight () throws CreditCardFaultMessage{
        XMLGregorianCalendar depDate = null;
        try {
            depDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar(2016, 11, 26));
        } catch (DatatypeConfigurationException ex) {
            Logger.getLogger(AirlineClientTests.class.getName()).log(Level.SEVERE, null, ex);
        }
        String startAirport = "Copenhagen";
        String endAirport = "Rome";
        
        java.util.List<airline.ws.FlightInformation> result = getFlights(startAirport, endAirport, depDate);
        FlightInformation testFlightInformation = result.get(0);
        dk.dtu.imm.fastmoney.types.CreditCardInfoType ccit = new CreditCardInfoType();
        bookFlight(testFlightInformation.bookingNumber, ccit);
    }
    
    @Test
    public void should_book_flight () throws CreditCardFaultMessage {
        XMLGregorianCalendar depDate = null;
        try {
            depDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar(2016, 11, 26));
        } catch (DatatypeConfigurationException ex) {
            Logger.getLogger(AirlineClientTests.class.getName()).log(Level.SEVERE, null, ex);
        }
        String startAirport = "Copenhagen";
        String endAirport = "Rome";
        
        java.util.List<airline.ws.FlightInformation> result = getFlights(startAirport, endAirport, depDate);
        FlightInformation testFlightInformation = result.get(0);
        dk.dtu.imm.fastmoney.types.CreditCardInfoType ccit = new CreditCardInfoType();
        dk.dtu.imm.fastmoney.types.CreditCardInfoType.ExpirationDate expDate = new dk.dtu.imm.fastmoney.types.CreditCardInfoType.ExpirationDate();
        expDate.setMonth(5);
        expDate.setYear(9);
                
        // Valid card info
        ccit.setExpirationDate(expDate);
        ccit.setName("Thor-Jensen Claus");
        ccit.setNumber("50408825");
        
        assertTrue(bookFlight(testFlightInformation.bookingNumber, ccit));
    }
    
    @Test (expected=airline.ws.CreditCardFaultMessage.class) 
    public void should_throw_insufficient_funds_fault_book_flight () throws CreditCardFaultMessage {
        XMLGregorianCalendar depDate = null;
        try {
            depDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar(2016, 11, 26));
        } catch (DatatypeConfigurationException ex) {
            Logger.getLogger(AirlineClientTests.class.getName()).log(Level.SEVERE, null, ex);
        }
        String startAirport = "Copenhagen";
        String endAirport = "Rome";
        
        java.util.List<airline.ws.FlightInformation> result = getFlights(startAirport, endAirport, depDate);
        FlightInformation testFlightInformation = result.get(1);
        dk.dtu.imm.fastmoney.types.CreditCardInfoType ccit = new CreditCardInfoType();
        dk.dtu.imm.fastmoney.types.CreditCardInfoType.ExpirationDate expDate = new dk.dtu.imm.fastmoney.types.CreditCardInfoType.ExpirationDate();
        expDate.setMonth(7);
        expDate.setYear(9);
                
        // Valid card info
        ccit.setExpirationDate(expDate);
        ccit.setName("Bech Camilla");
        ccit.setNumber("50408822");
        
        testFlightInformation.getFlight().setFlightPrice(999999);
        bookFlight(testFlightInformation.bookingNumber, ccit);
    }
    
    @Test
    public void should_cancel_a_booked_flight () throws CreditCardFaultMessage, Exception_Exception {
        XMLGregorianCalendar depDate = null;
        try {
            depDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar(2016, 11, 26));
        } catch (DatatypeConfigurationException ex) {
            Logger.getLogger(AirlineClientTests.class.getName()).log(Level.SEVERE, null, ex);
        }
        String startAirport = "Copenhagen";
        String endAirport = "Rome";
        
        java.util.List<airline.ws.FlightInformation> result = getFlights(startAirport, endAirport, depDate);
        FlightInformation testFlightInformation = result.get(0);
        dk.dtu.imm.fastmoney.types.CreditCardInfoType ccit = new CreditCardInfoType();
        dk.dtu.imm.fastmoney.types.CreditCardInfoType.ExpirationDate expDate = new dk.dtu.imm.fastmoney.types.CreditCardInfoType.ExpirationDate();
        expDate.setMonth(5);
        expDate.setYear(9);
                
        // Valid card info
        ccit.setExpirationDate(expDate);
        ccit.setName("Thor-Jensen Claus");
        ccit.setNumber("50408825");
        
        bookFlight(testFlightInformation.bookingNumber, ccit);
        
        cancelFlight(testFlightInformation.bookingNumber, testFlightInformation.getFlight().getFlightPrice(), ccit);
    }

    private static boolean bookFlight(int bookingNumber, dk.dtu.imm.fastmoney.types.CreditCardInfoType creditCardInformation) throws CreditCardFaultMessage {
        airline.ws.AirlineService service = new airline.ws.AirlineService();
        airline.ws.AirlineController port = service.getAirlineControllerPort();
        return port.bookFlight(bookingNumber, creditCardInformation);
    }

    private static void cancelFlight(int bookingNumber, int flightPrice, dk.dtu.imm.fastmoney.types.CreditCardInfoType creditCardInformation) throws CreditCardFaultMessage, Exception_Exception {
        airline.ws.AirlineService service = new airline.ws.AirlineService();
        airline.ws.AirlineController port = service.getAirlineControllerPort();
        port.cancelFlight(bookingNumber, flightPrice, creditCardInformation);
    }

    private static java.util.List<airline.ws.FlightInformation> getFlights(java.lang.String startAirport, java.lang.String endAirport, java.lang.Object startDate) {
        airline.ws.AirlineService service = new airline.ws.AirlineService();
        airline.ws.AirlineController port = service.getAirlineControllerPort();
        return port.getFlights(startAirport, endAirport, startDate);
    }
}
