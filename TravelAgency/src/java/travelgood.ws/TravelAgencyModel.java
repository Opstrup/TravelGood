package travelgood.ws;

import java.util.ArrayList;
import java.util.List;

public class TravelAgencyModel {

    private List<Itinerary> itineraries;
    private int itineraryCount=0;

    public Itinerary createFirstItinerary(){
        itineraries = new ArrayList<>();
        itineraryCount ++;
        Itinerary firstItinerary = new Itinerary(itineraryCount);
        itineraries.add(firstItinerary);
        return firstItinerary;
    }

    public Itinerary createAnotherItinerary() throws Exception {
        if(itineraries.isEmpty()){
            throw new Exception();
        }
        else{
            itineraryCount++;
            Itinerary anotherItinerary = new Itinerary(itineraryCount);
            itineraries.add(anotherItinerary);
            return anotherItinerary;
        }
    }

}
