package airline.ws;

import airline.ws.Flight;

import java.util.*;

public class AirlineModel {

    protected List <Flight> flightsDB;

    public AirlineModel(){
        //TODO populate the DB (file?)
        // we can use a file with one flight for each line, formatted this way:
        // startAirport;destinationAirport;departureDate;arrivalDate;airlineName;availableSeats
        // and add a flight from each line using the split(";") operation
        flightsDB= new ArrayList<>();
    }

    public List<airline.ws.FlightInformation> getFlights(String startAirport, String destinationAirport, Date departureDate) {

        List<FlightInformation> result = new ArrayList<>();

        for(Flight flight : flightsDB){
            if(flight.getStartAirport().equals(startAirport) &&
                    flight.getDestinationAirport().equals(destinationAirport) &&
                    flight.getDepartureDate().equals(departureDate) &&
                    flight.getAvailableSeats()>0){
                FlightInformation flightInfo = new FlightInformation(flight);
                result.add(flightInfo);
            }
        }

        return result;

    }
    
}
