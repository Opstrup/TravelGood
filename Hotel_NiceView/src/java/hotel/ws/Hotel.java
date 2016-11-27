package hotel.ws;
/**
 * @author arhjo
 */
public class Hotel {
    private String hotelName;
    private Address address;
    private int pricePerDay; // Consider price per day
    private boolean creditCardNeeded;

    
    public Hotel(String hotelName, Address address, int pricePerDay, boolean creditCardNeeded){
        this.hotelName = hotelName;
        this.address = address;
        this.pricePerDay = pricePerDay;
        this.creditCardNeeded = creditCardNeeded;
        
    }
    
    public boolean isCreditCardNeeded() {
        return creditCardNeeded;
    }

    public String getHotelName() {
        return hotelName;
    }

      public void setAddress(Address address) {
        this.address = address;
    }

    public void setCreditCardNeeded(boolean creditCardNeeded) {
        this.creditCardNeeded = creditCardNeeded;
    }
    
    public void setHotelName(String name){
        this.hotelName = name;
    }
    public Address getAddress() {
        return address;
    }

    public int getPricePerDay() {
        return pricePerDay;
    }
    
    public void setPricePerDay(int price){
        this.pricePerDay = price;
    }
    
    // A = period
    // B = Parameters

}
