package airline.ws;

import bankservice.ws.AccountType;
import bankservice.ws.CreditCardFaultMessage;
import java.util.*;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

public class AirlineModel {
    protected List <Flight> flightsDB;
    protected List <FlightInformation> flightsInformationDB;
       
    public AirlineModel() {
        flightsDB = new ArrayList<>();
        flightsInformationDB = new ArrayList<>();
        
        try {
            populateDb();
        } catch (Exception e) {
            // Throws 500 internal error
        }
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
                        //Charge credit card, and not validate credit card.
                        if(validateCreditCard(7, ccInfo, flightInfo.getFlightPrice())){
                            try {
                                AccountType lameDuckAccountType = new AccountType();
                                lameDuckAccountType.setName("LameDuck");
                                lameDuckAccountType.setName("50208812");
                                chargeCreditCard(7, ccInfo, bookingNumber, lameDuckAccountType);
                                flightInfo.setStatus("Confirmed");
                                return true;
                            } catch (CreditCardFaultMessage e) {
                                throw e;
                            }
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
                throw new Exception("BookingNo do not exsist");
            }
        }
        return false;
    }

    private void populateDb () throws DatatypeConfigurationException {
        XMLGregorianCalendar depatureDateFlightOne = DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar(2016, 11, 26));
        XMLGregorianCalendar arrivalDateFlightOne = DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar(2016, 11, 27));

        Flight flightOne = new Flight(1, "Copenhagen", "Rome", depatureDateFlightOne, arrivalDateFlightOne, "EasyJet", 10, true);
        FlightInformation flightInformationOne = new FlightInformation(flightOne);
        
        XMLGregorianCalendar depatureDateFlightTwo = DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar(2016, 11, 26));
        XMLGregorianCalendar arrivalDateFlightTwo = DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar(2016, 11, 27));

        Flight flightTwo = new Flight(1, "Copenhagen", "Rome", depatureDateFlightTwo, arrivalDateFlightTwo, "EasyJet", 10, false);
        FlightInformation flightInformationTwo = new FlightInformation(flightOne);
        
        flightsDB.add(flightOne);
        flightsDB.add(flightTwo);
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

    private static boolean chargeCreditCard(int group, bankservice.ws.CreditCardInfoType creditCardInfo, int amount, bankservice.ws.AccountType account) throws CreditCardFaultMessage {
        bankservice.ws.BankService service = new bankservice.ws.BankService();
        bankservice.ws.BankPortType port = service.getBankPort();
        return port.chargeCreditCard(group, creditCardInfo, amount, account);
    }

}
