package airline.ws;

import java.util.Date;

public class Flight {
    private String _destination;
    private String _startOfTravel;
    private Date _departureDate;
    private int _availableSeats;
    
    public Flight(String des, String startOfTravel, Date departureDate, int availableSeats) {
        _destination = des;
        _startOfTravel = startOfTravel;
        _departureDate = departureDate;
        _availableSeats = availableSeats;
    }
    
    public String getDestination() {
        return _destination;
    }
    
    public String getStartOfTravel() {
        return _startOfTravel;
    }
    
    public Date getDepaturDate() {
        return _departureDate;
    }
    
    public int getAvailableSeats() {
        return _availableSeats;
    }
    
}
