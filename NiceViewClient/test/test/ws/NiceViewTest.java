package test.ws;

import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import niceview.ws.CreditCardFaultMessage;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author arhjo
 */
public class NiceViewTest {
    XMLGregorianCalendar arrivalDate = null;
    XMLGregorianCalendar departureDate = null;
    
    public NiceViewTest() {
    }  
    
    public void setDates(int arrivalYear, int arrivalMonth, int arrivalDay, int departureYear, int departureMonth, int departureDay){
        try {
            arrivalDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar(arrivalYear, arrivalMonth, arrivalDay));
            departureDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar(departureYear, departureMonth, departureDay));
        } catch (DatatypeConfigurationException ex) {
            Logger.getLogger(NiceViewTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void getHotelsTest () throws CreditCardFaultMessage{
        setDates(2016, 10, 11, 2016, 10, 13);
        List<niceview.ws.HotelInformation> results = getHotels("Paris", arrivalDate,departureDate);
        
        assertEquals(2, results.size());
        assertEquals("Paris", results.get(0).getHotel().getAddress().getCity());
        assertEquals("Paris", results.get(1).getHotel().getAddress().getCity());
    }
    
    
    @Test
    public void bookHotelTest () throws CreditCardFaultMessage{
        setDates(2016, 10, 11, 2016, 10, 13);
        List<niceview.ws.HotelInformation> results = getHotels("Paris", arrivalDate,departureDate);
        
        for(niceview.ws.HotelInformation hotelInfo: results){
            if(!hotelInfo.getHotel().isCreditCardNeeded()){

                assertEquals("UNCONFIRMED", hotelInfo.getStatus());
                
                niceview.ws.CreditCardInfoType ccInfo = new niceview.ws.CreditCardInfoType();
                niceview.ws.CreditCardInfoType.ExpirationDate expDate = new niceview.ws.CreditCardInfoType.ExpirationDate();
                expDate.setMonth(10);
                expDate.setYear(16);
                
                ccInfo.setExpirationDate(expDate);
                ccInfo.setName("Andreas");
                ccInfo.setNumber("12345678");
                
                
                System.out.println(hotelInfo.getBookingNumber());
                System.out.println(hotelInfo.getStatus());
                
                boolean bookingCompleted = bookHotel(hotelInfo.getBookingNumber(), ccInfo);
                
                System.out.println(bookingCompleted);
                System.out.println(hotelInfo.getStatus());
                //assertEquals("Confirmed", hotelInfo.getStatus());
                
            }
            
        }
        
        
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}

    private static java.util.List<niceview.ws.HotelInformation> getHotels(java.lang.String city, java.lang.Object arrivalDate, java.lang.Object departureDate) {
        niceview.ws.HotelService service = new niceview.ws.HotelService();
        niceview.ws.HotelController port = service.getHotelControllerPort();
        return port.getHotels(city, arrivalDate, departureDate);
    }

    private static boolean bookHotel(int bookingNumber, niceview.ws.CreditCardInfoType creditCardInformation) throws CreditCardFaultMessage {
        niceview.ws.HotelService service = new niceview.ws.HotelService();
        niceview.ws.HotelController port = service.getHotelControllerPort();
        return port.bookHotel(bookingNumber, creditCardInformation);
    }

    private static void cancelHotel(int bookingNumber) {
        niceview.ws.HotelService service = new niceview.ws.HotelService();
        niceview.ws.HotelController port = service.getHotelControllerPort();
        port.cancelHotel(bookingNumber);
    }
}
