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
public class Hotel {
    private String hotelName;
    //private String address //simpler?
    private Address address;
    private float pricePerDay; // Consider price per day
    private List<OwnPeriod> fullyBooked = new ArrayList<>(); 

    public List<OwnPeriod> getFullyBooked() {
        return fullyBooked;
    }


    //I would also include fullyBooked in the constructor parameters
    // to ease the process of population of the DB!
    public Hotel(String hotelName, Address address, float pricePerDay){
        this.hotelName = hotelName;
        this.address = address;
        this.pricePerDay = pricePerDay;
        
    }

    //if fullyBooked is in the constructor this becomes useless
    public void addFullyBookedPeriode(OwnPeriod period){
        fullyBooked.add(period);
    }

    public String getHotelName() {
        return hotelName;
    }

    public Address getAddress() {
        return address;
    }

    public float getPricePerDay() {
        return pricePerDay;
    }
    
    // A = period
    // B = Parameters

    boolean fullyBooked(Date arrivalDate, Date departureDate) {
        for(OwnPeriod period : fullyBooked){
            if(arrivalDate.after(period.getStartDate()) && departureDate.after(period.getEndDate())){
                return true;
            }else if(arrivalDate.before(period.getStartDate())&& departureDate.before(period.getEndDate())){
                return true;
            }else if(arrivalDate.after(period.getStartDate()) && departureDate.before(period.getEndDate())){
                return true;
            }
        }
       return false;
    }
}
