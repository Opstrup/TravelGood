package airline.ws;

import bankservice.ws.CreditCardFaultMessage;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

public class AirlineModel {

    protected List <Flight> flightsDB;
    protected List <airline.ws.FlightInformation> flightsInformationDB;
    public String url = "C:/Users/Vitali/Desktop/TravelGood/Airline/build/web/WEB-INF/classes/airline/ws/flightDB.txt";

    
    public AirlineModel(String dbFilePath) throws IOException{
        // startAirport;destinationAirport;departureDate;arrivalDate;airlineName;availableSeats
        // and add a flight from each line using the split(";") operation
        flightsDB = new ArrayList<>();
        flightsInformationDB = new ArrayList<>();
        AtomicInteger flightId = new AtomicInteger(0);
       // URL url = getClass().getResource(dbFilePath);  //Getting path probelm with file/C: the "/" need to be taken away.

        //Files.lines(Paths.get(url.getPath()), StandardCharsets.UTF_8).forEach((line)-> {
        Files.lines(Paths.get(url), StandardCharsets.UTF_8).forEach((line)-> {
            try {
                String[] flightInformationArray = line.split(";");
                String startAirport = flightInformationArray[0];
                String destinationAirport = flightInformationArray[1];
                String departureDateString[] = flightInformationArray[2].split("-");
                XMLGregorianCalendar departureDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar(
                        Integer.parseInt(departureDateString[0]), Integer.parseInt(departureDateString[1]), Integer.parseInt(departureDateString[2])));
                //Date departureDate = new GregorianCalendar(Integer.parseInt(departureDateString[0]), Integer.parseInt(departureDateString[1]), Integer.parseInt(departureDateString[2])).getTime();
                String arrivalDateString[] = flightInformationArray[3].split("-");
                XMLGregorianCalendar arrivalDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar(
                        Integer.parseInt(arrivalDateString[0]), Integer.parseInt(arrivalDateString[1]), Integer.parseInt(arrivalDateString[2])));
                //Date arrivalDate = new GregorianCalendar(Integer.parseInt(arrivalDateString[0]), Integer.parseInt(arrivalDateString[1]), Integer.parseInt(arrivalDateString[2])).getTime();
                String airlineName = flightInformationArray[4];
                int availableSeats = Integer.parseInt(flightInformationArray[5]);
                
                boolean isCreditCardNeeded = false; 
                Flight newFlight = new Flight(flightId.incrementAndGet(), startAirport, destinationAirport, departureDate, arrivalDate, airlineName, availableSeats, isCreditCardNeeded);
                flightsDB.add(newFlight);
            } 
            catch (DatatypeConfigurationException ex) {
                Logger.getLogger(AirlineModel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        );             
    }
    public List<FlightInformation> getFlights(String startAirport, String destinationAirport, XMLGregorianCalendar departureDate) {
        List<FlightInformation> result = new ArrayList<>();
        for(Flight flight : flightsDB){
            if(flight.getStartAirport().equals(startAirport) &&
                    flight.getDestinationAirport().equals(destinationAirport) &&
                    flight.getDepartureDate().equals(departureDate) &&
                    flight.getAvailableSeats()>0){
                FlightInformation flightInfo = new FlightInformation(flight);
                result.add(flightInfo);
                flightsInformationDB.add(flightInfo); // Adding to global data base.
            }
        }
        return result;
    }
    public boolean bookFlight(int bookingNumber, bankservice.ws.CreditCardInfoType ccInfo) throws bankservice.ws.CreditCardFaultMessage{
        for(FlightInformation flightInfo : flightsInformationDB){
            if(flightInfo.getBookingNumber() == bookingNumber){
                if(flightInfo.getFlight().isCreditCardNeeded()){
                    try {
                        if(validateCreditCard(7, ccInfo, flightInfo.getFlightPrice())){
                        flightInfo.setStatus("Confirmed");
                        return true;
                        }
                    }
                    catch (bankservice.ws.CreditCardFaultMessage exFaultMessage) {
                        throw exFaultMessage;
                    }
                }
                else{
                    flightInfo.setStatus("Confirmed");
                    return true;
                }
            }
        }
        return false;       
    }
    
    private static boolean validateCreditCard(int group, bankservice.ws.CreditCardInfoType creditCardInfo, int amount) throws bankservice.ws.CreditCardFaultMessage {
        bankservice.ws.BankService service = new bankservice.ws.BankService();
        bankservice.ws.BankPortType port = service.getBankPort();
        return port.validateCreditCard(group, creditCardInfo, amount);
    }
    private static boolean refundCreditCart(int group, bankservice.ws.CreditCardInfoType creditCardInfo, int amount, bankservice.ws.AccountType account) throws bankservice.ws.CreditCardFaultMessage {
        bankservice.ws.BankService service = new bankservice.ws.BankService();
        bankservice.ws.BankPortType port = service.getBankPort();
        return port.refundCreditCard(group, creditCardInfo, amount, account);
    }
    
    public boolean cancelFly(int bookingNumber, bankservice.ws.CreditCardInfoType ccInfo, bankservice.ws.AccountType account) throws bankservice.ws.CreditCardFaultMessage, Exception {
        for(FlightInformation flightInfo : flightsInformationDB){
            if(flightInfo.getBookingNumber() == bookingNumber){
                try {
                    if(refundCreditCart(7, ccInfo, flightInfo.getFlightPrice(), account)) {
                        flightInfo.setStatus("Canceled");
                        return true;
                    }
                }
                catch (bankservice.ws.CreditCardFaultMessage exFaultMessage) {
                    //throw exFaultMessage;               
                    flightInfo.setStatus("Canceled");
                    return true;
                }
            }
            else {
                throw new Exception("BookingNo do not eksist");
            }
        }
        return false;
    }
}
