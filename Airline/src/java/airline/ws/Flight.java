package airline.ws;

import java.util.Date;

public class Flight {

    private int flightID;
    private String startAirport;
    private String destinationAirport;
    private Date departureDate;
    private Date arrivalDate;
    private String airlineName;
    private int availableSeats;

    public Flight(int flightID, String startAirport, String destinationAirport, Date departureDate,
                  Date arrivalDate, String airlineName, int availableSeats) {
        this.flightID = flightID;
        this.startAirport = startAirport;
        this.destinationAirport = destinationAirport;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.airlineName = airlineName;
        this.availableSeats = availableSeats; //when booked, availableSeats -= 1
    }

    public String getStartAirport() {
        return startAirport;
    }

    public String getDestinationAirport() {
        return destinationAirport;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public int getFlightID() {
        return flightID;
    }
}
