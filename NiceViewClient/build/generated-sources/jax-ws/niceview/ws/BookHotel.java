
package niceview.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for bookHotel complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="bookHotel">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="bookingNumber" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
@XmlType(name = "bookHotel", propOrder = {
    "bookingNumber",
    "creditCardInformation"
})
public class BookHotel {

    protected int bookingNumber;
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
