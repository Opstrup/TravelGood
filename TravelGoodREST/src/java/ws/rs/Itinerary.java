package ws.rs;

import java.util.Random;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Opstrup
 */
@XmlRootElement
public class Itinerary {
 
    @XmlElement
    private Integer ID = null;
    
    public void Itinerary(Integer id) {
        if (id == null) {
            Random randomIDGenerator = new Random();
            this.ID = randomIDGenerator.nextInt();
        }
    }    
}
