/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.travelgood;

import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.netbeans.j2ee.wsdl.travelgoodbpel.src.travelgoodbpel.FlightsListType;
import org.netbeans.j2ee.wsdl.travelgoodbpel.src.travelgoodbpel.ItineraryType;

public class ClientTest {

    @Test
    public void should_return_empty_itinerary(){
        ItineraryType itinerary = createItinerary("create my itinerary");
        assertNotNull(itinerary);
    }
    
    @Test
    public void should_return_empty_itinerary_with_id(){
        String itineraryID = create;
        String id = itinerary.getItineraryID();
        assertEquals("1", id);
    }
    
    @Test
    public void should_return_flight(){
        
        ItineraryType itinerary = createItinerary("create my itinerary");
        
        XMLGregorianCalendar depDate = null;
        try {
            depDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar(2016, 8, 10));
        } catch (DatatypeConfigurationException ex) {
            Logger.getLogger(ClientTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String startAirport = "copenhagen";
        String endAirport = "rome";
        
        FlightsListType result = getFlights(startAirport, endAirport, depDate);
        assertNotNull(result.getNewElement());
        
    }

    private static void createItinerary(java.lang.String createItineraryReq, javax.xml.ws.Holder<org.netbeans.j2ee.wsdl.travelgoodbpel.src.travelgoodbpel.ItineraryType> emptyItinerary, javax.xml.ws.Holder<java.lang.String> createItineraryResp) {
        org.netbeans.j2ee.wsdl.travelgoodbpel.src.travelgoodbpel.TravelGoodBPELService service = new org.netbeans.j2ee.wsdl.travelgoodbpel.src.travelgoodbpel.TravelGoodBPELService();
        org.netbeans.j2ee.wsdl.travelgoodbpel.src.travelgoodbpel.TravelGoodBPELPortType port = service.getTravelGoodBPELPort();
        port.createItinerary(createItineraryReq, emptyItinerary, createItineraryResp);
    }


}

