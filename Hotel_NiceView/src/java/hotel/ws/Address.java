package hotel.ws;

/**
 * @author arhjo
 */
class Address {
    private String city;
    private String street;
        
    public Address(String city, String street){
        this.city = city;
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }
    
    public void setCity(String city) {
       this.city = city;
    }

    public void setStreet(String street) {
        this.street = street;
    }
    
    
}
