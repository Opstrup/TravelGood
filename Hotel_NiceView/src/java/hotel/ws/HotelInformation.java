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
    private float priceForStay;
    private boolean creditCardGuarantee;
    private String nameOfHotelService = "NiceView";

    public HotelInformation(Hotel hotel, boolean creditCardGuarantee) {
        this.hotel = hotel;
        bookingNumber = new Random().nextInt(50) +1;
        this.creditCardGuarantee = creditCardGuarantee;
    }
       public Hotel getHotel() {
        return hotel;
    }

    public float getPriceForStay() {
        return priceForStay;
    }

    public boolean needsCreditCardGuarantee() {
        return creditCardGuarantee;
    }

    public String getNameOfHotelService() {
        return nameOfHotelService;
    }    
    
    public void setPriceForStay(float priceForStay) {
        this.priceForStay = priceForStay;
    }

    public void calculatePrice(Date arrivalDate, Date departureDate) {
        long diff = departureDate.getTime() - arrivalDate.getTime();
        this.priceForStay =  hotel.getPricePerDay()*(diff/86400000); /*Miliseconds pr. day, rewrite is needed to find price*/
    }
}
