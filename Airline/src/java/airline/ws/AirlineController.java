package airline.ws;

import java.io.IOException;
import javax.jws.WebService;
import javax.jws.WebMethod;
import java.util.List;
import javax.jws.WebParam;
import javax.xml.datatype.XMLGregorianCalendar;

@WebService(serviceName = "AirlineService")

public class AirlineController {
    private AirlineModel model;
    
    public AirlineController() throws IOException {
        this.model = new AirlineModel();    
    }
    
    @WebMethod(operationName = "getFlights")
    public List<airline.ws.FlightInformation> getFlights(
                                @WebParam(name = "startAirport")String startAirport,
                                @WebParam(name = "endAirport")String destinationAirport,
                                @WebParam(name = "startDate") XMLGregorianCalendar departureDate) {

        return this.model.getFlights(startAirport, destinationAirport, departureDate);
    }
 
    @WebMethod(operationName = "bookFlight")
    public boolean bookFlight(@WebParam (name = "bookingNumber") int bookingNumber, 
                               @WebParam (name = "CreditCardInformation") 
                               bankservice.ws.CreditCardInfoType ccInfo)throws bankservice.ws.CreditCardFaultMessage{
        
        return model.bookFlight(bookingNumber,ccInfo);
    }
    
    @WebMethod(operationName = "cancleFlight")
    public void cancleFlight (@WebParam (name = "bookingNumber") int bookingNumber,
                              @WebParam (name = "flightPrice") int flightPrice,
                              @WebParam (name = "CreditCardInformation") 
                              bankservice.ws.CreditCardInfoType ccInfo) throws bankservice.ws.CreditCardFaultMessage, Exception {
      
        model.cancelFlight(bookingNumber, ccInfo);
    }
}
