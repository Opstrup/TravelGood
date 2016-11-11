package airline.ws;

import java.io.IOException;
import javax.jws.WebService;
import javax.jws.WebMethod;
import java.util.Date;
import java.util.List;

@WebService(serviceName = "AirlineService")
public class AirlineController {
    String dbFilePath = "flightDB.txt";
    private AirlineModel model;
    
    public void AirlineController() throws IOException {
     this.model = new AirlineModel(dbFilePath);    
    }
    
    @WebMethod(operationName = "getFlights")
    public List<airline.ws.FlightInformation> getFlights(String startAirport, String destinationAirport, Date departureDate) {
        return model.getFlights(startAirport, destinationAirport, departureDate);
    }
}
