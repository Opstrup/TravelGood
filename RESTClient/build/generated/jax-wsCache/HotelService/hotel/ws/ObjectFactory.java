
package hotel.ws;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the hotel.ws package. 
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

    private final static QName _BookHotel_QNAME = new QName("http://ws.hotel/", "bookHotel");
    private final static QName _GetHotelsResponse_QNAME = new QName("http://ws.hotel/", "getHotelsResponse");
    private final static QName _HotelBookException_QNAME = new QName("http://ws.hotel/", "HotelBookException");
    private final static QName _CancelHotel_QNAME = new QName("http://ws.hotel/", "cancelHotel");
    private final static QName _CancelHotelResponse_QNAME = new QName("http://ws.hotel/", "cancelHotelResponse");
    private final static QName _GetHotels_QNAME = new QName("http://ws.hotel/", "getHotels");
    private final static QName _BookHotelResponse_QNAME = new QName("http://ws.hotel/", "bookHotelResponse");
    private final static QName _HotelCancelException_QNAME = new QName("http://ws.hotel/", "HotelCancelException");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: hotel.ws
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetHotelsResponse }
     * 
     */
    public GetHotelsResponse createGetHotelsResponse() {
        return new GetHotelsResponse();
    }

    /**
     * Create an instance of {@link BookHotel }
     * 
     */
    public BookHotel createBookHotel() {
        return new BookHotel();
    }

    /**
     * Create an instance of {@link GetHotels }
     * 
     */
    public GetHotels createGetHotels() {
        return new GetHotels();
    }

    /**
     * Create an instance of {@link CancelHotelResponse }
     * 
     */
    public CancelHotelResponse createCancelHotelResponse() {
        return new CancelHotelResponse();
    }

    /**
     * Create an instance of {@link CancelHotel }
     * 
     */
    public CancelHotel createCancelHotel() {
        return new CancelHotel();
    }

    /**
     * Create an instance of {@link HotelBookException }
     * 
     */
    public HotelBookException createHotelBookException() {
        return new HotelBookException();
    }

    /**
     * Create an instance of {@link HotelCancelException }
     * 
     */
    public HotelCancelException createHotelCancelException() {
        return new HotelCancelException();
    }

    /**
     * Create an instance of {@link BookHotelResponse }
     * 
     */
    public BookHotelResponse createBookHotelResponse() {
        return new BookHotelResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BookHotel }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.hotel/", name = "bookHotel")
    public JAXBElement<BookHotel> createBookHotel(BookHotel value) {
        return new JAXBElement<BookHotel>(_BookHotel_QNAME, BookHotel.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetHotelsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.hotel/", name = "getHotelsResponse")
    public JAXBElement<GetHotelsResponse> createGetHotelsResponse(GetHotelsResponse value) {
        return new JAXBElement<GetHotelsResponse>(_GetHotelsResponse_QNAME, GetHotelsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link HotelBookException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.hotel/", name = "HotelBookException")
    public JAXBElement<HotelBookException> createHotelBookException(HotelBookException value) {
        return new JAXBElement<HotelBookException>(_HotelBookException_QNAME, HotelBookException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CancelHotel }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.hotel/", name = "cancelHotel")
    public JAXBElement<CancelHotel> createCancelHotel(CancelHotel value) {
        return new JAXBElement<CancelHotel>(_CancelHotel_QNAME, CancelHotel.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CancelHotelResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.hotel/", name = "cancelHotelResponse")
    public JAXBElement<CancelHotelResponse> createCancelHotelResponse(CancelHotelResponse value) {
        return new JAXBElement<CancelHotelResponse>(_CancelHotelResponse_QNAME, CancelHotelResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetHotels }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.hotel/", name = "getHotels")
    public JAXBElement<GetHotels> createGetHotels(GetHotels value) {
        return new JAXBElement<GetHotels>(_GetHotels_QNAME, GetHotels.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BookHotelResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.hotel/", name = "bookHotelResponse")
    public JAXBElement<BookHotelResponse> createBookHotelResponse(BookHotelResponse value) {
        return new JAXBElement<BookHotelResponse>(_BookHotelResponse_QNAME, BookHotelResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link HotelCancelException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.hotel/", name = "HotelCancelException")
    public JAXBElement<HotelCancelException> createHotelCancelException(HotelCancelException value) {
        return new JAXBElement<HotelCancelException>(_HotelCancelException_QNAME, HotelCancelException.class, null, value);
    }

}
