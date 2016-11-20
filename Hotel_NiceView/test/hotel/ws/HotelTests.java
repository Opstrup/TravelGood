/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.ws;

import java.util.Date;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author arhjo
 */
public class HotelTests {
    HotelModel hm;
    
    public HotelTests() {
        Hotel hotel1 = new Hotel("NiceView1", new Address("Copenhagen", "Somestreet 5"), 560,false);
        Hotel hotel2 = new Hotel("NiceView2", new Address("Copenhagen", "Somestreet 55"), 200,true);
        Hotel hotel3 = new Hotel("NiceView3", new Address("Østerlars","Somestreet 60"),110,true);

        //the constructor Date(int, int) is deprecated.
        // use instead:
        // Date departureDate = new GregorianCalendar(2014, Calendar.FEBRUARY, 11).getTime();
        
        hm = new HotelModel();
        hm.hotelDB.add(hotel1);
        hm.hotelDB.add(hotel2);
        hm.hotelDB.add(hotel3);
    }
    
    @Test
    public void getHotelsTest1(){
       List<HotelInformation> result = hm.getHotels("Copenhagen", new Date(2016,10,2), new Date(2016,10,5));
       assertEquals(2, result.size());
    }
    
    @Test
    public void getHotelsTest2(){
        List<HotelInformation> result = hm.getHotels("Rønne", new Date(2016,11,20),new Date(2016,11,24));
        assertEquals(0, result.size());
    }
    
    @Test 
    public void getHotelsTest3(){   
        List<HotelInformation> result = hm.getHotels("Østerlars", new Date(2016,11,20), new Date(2016,11,24));
        assertEquals(1, result.size());
    }
    
    @Test
    public void bookHotelTest1() throws CreditCardFaultMessage{
       List<HotelInformation> result = hm.getHotels("Copenhagen", new Date(2016,11,11), new Date(2016,11,12));
       assertEquals(2, result.size());
       
       HotelInformation hotelWithoutCreditGuarantee = null;
       for(HotelInformation hotelInfo: result){
           if(!hotelInfo.getHotel().isCreditCardNeeded()){
               hotelWithoutCreditGuarantee = hotelInfo;
           }
       }
       CreditCardInfoType ccInfo = null;
       boolean res = hm.bookHotel(hotelWithoutCreditGuarantee.getBookingNumber(),ccInfo);
    }
    
    
    @Test
    public void bookHotelTest2() throws CreditCardFaultMessage{
       List<HotelInformation> result = hm.getHotels("Copenhagen", new Date(2016,11,11), new Date(2016,11,12));
       assertEquals(2, result.size());
       
       HotelInformation hotelWithCreditGuarantee = null;
       for(HotelInformation hotelInfo: result){
           if(hotelInfo.getHotel().isCreditCardNeeded()){
               hotelWithCreditGuarantee = hotelInfo;
           }
       }
       
       
       CreditCardInfoType ccInfo = new hotel.ws.CreditCardInfoType();
       hotel.ws.CreditCardInfoType.ExpirationDate expDate = new hotel.ws.CreditCardInfoType.ExpirationDate();
       expDate.setMonth(10);
       expDate.setYear(17);
       
       ccInfo.setExpirationDate(expDate);
       ccInfo.setName("Luca");
       ccInfo.setNumber("12345678");
       
       hm.bookHotel(hotelWithCreditGuarantee.getBookingNumber(), ccInfo);
       
       
       
    }
    
    
    
    
    
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
