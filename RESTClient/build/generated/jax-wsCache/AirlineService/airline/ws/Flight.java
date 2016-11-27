
package airline.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for flight complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="flight">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="airlineName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="arrivalDate" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" minOccurs="0"/>
 *         &lt;element name="availableSeats" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="departureDate" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" minOccurs="0"/>
 *         &lt;element name="destinationAirport" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="flightID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="flightPrice" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="startAirport" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "flight", propOrder = {
    "airlineName",
    "arrivalDate",
    "availableSeats",
    "departureDate",
    "destinationAirport",
    "flightID",
    "flightPrice",
    "startAirport"
})
public class Flight {

    protected String airlineName;
    @XmlSchemaType(name = "anySimpleType")
    protected Object arrivalDate;
    protected int availableSeats;
    @XmlSchemaType(name = "anySimpleType")
    protected Object departureDate;
    protected String destinationAirport;
    protected int flightID;
    protected int flightPrice;
    protected String startAirport;

    /**
     * Gets the value of the airlineName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAirlineName() {
        return airlineName;
    }

    /**
     * Sets the value of the airlineName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAirlineName(String value) {
        this.airlineName = value;
    }

    /**
     * Gets the value of the arrivalDate property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getArrivalDate() {
        return arrivalDate;
    }

    /**
     * Sets the value of the arrivalDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setArrivalDate(Object value) {
        this.arrivalDate = value;
    }

    /**
     * Gets the value of the availableSeats property.
     * 
     */
    public int getAvailableSeats() {
        return availableSeats;
    }

    /**
     * Sets the value of the availableSeats property.
     * 
     */
    public void setAvailableSeats(int value) {
        this.availableSeats = value;
    }

    /**
     * Gets the value of the departureDate property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getDepartureDate() {
        return departureDate;
    }

    /**
     * Sets the value of the departureDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setDepartureDate(Object value) {
        this.departureDate = value;
    }

    /**
     * Gets the value of the destinationAirport property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDestinationAirport() {
        return destinationAirport;
    }

    /**
     * Sets the value of the destinationAirport property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDestinationAirport(String value) {
        this.destinationAirport = value;
    }

    /**
     * Gets the value of the flightID property.
     * 
     */
    public int getFlightID() {
        return flightID;
    }

    /**
     * Sets the value of the flightID property.
     * 
     */
    public void setFlightID(int value) {
        this.flightID = value;
    }

    /**
     * Gets the value of the flightPrice property.
     * 
     */
    public int getFlightPrice() {
        return flightPrice;
    }

    /**
     * Sets the value of the flightPrice property.
     * 
     */
    public void setFlightPrice(int value) {
        this.flightPrice = value;
    }

    /**
     * Gets the value of the startAirport property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStartAirport() {
        return startAirport;
    }

    /**
     * Sets the value of the startAirport property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStartAirport(String value) {
        this.startAirport = value;
    }

}
