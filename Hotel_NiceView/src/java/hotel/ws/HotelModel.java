package hotel.ws;

import hotel.ws.exception.HotelBookException;
import hotel.ws.exception.HotelCancelException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * @author arhjo
 */
public class HotelModel {
    List<hotel.ws.Hotel> hotelDB;
    List<hotel.ws.HotelInformation> hotelInformationDB;
    
    
    public HotelModel(){
        hotelDB = new ArrayList<>();
        hotelInformationDB = new ArrayList<>();
    }
    
    public List<hotel.ws.HotelInformation> getHotels(String city, XMLGregorianCalendar arrivalDate, XMLGregorianCalendar departureDate){
        List<hotel.ws.HotelInformation> results = new ArrayList<>();
        for(Hotel hotel : hotelDB)
            if(hotel.getAddress().getCity().equals(city)){
                hotel.ws.HotelInformation hotelInfo = new hotel.ws.HotelInformation(hotel);
                hotelInfo.setPriceForStay(hotel.getPricePerDay());
                results.add(hotelInfo);
                hotelInformationDB.add(hotelInfo);
            }
        
        return results;
    }
    
    public boolean bookHotel(int bookingNumber, bankservice.ws.CreditCardInfoType ccInfo) throws HotelBookException{
        for(HotelInformation hotelInfo : hotelInformationDB)
            if(hotelInfo.getBookingNumber() == bookingNumber && hotelInfo.getStatus() == HotelInformation.BookingStatus.UNCONFIRMED){
                
                if(hotelInfo.getHotel().getAddress().getCity().equals("BookingFailure"))
                        throw new HotelBookException("Booking of hotel failed by design");
                
//                if(hotelInfo.getHotel().isCreditCardNeeded()){
//                    
//                    try{
//                        validateCreditCard(7, ccInfo, hotelInfo.getPriceForStay());
//                    } catch(bankservice.ws.CreditCardFaultMessage e){
//                        throw new HotelBookException("Validation of credit card failed!");
//                    }    
//                }
                hotelInfo.setStatus(HotelInformation.BookingStatus.BOOKED);
                return true;
            }
        throw new HotelBookException("No Hotel with provided booking number");      
    }
    
    public boolean cancelHotel(int bookingNumber) throws HotelCancelException{
        for(HotelInformation hotelInfo: hotelInformationDB){
            if(hotelInfo.getBookingNumber() == bookingNumber && hotelInfo.getStatus() == HotelInformation.BookingStatus.BOOKED){
                if(hotelInfo.getHotel().getAddress().getCity().equals("CancelingFailure"))
                    throw new HotelCancelException("Canceling of hotel failed by design");
                else{
                    hotelInfo.setStatus(HotelInformation.BookingStatus.CANCELLED);
                    return true;
                }
            }
        }
        
        throw new HotelCancelException("This hotel can't be cancelled");
    }
    
    private static boolean validateCreditCard(int group, bankservice.ws.CreditCardInfoType creditCardInfo, int amount) throws bankservice.ws.CreditCardFaultMessage {
        bankservice.ws.BankService service = new bankservice.ws.BankService();
        bankservice.ws.BankPortType port = service.getBankPort();
        return port.validateCreditCard(group, creditCardInfo, amount);
    }
}
