/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package representation;

import javax.xml.bind.annotation.XmlRootElement;
import data.Itinerary;

@XmlRootElement()
public class ItineraryRepresentation extends Representation {
    
    private Itinerary itinerary;

    public Itinerary getItinerary() {
        return itinerary;
    }

    public void setItinerary(Itinerary itinerary) {
        this.itinerary = itinerary;
    }
    
}
