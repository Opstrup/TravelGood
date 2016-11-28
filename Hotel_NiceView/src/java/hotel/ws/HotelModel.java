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
            if(hotelInfo.getBookingNumber() == bookingNumber){
                if(hotelInfo.getHotel().isCreditCardNeeded()){
                    try{
                        validateCreditCard(7, ccInfo, hotelInfo.getPriceForStay());
                    } catch(bankservice.ws.CreditCardFaultMessage e){
                        throw new HotelBookException("Validation of credit card failed!");
                    }    
                }
                hotelInfo.setStatus("Confirmed");
                return true;
            }
        throw new HotelBookException("No Hotel with provided booking number");      
    }
    
    public boolean cancelHotel(int bookingNumber) throws HotelCancelException{
        for(HotelInformation hotelInfo: hotelInformationDB){
            if(hotelInfo.getBookingNumber() == bookingNumber && hotelInfo.getStatus() == "Confirmed"){
                if(hotelInfo.getHotel().getHotelName().equals("Failing Hotel"))
                    throw new HotelCancelException("Canceling the booked hotel failed");
                else{
                    hotelInfo.setStatus("Cancelled");
                    return true;
                }
            }
        }
        
        throw new HotelCancelException("No hotel with provided booking number");
    }
    
    private static boolean validateCreditCard(int group, bankservice.ws.CreditCardInfoType creditCardInfo, int amount) throws bankservice.ws.CreditCardFaultMessage {
        bankservice.ws.BankService service = new bankservice.ws.BankService();
        bankservice.ws.BankPortType port = service.getBankPort();
        return port.validateCreditCard(group, creditCardInfo, amount);
    }
}
