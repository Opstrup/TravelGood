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
        Hotel hotel1 = new Hotel("NiceView1", new Address("Copenhagen", "Somestreet 5"), 560);
        Hotel hotel2 = new Hotel("NiceView2", new Address("Copenhagen", "Somestreet 55"), 200);

        //the constructor Date(int, int) is deprecated.
        // use instead:
        // Date departureDate = new GregorianCalendar(2014, Calendar.FEBRUARY, 11).getTime();
        hotel1.addFullyBookedPeriode(new OwnPeriod(new Date(2016,10,1), new Date(2016,10,30)));
        
        hm = new HotelModel();
        hm.hotelInformationDB.add(new HotelInformation(hotel1, true, "NiceView"));
        hm.hotelInformationDB.add(new HotelInformation(hotel2, true, "NiceView"));
        
        
    }
    
    @Test
    public void firstTest(){
       List<HotelInformation> result = hm.getHotels("Copenhagen", new Date(2016,10,02), new Date(2016,10,05));
        
       assertEquals(1, result.size());
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
