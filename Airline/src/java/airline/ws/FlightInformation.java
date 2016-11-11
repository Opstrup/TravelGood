package airline.ws;

import java.util.Random;

public class FlightInformation {
    private int bookingNumber;
    private int flightPrice;
    private String reservationAgency;
    private airline.ws.Flight flight;

    public FlightInformation(Flight flight) {
        bookingNumber = new Random().nextInt(50) +1;
        flightPrice = new Random().nextInt(50) +1;
        reservationAgency = "LameDuck";
        this.flight = flight;
    }

    public int getBookingNumber() {
        return bookingNumber;
    }

    public int getFlightPrice() {
        return flightPrice;
    }

    public String getReservationAgency() {
        return reservationAgency;
    }

    public airline.ws.Flight getFlight() {
        return flight;
    }
}
