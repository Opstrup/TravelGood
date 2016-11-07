package airline.ws;

import airline.ws.Flight;

import java.util.*;

public class AirlineModel {

    List <airline.ws.FlightInformation> flightInformationDB;

    public AirlineModel(){
        //TODO populate the DB (file?)
        this.flightInformationDB= new ArrayList<airline.ws.FlightInformation>();
    }

    public List<airline.ws.FlightInformation> getFlights(String startAirport, String destinationAirport, Date departureDate) {

        List<airline.ws.FlightInformation> result = new ArrayList<airline.ws.FlightInformation>();

        for(airline.ws.FlightInformation flightInformation : flightInformationDB){
            Flight flight = flightInformation.getFlight();
            if(startAirport.equals(flight.getStartAirport()) &&
                    destinationAirport.equals(flight.getDestinationAirport()) &&
                    departureDate.equals(flight.getDepartureDate()) &&
                    flight.getAvailableSeats()>0){
                result.add(flightInformation);
            }
        }

        return result;

    }
    
}
