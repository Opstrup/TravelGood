package airline.ws;

import java.util.Date;
import javax.xml.datatype.XMLGregorianCalendar;

public class Flight {

    private int flightID;
    private String startAirport;
    private String destinationAirport;
    private XMLGregorianCalendar departureDate;
    private XMLGregorianCalendar arrivalDate;
    private String airlineName;
    private int availableSeats;

    public Flight(int flightID, String startAirport, String destinationAirport, XMLGregorianCalendar departureDate,
                  XMLGregorianCalendar arrivalDate, String airlineName, int availableSeats) {
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

    public XMLGregorianCalendar getDepartureDate() {
        return departureDate;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public int getFlightID() {
        return flightID;
    }
}
