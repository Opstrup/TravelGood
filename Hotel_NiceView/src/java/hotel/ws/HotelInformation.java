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
    private String hotelName; //not necessary (already in hotel?)
    private Address hotelAddress; //not necessary
    private int bookingNumber;
    private float priceForStay;
    private boolean creditCardGuarantee;
    private String nameOfHotelService;

    public HotelInformation(Hotel hotel, boolean creditCardGuarantee, String nameOfHotelService) {
        this.hotel = hotel;
        this.hotelName = hotel.getHotelName();
        this.hotelAddress = hotel.getAddress();
        bookingNumber = new Random().nextInt(50) +1;
        this.creditCardGuarantee = creditCardGuarantee;
        this.nameOfHotelService = nameOfHotelService;
    }
       public Hotel getHotel() {
        return hotel;
    }

    public String getHotelName() {
        return hotelName;
    }

    public Address getHotelAddress() {
        return hotelAddress;
    }

    public int getBookingNumber() {
        return bookingNumber;
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
