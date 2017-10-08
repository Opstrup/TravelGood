
package airline.ws;

import javax.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.6-1b01 
 * Generated source version: 2.2
 * 
 */
@WebFault(name = "FlightCancelException", targetNamespace = "http://ws.airline/")
public class FlightCancelException_Exception
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private FlightCancelException faultInfo;

    /**
     * 
     * @param faultInfo
     * @param message
     */
    public FlightCancelException_Exception(String message, FlightCancelException faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param faultInfo
     * @param cause
     * @param message
     */
    public FlightCancelException_Exception(String message, FlightCancelException faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: airline.ws.FlightCancelException
     */
    public FlightCancelException getFaultInfo() {
        return faultInfo;
    }

}