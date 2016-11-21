package airline.ws;

import java.util.Random;

public class FlightInformation {
    private int bookingNumber;
    private int flightPrice;
    private String reservationAgency;
    private Flight flight;

    public FlightInformation(Flight flight) {
        bookingNumber = new Random().nextInt(50) +1;
        flightPrice = new Random().nextInt(50) +1;
        reservationAgency = "LameDuck";
        this.flight = flight;
    }

    public int getBookingNumber() {
        return bookingNumber;
    }

    public void setBookingNumber(int bookingNumber) {
        this.bookingNumber = bookingNumber;
    }

    public int getFlightPrice() {
        return flightPrice;
    }

    public void setFlightPrice(int flightPrice) {
        this.flightPrice = flightPrice;
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

}
