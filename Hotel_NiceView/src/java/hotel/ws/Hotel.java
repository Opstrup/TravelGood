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
    private boolean creditCardNeeded;

    
    //I would also include fullyBooked in the constructor parameters
    // to ease the process of population of the DB!
    public Hotel(String hotelName, Address address, float pricePerDay, boolean creditCardNeeded){
        this.hotelName = hotelName;
        this.address = address;
        this.pricePerDay = pricePerDay;
        this.creditCardNeeded = creditCardNeeded;
        
    }
    
    public boolean isCreditCardNeeded() {
        return creditCardNeeded;
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

}
