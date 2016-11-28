
package airline.ws;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the airline.ws package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _FlightCancelException_QNAME = new QName("http://ws.airline/", "FlightCancelException");
    private final static QName _CancelFlightResponse_QNAME = new QName("http://ws.airline/", "cancelFlightResponse");
    private final static QName _BookFlightResponse_QNAME = new QName("http://ws.airline/", "bookFlightResponse");
    private final static QName _GetFlights_QNAME = new QName("http://ws.airline/", "getFlights");
    private final static QName _BookFlight_QNAME = new QName("http://ws.airline/", "bookFlight");
    private final static QName _CancelFlight_QNAME = new QName("http://ws.airline/", "cancelFlight");
    private final static QName _FlightBookException_QNAME = new QName("http://ws.airline/", "FlightBookException");
    private final static QName _GetFlightsResponse_QNAME = new QName("http://ws.airline/", "getFlightsResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: airline.ws
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link BookFlightResponse }
     * 
     */
    public BookFlightResponse createBookFlightResponse() {
        return new BookFlightResponse();
    }

    /**
     * Create an instance of {@link GetFlights }
     * 
     */
    public GetFlights createGetFlights() {
        return new GetFlights();
    }

    /**
     * Create an instance of {@link FlightCancelException }
     * 
     */
    public FlightCancelException createFlightCancelException() {
        return new FlightCancelException();
    }

    /**
     * Create an instance of {@link CancelFlightResponse }
     * 
     */
    public CancelFlightResponse createCancelFlightResponse() {
        return new CancelFlightResponse();
    }

    /**
     * Create an instance of {@link BookFlight }
     * 
     */
    public BookFlight createBookFlight() {
        return new BookFlight();
    }

    /**
     * Create an instance of {@link CancelFlight }
     * 
     */
    public CancelFlight createCancelFlight() {
        return new CancelFlight();
    }

    /**
     * Create an instance of {@link FlightBookException }
     * 
     */
    public FlightBookException createFlightBookException() {
        return new FlightBookException();
    }

    /**
     * Create an instance of {@link GetFlightsResponse }
     * 
     */
    public GetFlightsResponse createGetFlightsResponse() {
        return new GetFlightsResponse();
    }

    /**
     * Create an instance of {@link FlightInformation }
     * 
     */
    public FlightInformation createFlightInformation() {
        return new FlightInformation();
    }

    /**
     * Create an instance of {@link Flight }
     * 
     */
    public Flight createFlight() {
        return new Flight();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FlightCancelException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.airline/", name = "FlightCancelException")
    public JAXBElement<FlightCancelException> createFlightCancelException(FlightCancelException value) {
        return new JAXBElement<FlightCancelException>(_FlightCancelException_QNAME, FlightCancelException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CancelFlightResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.airline/", name = "cancelFlightResponse")
    public JAXBElement<CancelFlightResponse> createCancelFlightResponse(CancelFlightResponse value) {
        return new JAXBElement<CancelFlightResponse>(_CancelFlightResponse_QNAME, CancelFlightResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BookFlightResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.airline/", name = "bookFlightResponse")
    public JAXBElement<BookFlightResponse> createBookFlightResponse(BookFlightResponse value) {
        return new JAXBElement<BookFlightResponse>(_BookFlightResponse_QNAME, BookFlightResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetFlights }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.airline/", name = "getFlights")
    public JAXBElement<GetFlights> createGetFlights(GetFlights value) {
        return new JAXBElement<GetFlights>(_GetFlights_QNAME, GetFlights.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BookFlight }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.airline/", name = "bookFlight")
    public JAXBElement<BookFlight> createBookFlight(BookFlight value) {
        return new JAXBElement<BookFlight>(_BookFlight_QNAME, BookFlight.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CancelFlight }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.airline/", name = "cancelFlight")
    public JAXBElement<CancelFlight> createCancelFlight(CancelFlight value) {
        return new JAXBElement<CancelFlight>(_CancelFlight_QNAME, CancelFlight.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FlightBookException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.airline/", name = "FlightBookException")
    public JAXBElement<FlightBookException> createFlightBookException(FlightBookException value) {
        return new JAXBElement<FlightBookException>(_FlightBookException_QNAME, FlightBookException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetFlightsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.airline/", name = "getFlightsResponse")
    public JAXBElement<GetFlightsResponse> createGetFlightsResponse(GetFlightsResponse value) {
        return new JAXBElement<GetFlightsResponse>(_GetFlightsResponse_QNAME, GetFlightsResponse.class, null, value);
    }

}
