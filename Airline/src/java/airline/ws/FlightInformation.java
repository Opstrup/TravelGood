package airline.ws;

public class FlightInformation {
    private int bookingNumber;
    private int flightPrice;
    private String airlineName;
    private airline.ws.Flight flight;

    public FlightInformation(int bookingNumber, int flightPrice, String airlineName, airline.ws.Flight flight) {
        this.bookingNumber = bookingNumber;
        this.flightPrice = flightPrice;
        this.airlineName = airlineName;
        this.flight = flight;
    }

    public int getBookingNumber() {
        return bookingNumber;
    }

    public int getFlightPrice() {
        return flightPrice;
    }

    public String getAirlineName() {
        return airlineName;
    }

    public airline.ws.Flight getFlight() {
        return flight;
    }
}
