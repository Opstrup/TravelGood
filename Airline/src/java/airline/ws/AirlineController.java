package airline.ws;

import javax.jws.WebService;
import javax.jws.WebMethod;

@WebService(serviceName = "AirlineService")
public class AirlineController {
    
    @WebMethod(operationName = "getFlights")
    public String getFlights() {
        return "Hello World!";
    }
}
