package airline.ws;

import java.io.IOException;
import javax.jws.WebService;
import javax.jws.WebMethod;
import java.util.Date;
import java.util.List;
import javax.jws.WebParam;
import javax.jws.soap.SOAPBinding;
import javax.xml.datatype.XMLGregorianCalendar;

@WebService(serviceName = "AirlineService",	
        targetNamespace = "http://ws.airline/")

//@SOAPBinding(style=SOAPBinding.Style.RPC,use=SOAPBinding.Use.LITERAL,
//			parameterStyle=SOAPBinding.ParameterStyle.WRAPPED)

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
            @WebParam(name = "startDate") XMLGregorianCalendar departureDate) {
        return model.getFlights(startAirport, destinationAirport, departureDate);
    }
  
}
