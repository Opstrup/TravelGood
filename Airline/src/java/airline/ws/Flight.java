package airline.ws;

import java.util.Date;

public class Flight {

    private String startAirport;
    private String destinationAirport;
    private Date departureDate;
    private Date arrivalDate;
    private String airlineName;
    private int availableSeats;

    public Flight(String startAirport, String destinationAirport, Date departureDate, Date arrivalDate, String airlineName, int availableSeats) {
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

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public String getAirlineName() {
        return airlineName;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }
}
