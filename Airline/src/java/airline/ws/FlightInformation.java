package airline.ws;

import java.util.Random;

public class FlightInformation {
    
    public enum BookingStatus {
        UNCONFIRMED, BOOKED, CANCELLED
    }
    
    private int bookingNumber;
    
    private String reservationAgency;
    private Flight flight;
    public BookingStatus bookingStatus;
    
    public FlightInformation(Flight flight) {
        bookingNumber = new Random().nextInt(50) +1;
        reservationAgency = "LameDuck";
        this.flight = flight;
        bookingStatus = BookingStatus.UNCONFIRMED;
    }
    public int getBookingNumber() {
        return bookingNumber;
    }
    public void setBookingNumber(int bookingNumber) {
        this.bookingNumber = bookingNumber;
    }
    public String getReservationAgency() {
        return reservationAgency;
    }
    public void setReservationAgency(String reservationAgency) {
        this.reservationAgency = reservationAgency;
    }
    public Flight getFlight() {
        return flight;
    }
    public void setFlight(Flight flight) {
        this.flight = flight;
    }
    public BookingStatus getStatus(){
        return bookingStatus;
    }
    public void setStatus(BookingStatus status){
        this.bookingStatus = status;
    }
}
