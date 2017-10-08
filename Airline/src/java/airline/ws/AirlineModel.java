package airline.ws;

import airline.ws.exception.FlightBookException;
import airline.ws.exception.FlightCancelException;
import bankservice.ws.AccountType;
import bankservice.ws.CreditCardFaultMessage;
import java.util.*;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

public class AirlineModel {
    protected List <Flight> flightsDB;
    protected List <FlightInformation> flightsInformationDB;
    protected AccountType lameDuckAccountType = new AccountType();
    
    public AirlineModel() {
        flightsDB = new ArrayList<>();
        flightsInformationDB = new ArrayList<>();
        lameDuckAccountType.setName("LameDuck");
        lameDuckAccountType.setNumber("50208812");
        
        try {
            populateDb();
        } catch (Exception e) {
            // Throws 500 internal error
        }
    }
    
    public List<FlightInformation> getFlights(String startAirport, String destinationAirport, XMLGregorianCalendar departureDate) {
        List<FlightInformation> result = new ArrayList<>();
        for(Flight flight : flightsDB)
            if(flight.getStartAirport().equals(startAirport) &&
                    flight.getDestinationAirport().equals(destinationAirport) &&
                    flight.getDepartureDate().equals(departureDate) &&
                    flight.getAvailableSeats()>0){
                FlightInformation flightInfo = new FlightInformation(flight);
                result.add(flightInfo);
                flightsInformationDB.add(flightInfo); // Adding to global data base.
            }
            
        return result;
    }
    
    public boolean bookFlight(int bookingNumber, bankservice.ws.CreditCardInfoType ccInfo) throws FlightBookException{
        for(FlightInformation flightInfo : flightsInformationDB)
            if(flightInfo.getBookingNumber() == bookingNumber){
                
                if(flightInfo.getFlight().getStartAirport().equals("BookingFailure"))
                    throw new FlightBookException("Booking of flight failed by design");
                
//                if(flightInfo.getFlight().isCreditCardNeeded()){
//                    try{
//                        validateCreditCard(7, ccInfo, flightInfo.getFlight().getFlightPrice());
//                        chargeCreditCard(7, ccInfo, bookingNumber, lameDuckAccountType);
//                    } catch(bankservice.ws.CreditCardFaultMessage e){
//                        throw new FlightBookException("Validation of credit card failed!");
//                    }
//                }
                flightInfo.setStatus(FlightInformation.BookingStatus.BOOKED);
                return true;
            }
        
        throw new FlightBookException("No flight with provided booking number");      
    }
    
    public boolean cancelFlight(int bookingNumber, bankservice.ws.CreditCardInfoType ccInfo) throws FlightCancelException {
        for(FlightInformation flightInfo : flightsInformationDB)
            if(flightInfo.getBookingNumber() == bookingNumber){
  
                if(flightInfo.getFlight().getStartAirport().equals("CancelingFailure"))
                    throw new FlightCancelException("Cancellation of flight failed by design");
                
//                try{
//                    refundCreditCard(7, ccInfo, flightInfo.getFlight().getFlightPrice()/2, lameDuckAccountType);
//                } catch(bankservice.ws.CreditCardFaultMessage e){
//                    throw new FlightCancelException("Refund of CC failed!");
//                }
                
                flightInfo.setStatus(FlightInformation.BookingStatus.CANCELLED);
                return true;
            }
        
        throw new FlightCancelException("No flight with provided bookingNumber!");
    }

    private void populateDb () throws DatatypeConfigurationException {
        XMLGregorianCalendar depatureDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar(2016, 11, 26));
        XMLGregorianCalendar arrivalDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar(2016, 11, 27));

        Flight flightOne = new Flight(1, "Copenhagen", "Rome", depatureDate, arrivalDate, "EasyJet", 10, true, 100);
        Flight flightTwo = new Flight(1, "Copenhagen", "Milan", depatureDate, arrivalDate, "EasyJet", 10, true, 100);
        Flight flightThree = new Flight(1, "Copenhagen", "Turin", depatureDate, arrivalDate, "EasyJet", 10, true, 100);
        Flight flightBookFail = new Flight(1, "BookingFailure", "Rome", depatureDate, arrivalDate, "EasyJet", 10, true, 10000);
        Flight flightCancFail = new Flight(1, "CancelingFailure", "Rome", depatureDate, arrivalDate, "EasyJet", 10, true, 10000);
        
        flightsDB.add(flightOne);
        flightsDB.add(flightTwo);
        flightsDB.add(flightThree);
        flightsDB.add(flightBookFail);
        flightsDB.add(flightCancFail);
    }
    
    private static boolean validateCreditCard(int group, bankservice.ws.CreditCardInfoType creditCardInfo, int amount) throws bankservice.ws.CreditCardFaultMessage {
        bankservice.ws.BankService service = new bankservice.ws.BankService();
        bankservice.ws.BankPortType port = service.getBankPort();
        return port.validateCreditCard(group, creditCardInfo, amount);
    }
    
    private static boolean refundCreditCard(int group, bankservice.ws.CreditCardInfoType creditCardInfo, int amount, bankservice.ws.AccountType account) throws bankservice.ws.CreditCardFaultMessage {
        bankservice.ws.BankService service = new bankservice.ws.BankService();
        bankservice.ws.BankPortType port = service.getBankPort();
        return port.refundCreditCard(group, creditCardInfo, amount, account);
    }

    private static boolean chargeCreditCard(int group, bankservice.ws.CreditCardInfoType creditCardInfo, int amount, bankservice.ws.AccountType account) throws CreditCardFaultMessage {
        bankservice.ws.BankService service = new bankservice.ws.BankService();
        bankservice.ws.BankPortType port = service.getBankPort();
        return port.chargeCreditCard(group, creditCardInfo, amount, account);
    }

}
