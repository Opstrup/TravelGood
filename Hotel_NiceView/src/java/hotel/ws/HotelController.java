/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.ws;

import java.util.Date;
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 *
 * @author arhjo
 */
@WebService(serviceName = "HotelService")
public class HotelController {
    private HotelModel hotelModel;
    
    
    public HotelController(){
        hotelModel = new HotelModel();
    }
    
    @WebMethod(operationName ="getHotels")
    public List<HotelInformation> getHotels(@WebParam(name="city") String city,@WebParam (name = "arrivalDate") Date arrivalDate, @WebParam (name="departureDate") Date departureDate){
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
