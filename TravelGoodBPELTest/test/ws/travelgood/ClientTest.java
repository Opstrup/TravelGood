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


public class ClientTest {
    

    @Test
    public void should_return_empty_itinerary(){
        //
    }
    
    private static String createItineray() {
        ws.travelgood.TravelGoodBPELService service = new ws.travelgood.TravelGoodBPELService();
        ws.travelgood.TravelGoodBPELPortType port = service.getTravelGoodBPELPort();
        return port.createItineray();
    }
    
}

