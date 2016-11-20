/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.ws;

import java.util.ArrayList;
import java.util.List;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 *
 * @author arhjo
 */
public class HotelModel {
    List<Hotel> hotelDB;
    List<HotelInformation> hotelInformationDB;
    
    
    public HotelModel(){
        hotelDB = new ArrayList<>();
        hotelInformationDB = new ArrayList<>();

    }
    
    public List<HotelInformation> getHotels(String city, XMLGregorianCalendar arrivalDate, XMLGregorianCalendar departureDate){
        List<HotelInformation> results = new ArrayList<>();
        for(Hotel hotel : hotelDB){
            if(hotel.getAddress().getCity().equals(city)){
                HotelInformation hotelInfo = new HotelInformation(hotel);
                hotelInfo.calculatePrice(arrivalDate, departureDate);
                results.add(hotelInfo);
                hotelInformationDB.add(hotelInfo);
            }
        }
        return results;
    }
    
    public boolean bookHotel(int bookingNumber, CreditCardInfoType ccInfo) throws CreditCardFaultMessage{
        for(HotelInformation hotelInfo : hotelInformationDB){
            if(hotelInfo.getBookingNumber() == bookingNumber){
                if(hotelInfo.getHotel().isCreditCardNeeded()){
                     if(validateCreditCard(7, ccInfo, hotelInfo.getPriceForStay())){
                         hotelInfo.setStatus("Confirmed");
                         return true;
                     }
                }else{
                    return true;
                }
            }
        }
        //TODO: Throw exception if it does not succeed
        return false;       
    }
    
    public void cancelHotel(int bookingNumber){
        for(HotelInformation hotelInfo: hotelInformationDB){
            if(hotelInfo.getBookingNumber() == bookingNumber){
                hotelInfo.setStatus("Cancelled");
            }
        }
    }
    
 //   The bookHotel operation takes a booking number and credit card information (depending
 //   on whether a credit card guarantee is required or not) and books the hotel. If a guarantee is required, the
 //   operation validateCreditCard from the Bank service is called. The bookHotel operation returns true, if
 //   the booking was successful, and returns a fault (i.e., throws an exception) if the credit card information
 //   was not valid, there was not enough money on the client account, or if for other reasons the booking
 //   fails.

    private static boolean validateCreditCard(int group, hotel.ws.CreditCardInfoType creditCardInfo, int amount) throws CreditCardFaultMessage {
        hotel.ws.BankService service = new hotel.ws.BankService();
        hotel.ws.BankPortType port = service.getBankPort();
        return port.validateCreditCard(group, creditCardInfo, amount);
    }
    
}
