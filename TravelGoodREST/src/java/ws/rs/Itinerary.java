package ws.rs;

import java.util.Random;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Itinerary {
    
    public int ID;
    
    public Itinerary() { };
    
//    public Itinerary(Integer id) {
//        if (id == null) {
//            Random randomIDGenerator = new Random();
//            this.ID = randomIDGenerator.nextInt();
//        } else {
//            this.ID = id;
//        }
//    }
    
    public Itinerary(int ID) {
        this.ID = ID;
    }
    
    public void SetID(Integer id) {
        this.ID = id;
    }
}
