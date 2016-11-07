package airline.ws;

import javax.jws.WebService;
import javax.jws.WebMethod;
import java.util.Date;
import java.util.List;

@WebService(serviceName = "AirlineService")
public class AirlineController {

    airline.ws.AirlineModel model = new airline.ws.AirlineModel();

    @WebMethod(operationName = "getFlights")
    public List<airline.ws.FlightInformation> getFlights(String startAirport, String destinationAirport, Date departureDate) {
        return model.getFlights(startAirport, destinationAirport, departureDate);
    }
}
