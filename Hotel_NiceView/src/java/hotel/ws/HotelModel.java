/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.ws;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author arhjo
 */
public class HotelModel {
    List<HotelInformation> hotelInformationDB;
    
    public HotelModel(){
        hotelInformationDB = new ArrayList<>();
    }
    
    public List<HotelInformation> getHotels(String city, Date arrivalDate, Date departureDate){
        List<HotelInformation> resultList = new ArrayList<>();
        for(HotelInformation hotelInfo : hotelInformationDB){
            if(hotelInfo.getHotel().getAddress().getCity().equals(city) && !hotelInfo.getHotel().fullyBooked(arrivalDate,departureDate)){
                hotelInfo.calculatePrice(arrivalDate, departureDate);
                resultList.add(hotelInfo);
            }
        }
        return resultList;
    }
    
    public boolean bookHotel(int bookingNumber, CreditCardInfoType ccInfo) throws CreditCardFaultMessage{
        for(HotelInformation hotelInfo : hotelInformationDB){
            if(hotelInfo.getBookingNumber() == bookingNumber){
                if(hotelInfo.needsCreditCardGuarantee()){
                    return validateCreditCard(07, ccInfo, bookingNumber);
                }else{
                    return true;
                }
            }
        }
        return false;       
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
