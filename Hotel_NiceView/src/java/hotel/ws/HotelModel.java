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
    
    
    
}
