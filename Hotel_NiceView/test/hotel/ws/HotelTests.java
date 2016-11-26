package hotel.ws;

import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author arhjo
 */
public class HotelTests {
    HotelModel hm;
    
    XMLGregorianCalendar arrivalDate = null;
    XMLGregorianCalendar departureDate = null;
    
    public HotelTests() {
        Hotel hotel1 = new Hotel("NiceView1", new Address("Copenhagen", "Somestreet 5"), 560,false);
        Hotel hotel2 = new Hotel("NiceView2", new Address("Copenhagen", "Somestreet 55"), 200,true);
        Hotel hotel3 = new Hotel("NiceView3", new Address("Østerlars","Somestreet 60"),110,true);

        hm = new HotelModel();
        hm.hotelDB.add(hotel1);
        hm.hotelDB.add(hotel2);
        hm.hotelDB.add(hotel3);
    }
    
    public void setDates(int arrivalYear, int arrivalMonth, int arrivalDay, int departureYear, int departureMonth, int departureDay){
        try {
            arrivalDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar(arrivalYear, arrivalMonth, arrivalDay));
            departureDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar(departureYear, departureMonth, departureDay));
        } catch (DatatypeConfigurationException ex) {
            Logger.getLogger(HotelTests.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void getHotelsTest1(){
      
       setDates(2016, 11, 28, 2016, 12, 4);
       List<HotelInformation> result = hm.getHotels("Copenhagen", arrivalDate, departureDate);
       assertEquals(2, result.size());
    }
    
    @Test
    public void getHotelsTest2(){
        setDates(2016, 11, 28, 2016, 12, 4);
        List<HotelInformation> result = hm.getHotels("Rønne", arrivalDate,departureDate);
        assertEquals(0, result.size());
    }
    
    @Test 
    public void getHotelsTest3(){   
        setDates(2016, 11, 28, 2016, 12, 4);
        List<HotelInformation> result = hm.getHotels("Østerlars", arrivalDate, departureDate);
        assertEquals(1, result.size());
    }
    
    @Test
    public void bookHotelTest1() throws bankservice.ws.CreditCardFaultMessage{
       setDates(2016, 11, 28, 2016, 12, 4);
       List<HotelInformation> result = hm.getHotels("Copenhagen", arrivalDate, departureDate);
       assertEquals(2, result.size());
       
       HotelInformation hotelWithoutCreditGuarantee = null;
       for(HotelInformation hotelInfo: result){
           if(!hotelInfo.getHotel().isCreditCardNeeded()){
               hotelWithoutCreditGuarantee = hotelInfo;
           }
       }
       bankservice.ws.CreditCardInfoType ccInfo = null;
       boolean res = hm.bookHotel(hotelWithoutCreditGuarantee.getBookingNumber(),ccInfo);
    }
       
    @Test
    public void bookHotelTest2() throws bankservice.ws.CreditCardFaultMessage{
       setDates(2016, 11, 28, 2016, 12, 4);
       List<HotelInformation> result = hm.getHotels("Copenhagen", arrivalDate, departureDate);
       assertEquals(2, result.size());
       
       HotelInformation hotelWithCreditGuarantee = null;
       for(HotelInformation hotelInfo: result){
           if(hotelInfo.getHotel().isCreditCardNeeded()){
               hotelWithCreditGuarantee = hotelInfo;
           }
       }
           
       bankservice.ws.CreditCardInfoType ccInfo = new bankservice.ws.CreditCardInfoType();
       bankservice.ws.CreditCardInfoType.ExpirationDate expDate = new bankservice.ws.CreditCardInfoType.ExpirationDate();
       expDate.setMonth(10);
       expDate.setYear(17);
       
       ccInfo.setExpirationDate(expDate);
       ccInfo.setName("Luca");
       ccInfo.setNumber("12345678");
       
       hm.bookHotel(hotelWithCreditGuarantee.getBookingNumber(), ccInfo);  
    }
    
}
