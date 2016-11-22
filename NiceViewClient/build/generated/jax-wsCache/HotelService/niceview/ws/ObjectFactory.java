
package niceview.ws;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the niceview.ws package. 
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
    private final static QName _CancelHotel_QNAME = new QName("http://ws.hotel/", "cancelHotel");
    private final static QName _CancelHotelResponse_QNAME = new QName("http://ws.hotel/", "cancelHotelResponse");
    private final static QName _GetHotels_QNAME = new QName("http://ws.hotel/", "getHotels");
    private final static QName _BookHotelResponse_QNAME = new QName("http://ws.hotel/", "bookHotelResponse");
    private final static QName _CreditCardFault_QNAME = new QName("http://fastmoney.imm.dtu.dk", "CreditCardFault");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: niceview.ws
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CreditCardInfoType }
     * 
     */
    public CreditCardInfoType createCreditCardInfoType() {
        return new CreditCardInfoType();
    }

    /**
     * Create an instance of {@link CreditCardFaultType }
     * 
     */
    public CreditCardFaultType createCreditCardFaultType() {
        return new CreditCardFaultType();
    }

    /**
     * Create an instance of {@link HotelInformation }
     * 
     */
    public HotelInformation createHotelInformation() {
        return new HotelInformation();
    }

    /**
     * Create an instance of {@link Address }
     * 
     */
    public Address createAddress() {
        return new Address();
    }

    /**
     * Create an instance of {@link Hotel }
     * 
     */
    public Hotel createHotel() {
        return new Hotel();
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
     * Create an instance of {@link BookHotelResponse }
     * 
     */
    public BookHotelResponse createBookHotelResponse() {
        return new BookHotelResponse();
    }

    /**
     * Create an instance of {@link CreditCardInfoType.ExpirationDate }
     * 
     */
    public CreditCardInfoType.ExpirationDate createCreditCardInfoTypeExpirationDate() {
        return new CreditCardInfoType.ExpirationDate();
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
     * Create an instance of {@link JAXBElement }{@code <}{@link CreditCardFaultType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://fastmoney.imm.dtu.dk", name = "CreditCardFault")
    public JAXBElement<CreditCardFaultType> createCreditCardFault(CreditCardFaultType value) {
        return new JAXBElement<CreditCardFaultType>(_CreditCardFault_QNAME, CreditCardFaultType.class, null, value);
    }

}
