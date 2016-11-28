
package airline.ws;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for bookingStatus.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="bookingStatus">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="UNCONFIRMED"/>
 *     &lt;enumeration value="BOOKED"/>
 *     &lt;enumeration value="CANCELLED"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "bookingStatus")
@XmlEnum
public enum BookingStatus {

    UNCONFIRMED,
    BOOKED,
    CANCELLED;

    public String value() {
        return name();
    }

    public static BookingStatus fromValue(String v) {
        return valueOf(v);
    }

}
