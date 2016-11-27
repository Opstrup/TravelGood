
package dk.dtu.imm.fastmoney;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for hotelInformation complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="hotelInformation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="bookingNumber" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="hotel" type="{http://fastmoney.imm.dtu.dk}hotel" minOccurs="0"/>
 *         &lt;element name="nameOfHotelService" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="priceForStay" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "hotelInformation", propOrder = {
    "bookingNumber",
    "hotel",
    "nameOfHotelService",
    "priceForStay",
    "status"
})
public class HotelInformation {

    protected int bookingNumber;
    protected Hotel hotel;
    protected String nameOfHotelService;
    protected int priceForStay;
    protected String status;

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
     * Gets the value of the hotel property.
     * 
     * @return
     *     possible object is
     *     {@link Hotel }
     *     
     */
    public Hotel getHotel() {
        return hotel;
    }

    /**
     * Sets the value of the hotel property.
     * 
     * @param value
     *     allowed object is
     *     {@link Hotel }
     *     
     */
    public void setHotel(Hotel value) {
        this.hotel = value;
    }

    /**
     * Gets the value of the nameOfHotelService property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNameOfHotelService() {
        return nameOfHotelService;
    }

    /**
     * Sets the value of the nameOfHotelService property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNameOfHotelService(String value) {
        this.nameOfHotelService = value;
    }

    /**
     * Gets the value of the priceForStay property.
     * 
     */
    public int getPriceForStay() {
        return priceForStay;
    }

    /**
     * Sets the value of the priceForStay property.
     * 
     */
    public void setPriceForStay(int value) {
        this.priceForStay = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatus(String value) {
        this.status = value;
    }

}
