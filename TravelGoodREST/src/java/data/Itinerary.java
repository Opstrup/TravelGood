package data;

import airline.ws.FlightInformation;
import hotel.ws.HotelInformation;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Itinerary {

    public enum BookingStatus {
        UNCONFIRMED, BOOKED, CANCELLED
    }

    public String ID;
    public BookingStatus status;
    public List<HotelInformation> hotels = new ArrayList<>();
    public List<FlightInformation> flights = new ArrayList<>();
    
    
    public Itinerary() { };
    
    public Itinerary(String ID) {
        this.ID = ID;
        this.status = BookingStatus.UNCONFIRMED;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }

    public List<HotelInformation> getHotels() {
        return hotels;
    }

    public void setHotels(List<HotelInformation> hotels) {
        this.hotels = hotels;
    }

    public List<FlightInformation> getFlights() {
        return flights;
    }

    public void setFlights(List<FlightInformation> flights) {
        this.flights = flights;
    }
    
}
