/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.ws;
/**
 *
 * @author arhjo
 */
public class Hotel {
    private String hotelName;
    private Address address;
    private float pricePerDay; // Consider price per day
    private boolean creditCardNeeded;

    
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

      public void setAddress(Address address) {
        this.address = address;
    }

    public void setCreditCardNeeded(boolean creditCardNeeded) {
        this.creditCardNeeded = creditCardNeeded;
    }
    
    public void setHotelName(String name){
        this.hotelName = name;
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
