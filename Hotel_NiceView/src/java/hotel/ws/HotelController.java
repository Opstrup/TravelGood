/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.ws;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 *
 * @author arhjo
 */
@WebService(serviceName = "HotelService")
public class HotelController {
    private HotelModel hotelModel;
    
    
    public HotelController(){
        hotelModel = new HotelModel();
        
        hotelModel.hotelDB.add(new Hotel("NiceView1", new Address("Paris", "Somestreet 44"), 125, true));
        hotelModel.hotelDB.add(new Hotel("NiceView2", new Address("Paris", "Somestreet 11"), 300, false));
        hotelModel.hotelDB.add(new Hotel("NiceView3", new Address("Copenhagen", "Somestreet 22"), 525, true));
        hotelModel.hotelDB.add(new Hotel("NiceView4", new Address("Copenhagen", "Somestreet 33"), 425, false));
        hotelModel.hotelDB.add(new Hotel("NiceView5", new Address("Milan", "Somestreet 55"), 310, false));
        
    }
    
    @WebMethod(operationName ="getHotels")
    public List<HotelInformation> getHotels(@WebParam(name="city") String city,@WebParam (name = "arrivalDate") XMLGregorianCalendar arrivalDate, @WebParam (name="departureDate") XMLGregorianCalendar departureDate){
        return hotelModel.getHotels(city, arrivalDate, departureDate);
    }
    
    @WebMethod(operationName ="bookHotel")
    public boolean bookHotel(@WebParam (name = "bookingNumber") int bookingNumber, @WebParam (name = "CreditCardInformation") CreditCardInfoType ccInfo) throws CreditCardFaultMessage{
        return hotelModel.bookHotel(bookingNumber, ccInfo);
    }
    
    @WebMethod(operationName = "cancelHotel")
    public void cancelHotel(@WebParam(name = "bookingNumber") int bookingNumber){
        hotelModel.cancelHotel(bookingNumber);
    }
}
