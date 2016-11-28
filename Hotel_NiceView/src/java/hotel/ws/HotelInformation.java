package hotel.ws;

import java.util.Date;
import java.util.Random;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * @author arhjo
 */
public class HotelInformation {
    
    public enum BookingStatus {
        UNCONFIRMED, BOOKED, CANCELLED
    }
    
    private Hotel hotel;
    private int bookingNumber;   
    private int priceForStay;
    private String nameOfHotelService = "NiceView";
    private BookingStatus status;

    public HotelInformation(Hotel hotel) {
        this.hotel = hotel;
        bookingNumber = new Random().nextInt(50) +1;
        status = BookingStatus.UNCONFIRMED;
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
    public BookingStatus getStatus() {
        return status;
    }
    public void setStatus(BookingStatus status) {
        this.status = status;
    } 
    public int getPriceForStay() {
        return priceForStay;
    }

    public String getNameOfHotelService() {
        return nameOfHotelService;
    }    
   
    public int getBookingNumber() {
        return bookingNumber;
    }
}
