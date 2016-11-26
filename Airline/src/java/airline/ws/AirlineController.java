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
        
        // 1.) chargeCreditCard
        // 2.) permanently books the flight
        
        return model.bookFlight(bookingNumber,ccInfo);
        
        /*
        The bookFlight operation takes a booking number and credit card information and permanently books the 
        flight after first having charged the credit card for the flight using the chargeCreditCard of the bank. 
        The bookFlight operation returns true, if the booking was successful and returns a fault 
        (i.e., throws an exception) if the credit card information was not valid, there was not enough money on
        the client account, or if for other reasons the booking fails.
        */
    }
    @WebMethod(operationName = "cancleFlight")
    public void cancleFlight (@WebParam (name = "bookingNumber") int bookingNumber,
                              @WebParam (name = "flightPrice") int flightPrice,
                              @WebParam (name = "CreditCardInformation")
                              bankservice.ws.CreditCardInfoType ccInfo) throws bankservice.ws.CreditCardFaultMessage {
      
        //model.cancelFly(bookingNumber,ccInfo); 
    }
	
    
}
