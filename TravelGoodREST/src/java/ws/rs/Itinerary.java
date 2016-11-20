package ws.rs;

import java.util.Random;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Itinerary {

    public enum BookingStatus {
        NOTBOOKED, BOOKED
    }
    
    public int ID;
    public BookingStatus status;
    
    public Itinerary() { };
    
    public Itinerary(int ID) {
        this.ID = ID;
        this.status = BookingStatus.NOTBOOKED;
    }
    
    public void SetID(Integer id) {
        this.ID = id;
    }
}
