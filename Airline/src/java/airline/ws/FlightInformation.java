package airline.ws;

import java.util.Random;

public class FlightInformation {
    private int bookingNumber;
    
    private String reservationAgency;
    private Flight flight;
    private String status;
    
    public FlightInformation(Flight flight) {
        bookingNumber = new Random().nextInt(50) +1;
        reservationAgency = "LameDuck";
        this.flight = flight;
        status = "Unconfirmed";
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
    public String getStatus(){
        return status;
    }
    public void setStatus(String status){
        this.status = status;
    }
}
