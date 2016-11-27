package hotel.ws;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.datatype.XMLGregorianCalendar;
import hotel.ws.Address;
import hotel.ws.Hotel;
import hotel.ws.HotelInformation;
import hotel.ws.HotelModel;

/**
 * @author arhjo
 */
@WebService(serviceName = "HotelService")
public class HotelController {
    public HotelModel hotelModel;
    public HotelController(){
        hotelModel = new HotelModel();
        hotelModel.hotelDB.add(new Hotel("NiceView1", new Address("Paris", "Somestreet 44"), 125, true));
        hotelModel.hotelDB.add(new Hotel("NiceView2", new Address("Paris", "Somestreet 11"), 300, false));
        hotelModel.hotelDB.add(new Hotel("NiceView3", new Address("Copenhagen", "Somestreet 22"), 525, true));
        hotelModel.hotelDB.add(new Hotel("NiceView4", new Address("Copenhagen", "Somestreet 33"), 425, false));
        hotelModel.hotelDB.add(new Hotel("NiceView5", new Address("Milan", "Somestreet 55"), 310, false));
    }
    
    @WebMethod(operationName ="getHotels")
    public List<hotel.ws.HotelInformation> getHotels(@WebParam(name="city") String city,@WebParam (name = "arrivalDate") XMLGregorianCalendar arrivalDate, @WebParam (name="departureDate") XMLGregorianCalendar departureDate){
        return hotelModel.getHotels(city, arrivalDate, departureDate);
    }
    
    @WebMethod(operationName ="bookHotel")
    public boolean bookHotel(@WebParam (name = "bookingNumber") int bookingNumber, 
                             @WebParam (name = "CreditCardInformation") bankservice.ws.CreditCardInfoType ccInfo) throws bankservice.ws.CreditCardFaultMessage, Exception{
        if (!hotelModel.bookHotel(bookingNumber, ccInfo))
            throw new Exception("Not able to book hotel");
        else
            return true;
    }
    
    @WebMethod(operationName = "cancelHotel")
    public void cancelHotel(@WebParam(name = "bookingNumber") int bookingNumber) throws Exception{
        if (!hotelModel.cancelHotel(bookingNumber))
            throw new Exception("Not able to cancel booked hotel");
    }
}
