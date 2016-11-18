/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.travelgood;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.netbeans.j2ee.wsdl.travelgoodbpel.src.travelgoodbpel.ItineraryType;

public class ClientTest {

    @Test
    public void should_return_empty_itinerary(){
        ItineraryType itinerary = createItinerary("create my itinerary");
        assertNotNull(itinerary);
    }
    
    @Test
    public void should_return_empty_itinerary_with_id(){
        ItineraryType itinerary = createItinerary("create my itinerary");
        String id = itinerary.getItineraryID();
        assertEquals("1", id);
    }
    
    private static ItineraryType createItinerary(java.lang.Object createItineraryReq) {
        org.netbeans.j2ee.wsdl.travelgoodbpel.src.travelgoodbpel.TravelGoodBPELService service = new org.netbeans.j2ee.wsdl.travelgoodbpel.src.travelgoodbpel.TravelGoodBPELService();
        org.netbeans.j2ee.wsdl.travelgoodbpel.src.travelgoodbpel.TravelGoodBPELPortType port = service.getTravelGoodBPELPort();
        return port.createItinerary(createItineraryReq);
    }

}

