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
    boolean creditCardNeed;

    public Flight(int flightID, String startAirport, String destinationAirport, XMLGregorianCalendar departureDate,
                  XMLGregorianCalendar arrivalDate, String airlineName, int availableSeats, boolean isCreditCardNeeded) {
        this.flightID = flightID;
        this.startAirport = startAirport;
        this.destinationAirport = destinationAirport;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.airlineName = airlineName;
        this.availableSeats = availableSeats; //when booked, availableSeats -= 1
        this.creditCardNeed = isCreditCardNeeded;
    }
    
    public int getFlightID() {
        return flightID;
    }
    public void setFlightID(int flightID) {
        this.flightID = flightID;
    }
    public String getStartAirport() {
        return startAirport;
    }
    public void setStartAirport(String startAirport) {
        this.startAirport = startAirport;
    }
    public String getDestinationAirport() {
        return destinationAirport;
    }
    public void setDestinationAirport(String destinationAirport) {
        this.destinationAirport = destinationAirport;
    }
    public XMLGregorianCalendar getDepartureDate() {
        return departureDate;
    }
    public void setDepartureDate(XMLGregorianCalendar departureDate) {
        this.departureDate = departureDate;
    }
    public XMLGregorianCalendar getArrivalDate() {
        return arrivalDate;
    }
    public void setArrivalDate(XMLGregorianCalendar arrivalDate) {
        this.arrivalDate = arrivalDate;
    }
    public String getAirlineName() {
        return airlineName;
    }
    public void setAirlineName(String airlineName) {
        this.airlineName = airlineName;
    }
    public int getAvailableSeats() {
        return availableSeats;
    }
    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }
    public boolean isCreditCardNeeded() {
        return creditCardNeed;
    }
    
}
