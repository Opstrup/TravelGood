/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.ws;

import java.util.Date;
import java.util.Random;

/**
 *
 * @author arhjo
 */
class HotelInformation {
    private Hotel hotel;
    private int bookingNumber;
    private int priceForStay;
    private String nameOfHotelService = "NiceView";
    private String status;

    
    
    public HotelInformation(Hotel hotel) {
        this.hotel = hotel;
        bookingNumber = new Random().nextInt(50) +1;
        status = "Unconfirmed";
    }
       public Hotel getHotel() {
        return hotel;
    }
       
       
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
       
    public int getPriceForStay() {
        return priceForStay;
    }

    public String getNameOfHotelService() {
        return nameOfHotelService;
    }    
    

    public void calculatePrice(Date arrivalDate, Date departureDate) {
        long diff = departureDate.getTime() - arrivalDate.getTime();
        this.priceForStay =  (int) (hotel.getPricePerDay()*(diff/86400000)); /*Miliseconds pr. day, rewrite is needed to find price*/
    }

    public int getBookingNumber() {
        return bookingNumber;
    }
}
