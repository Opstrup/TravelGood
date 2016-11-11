package airline.ws;

import airline.ws.Flight;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class AirlineModel {

    protected List <Flight> flightsDB;

    public AirlineModel(String dbFilePath) throws IOException{
        // startAirport;destinationAirport;departureDate;arrivalDate;airlineName;availableSeats
        // and add a flight from each line using the split(";") operation
        AtomicInteger flightId = new AtomicInteger(0);
        
        URL url = getClass().getResource(dbFilePath);
        
        Files.lines(Paths.get(url.getPath()), StandardCharsets.UTF_8).forEach((line)-> {
                int availableSeats = 0;
                String startAirport = "";
                String destinationAirport = "";
                Date departureDate = new GregorianCalendar(2015, 7, 7).getTime();
                Date arrivalDate = new GregorianCalendar(2015, 7, 8).getTime();
                String airlineName = "";
                
                Flight newFlight = new Flight(flightId.incrementAndGet(), startAirport, destinationAirport, departureDate, arrivalDate, airlineName, availableSeats);
                FlightInformation newFlightInformatin = new FlightInformation(newFlight);   
            }
        );
        
        
        flightsDB= new ArrayList<>();
    }

    public List<FlightInformation> getFlights(String startAirport, String destinationAirport, Date departureDate) {

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
