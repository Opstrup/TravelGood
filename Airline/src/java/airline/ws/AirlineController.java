package airline.ws;

import airline.ws.exception.FlightBookException;
import airline.ws.exception.FlightCancelException;
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
                               bankservice.ws.CreditCardInfoType ccInfo)throws FlightBookException{
        
        //can throw FlightBookException
        return model.bookFlight(bookingNumber,ccInfo);
    }
    
    @WebMethod(operationName = "cancelFlight")
    public boolean cancelFlight (@WebParam (name = "bookingNumber") int bookingNumber,
                              @WebParam (name = "flightPrice") int flightPrice,
                              @WebParam (name = "CreditCardInformation") 
                              bankservice.ws.CreditCardInfoType ccInfo) throws FlightCancelException {
        //can throw FlightCancelException
        return model.cancelFlight(bookingNumber, ccInfo);
    }
}
