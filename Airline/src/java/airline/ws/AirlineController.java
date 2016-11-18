package airline.ws;

import java.io.IOException;
import javax.jws.WebService;
import javax.jws.WebMethod;
import java.util.Date;
import java.util.List;
import javax.jws.WebParam;

@WebService(serviceName = "AirlineService")
public class AirlineController {
    private String dbFilePath = "flightDB.txt";
    private AirlineModel model;
    
    public AirlineController() throws IOException {
     this.model = new AirlineModel(this.dbFilePath);    
    }
    
    @WebMethod(operationName = "getFlights")
    public List<airline.ws.FlightInformation> getFlights(
            @WebParam(name = "startAirport")String startAirport,
            @WebParam(name = "endAirport")String destinationAirport,
            @WebParam(name = "startDate") java.util.Date departureDate) {
        return model.getFlights(startAirport, destinationAirport, departureDate);
    }
}
