package ws.rs;

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
        NOTBOOKED, BOOKED
    }
    
    public String ID;
    public BookingStatus status;
    public List<HotelInformation> hotels = new ArrayList<>();
    public List<FlightInformation> flights = new ArrayList<>();
    
    
    public Itinerary() { };
    
    public Itinerary(String ID) {
        this.ID = ID;
        this.status = BookingStatus.NOTBOOKED;
    }

}
