package airline.ws;

import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import org.junit.Test;
import static org.junit.Assert.*;

public class AirlineTests{
        
    @Test
    public void should_return_arrayList_test() throws IOException {
        AirlineModel UUT = new AirlineModel();
        
        assertTrue(UUT.getFlights(null, null, null) instanceof ArrayList);
    } 
    @Test
    public void should_return_empty_arrayList_test() throws IOException {
        AirlineModel UUT = new AirlineModel();
        assertTrue(UUT.getFlights(null, null, null).isEmpty());
    }
    @Test
    public void should_return_arrayList_with_one_element_test() throws IOException {
        AirlineModel UUT = new AirlineModel();
        XMLGregorianCalendar departureDate = null;
        
        try {
            departureDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar(2016, 11, 26));
        } catch (DatatypeConfigurationException ex) {
            Logger.getLogger(AirlineTests.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        List<FlightInformation> result = UUT.getFlights("Copenhagen", "Rome", departureDate);
        assertTrue(result.size() > 0);
    }
    
    @Test
    public void bookFlights() throws bankservice.ws.CreditCardFaultMessage, IOException {
        AirlineModel airlineModel = new AirlineModel();
        XMLGregorianCalendar departureDate = null;
        
        try {
            departureDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar(2016, 11, 26));
        } catch (DatatypeConfigurationException ex) {
            Logger.getLogger(AirlineTests.class.getName()).log(Level.SEVERE, null, ex);
        }
        List<FlightInformation> result = airlineModel.getFlights("Copenhagen", "Rome", departureDate);
        assertTrue(result.size() > 0);
        
        FlightInformation fligtWithoutCreditGuarantee = null;
    
        //Looking for flight with out card.
        for(FlightInformation flyInfo: result){
            if(!flyInfo.getFlight().isCreditCardNeeded()){
                fligtWithoutCreditGuarantee = flyInfo;
            }
        }
        bankservice.ws.CreditCardInfoType ccInfo = null;
       
        int flyId = fligtWithoutCreditGuarantee.getBookingNumber();
        boolean res = airlineModel.bookFlight(flyId, ccInfo);
        assertTrue(res);
    }
    
//    @Test
//    public void bookFlights1() throws bankservice.ws.CreditCardFaultMessage, IOException {
//        AirlineModel airlineModel = new AirlineModel();
//        XMLGregorianCalendar departureDate = null;
//        
//        try {
//            departureDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar(2016, 11, 26));
//        } catch (DatatypeConfigurationException ex) {
//            Logger.getLogger(AirlineTests.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        List<FlightInformation> result = airlineModel.getFlights("Copenhagen", "Rome", departureDate);
//        assertTrue(result.size() > 0);
//        
//        FlightInformation fligtWithoutCreditGuarantee = null;
//    
//        for(FlightInformation flyInfo: result){
//            if(flyInfo.getFlight().isCreditCardNeeded()) {
//                fligtWithoutCreditGuarantee = flyInfo;
//            }
//        }
//        bankservice.ws.CreditCardInfoType ccInfo = new bankservice.ws.CreditCardInfoType();
//        bankservice.ws.CreditCardInfoType.ExpirationDate expDate = new bankservice.ws.CreditCardInfoType.ExpirationDate();
//        expDate.setMonth(10);
//        expDate.setYear(17);
//       
//        ccInfo.setExpirationDate(expDate);
//        ccInfo.setName("Luca");
//        ccInfo.setNumber("12345678");
//       
//        int flyId = fligtWithoutCreditGuarantee.getBookingNumber();
//        boolean res = airlineModel.bookFlight(flyId, ccInfo);
//        assertTrue(res);
//    }
    
//    @Test 
//    public void bookFlights2() throws bankservice.ws.CreditCardFaultMessage, IOException {
//        AirlineModel airlineModel = new AirlineModel();
//        XMLGregorianCalendar departureDate = null;
//        
//        try {
//            departureDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar(2016, 11, 26));
//        } catch (DatatypeConfigurationException ex) {
//            Logger.getLogger(AirlineTests.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        List<FlightInformation> result = airlineModel.getFlights("Copenhagen", "Rome", departureDate);
//        assertTrue(result.size() > 0);
//        
//        FlightInformation fligtWithCreditGuarantee = null;
//    
//        for(FlightInformation flyInfo: result){
//           flyInfo.getFlight().isCreditCardNeeded() = true;
//            if(flyInfo.getFlight().isCreditCardNeeded()){
//               fligtWithCreditGuarantee = flyInfo;
//            }
//        }
//       
//        bankservice.ws.CreditCardInfoType ccInfo = new bankservice.ws.CreditCardInfoType();
//        bankservice.ws.CreditCardInfoType.ExpirationDate expDate = new bankservice.ws.CreditCardInfoType.ExpirationDate();
//        expDate.setMonth(10);
//        expDate.setYear(17);
//       
//        ccInfo.setExpirationDate(expDate);
//        ccInfo.setName("Luca");
//        ccInfo.setNumber("12345678");
//     
//        int flyId = fligtWithCreditGuarantee.getBookingNumber();
//        boolean res = airlineModel.bookFlight(flyId, ccInfo);
//        assertTrue(res);
//    }   
    
//    @Test
//    public void cancelFlight() throws bankservice.ws.CreditCardFaultMessage, IOException, Exception {
//        AirlineModel airlineModel = new AirlineModel();
//        XMLGregorianCalendar departureDate = null;
//        
//        try {
//            departureDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar(2016, 11, 26));
//        } catch (DatatypeConfigurationException ex) {
//            Logger.getLogger(AirlineTests.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        List<FlightInformation> result = airlineModel.getFlights("Copenhagen", "Rome", departureDate);
//        assertTrue(result.size() > 0);
//        
//        FlightInformation fligtWithNoCreditGuarantee = null;
//    
//        for(FlightInformation flyInfo: result){
//            if(!flyInfo.getFlight().isCreditCardNeeded()){
//               fligtWithNoCreditGuarantee = flyInfo;
//            }
//        }
//       
//        bankservice.ws.CreditCardInfoType ccInfo = new bankservice.ws.CreditCardInfoType();
//        bankservice.ws.CreditCardInfoType.ExpirationDate expDate = new bankservice.ws.CreditCardInfoType.ExpirationDate();
//        expDate.setMonth(10);
//        expDate.setYear(17);
//       
//        ccInfo.setExpirationDate(expDate);
//        ccInfo.setName("Luca");
//        ccInfo.setNumber("12345678");
//        fligtWithNoCreditGuarantee.setStatus("Confirmed");
//       
//        bankservice.ws.AccountType acount = new bankservice.ws.AccountType();
//        acount.setName("Luca");
//        acount.setNumber("455555555555555");
//               
//        int flyId = fligtWithNoCreditGuarantee.getBookingNumber();
//        boolean res = airlineModel.cancelFly(flyId, ccInfo, acount);
//        assertTrue(res);
//    }     
}
