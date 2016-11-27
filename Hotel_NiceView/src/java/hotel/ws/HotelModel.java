package hotel.ws;

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
        hotelDB.add(new Hotel("NiceView1", new Address("copenhagen", "Somestreet 5"), 560,false));
        hotelDB.add(new Hotel("NiceView2", new Address("copenhagen", "Somestreet 55"), 200,true));
        hotelDB.add(new Hotel("NiceView3", new Address("østerlars","Somestreet 60"),110,true));
        hotelInformationDB = new ArrayList<>();
    }
    
    public List<hotel.ws.HotelInformation> getHotels(String city, XMLGregorianCalendar arrivalDate, XMLGregorianCalendar departureDate){
        List<hotel.ws.HotelInformation> results = new ArrayList<>();
        for(Hotel hotel : hotelDB){
            if(hotel.getAddress().getCity().equals(city)){
                hotel.ws.HotelInformation hotelInfo = new hotel.ws.HotelInformation(hotel);
                hotelInfo.calculatePrice(arrivalDate, departureDate);
                results.add(hotelInfo);
                hotelInformationDB.add(hotelInfo);
            }
        }
        return results;
    }
    
    public boolean bookHotel(int bookingNumber, bankservice.ws.CreditCardInfoType ccInfo) throws bankservice.ws.CreditCardFaultMessage{
        for(HotelInformation hotelInfo : hotelInformationDB){
            if(hotelInfo.getBookingNumber() == bookingNumber){
                if(hotelInfo.getHotel().isCreditCardNeeded()){
                     if(validateCreditCard(7, ccInfo, hotelInfo.getPriceForStay())){
                         hotelInfo.setStatus("Confirmed");
                         return true;
                     }
                }else{
                    hotelInfo.setStatus("Confirmed");
                    return true;
                }
            }
        }
        return false;       
    }
    
    public void cancelHotel(int bookingNumber){
        for(HotelInformation hotelInfo: hotelInformationDB){
            if(hotelInfo.getBookingNumber() == bookingNumber){
                hotelInfo.setStatus("Cancelled");
            }
        }
    }
    
    private static boolean validateCreditCard(int group, bankservice.ws.CreditCardInfoType creditCardInfo, int amount) throws bankservice.ws.CreditCardFaultMessage {
        bankservice.ws.BankService service = new bankservice.ws.BankService();
        bankservice.ws.BankPortType port = service.getBankPort();
        return port.validateCreditCard(group, creditCardInfo, amount);
    }
}
