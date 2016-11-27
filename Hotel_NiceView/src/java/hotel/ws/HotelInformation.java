package hotel.ws;

import java.util.Date;
import java.util.Random;
import javax.xml.datatype.XMLGregorianCalendar;

/**
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
   /* public void calculatePrice(XMLGregorianCalendar arrivalDate, XMLGregorianCalendar departureDate) {
        this.priceForStay = (int)(departureDate.toGregorianCalendar().compareTo(arrivalDate.toGregorianCalendar()) / 86400000)*hotel.getPricePerDay();
    }*/
    public int getBookingNumber() {
        return bookingNumber;
    }
}
