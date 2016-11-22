/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.ws;

import java.util.Date;
import java.util.Random;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 *
 * @author arhjo
 */
public class HotelInformation {
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
    
    public void setBookingNumber(int bookingNumber) {
        this.bookingNumber = bookingNumber;
    }

    public void setPriceForStay(int priceForStay) {
        this.priceForStay = priceForStay;
    }

    public void setNameOfHotelService(String nameOfHotelService) {
        this.nameOfHotelService = nameOfHotelService;
    }
    
    public void setHotel(Hotel hotel){
        this.hotel = hotel;
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
    

    public void calculatePrice(XMLGregorianCalendar arrivalDate, XMLGregorianCalendar departureDate) {
        //long diff = departureDate. - arrivalDate.getTime();
       // this.priceForStay =  (int) (hotel.getPricePerDay()*(diff/86400000)); /*Miliseconds pr. day, rewrite is needed to find price*/
        this.priceForStay = (int)(departureDate.toGregorianCalendar().compareTo(arrivalDate.toGregorianCalendar()) / 86400000);
    }

    public int getBookingNumber() {
        return bookingNumber;
    }
}
