
package airline.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import dk.dtu.imm.fastmoney.types.CreditCardInfoType;


/**
 * <p>Java class for cancelFlight complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="cancelFlight">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="bookingNumber" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="flightPrice" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="CreditCardInformation" type="{http://types.fastmoney.imm.dtu.dk}creditCardInfoType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "cancelFlight", propOrder = {
    "bookingNumber",
    "flightPrice",
    "creditCardInformation"
})
public class CancelFlight {

    protected int bookingNumber;
    protected int flightPrice;
    @XmlElement(name = "CreditCardInformation")
    protected CreditCardInfoType creditCardInformation;

    /**
     * Gets the value of the bookingNumber property.
     * 
     */
    public int getBookingNumber() {
        return bookingNumber;
    }

    /**
     * Sets the value of the bookingNumber property.
     * 
     */
    public void setBookingNumber(int value) {
        this.bookingNumber = value;
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
     * Gets the value of the creditCardInformation property.
     * 
     * @return
     *     possible object is
     *     {@link CreditCardInfoType }
     *     
     */
    public CreditCardInfoType getCreditCardInformation() {
        return creditCardInformation;
    }

    /**
     * Sets the value of the creditCardInformation property.
     * 
     * @param value
     *     allowed object is
     *     {@link CreditCardInfoType }
     *     
     */
    public void setCreditCardInformation(CreditCardInfoType value) {
        this.creditCardInformation = value;
    }

}
