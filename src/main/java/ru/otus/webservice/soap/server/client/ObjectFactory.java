
package ru.otus.webservice.soap.server.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ru.otus.webservice.soap.server.date package. 
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

    private final static QName _GetCurrentDate_QNAME = new QName("http://date.server.soap.webservice.otus.ru/", "getCurrentDate");
    private final static QName _GetCurrentDateResponse_QNAME = new QName("http://date.server.soap.webservice.otus.ru/", "getCurrentDateResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ru.otus.webservice.soap.server.date
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetCurrentDate }
     * 
     */
    public GetCurrentDate createGetCurrentDate() {
        return new GetCurrentDate();
    }

    /**
     * Create an instance of {@link GetCurrentDateResponse }
     * 
     */
    public GetCurrentDateResponse createGetCurrentDateResponse() {
        return new GetCurrentDateResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCurrentDate }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://date.server.soap.webservice.otus.ru/", name = "getCurrentDate")
    public JAXBElement<GetCurrentDate> createGetCurrentDate(GetCurrentDate value) {
        return new JAXBElement<GetCurrentDate>(_GetCurrentDate_QNAME, GetCurrentDate.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCurrentDateResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://date.server.soap.webservice.otus.ru/", name = "getCurrentDateResponse")
    public JAXBElement<GetCurrentDateResponse> createGetCurrentDateResponse(GetCurrentDateResponse value) {
        return new JAXBElement<GetCurrentDateResponse>(_GetCurrentDateResponse_QNAME, GetCurrentDateResponse.class, null, value);
    }

}
