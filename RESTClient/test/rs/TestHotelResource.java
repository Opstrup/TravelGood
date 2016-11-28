/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs;

import dk.dtu.imm.fastmoney.HotelInformation;
import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import org.junit.Test;

public class TestHotelResource {
    
    Client client = ClientBuilder.newClient(); 
    WebTarget target = client.target("http://localhost:8080/ws.pr/webresources/hotels");
    
    @Test
    public void should_return_hotel(){
        List<HotelInformation> hotels = target.path("/Copenhagen")
                    .queryParam("start", "25-11-2016")
                    .queryParam("end", "31-11-2016")
                    .request("application/json")
                    .accept("application/json")
                    .get(new GenericType<List<HotelInformation>>() {});
        
        assert(hotels.size()>0);
    }
    
}
