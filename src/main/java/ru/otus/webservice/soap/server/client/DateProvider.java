
package ru.otus.webservice.soap.server.client;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "DateProvider", targetNamespace = "http://date.server.soap.webservice.otus.ru/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface DateProvider {


    /**
     * 
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(name = "currentDate", targetNamespace = "")
    @RequestWrapper(localName = "getCurrentDate", targetNamespace = "http://date.server.soap.webservice.otus.ru/", className = "ru.otus.webservice.soap.server.client.GetCurrentDate")
    @ResponseWrapper(localName = "getCurrentDateResponse", targetNamespace = "http://date.server.soap.webservice.otus.ru/", className = "ru.otus.webservice.soap.server.client.GetCurrentDateResponse")
    @Action(input = "http://date.server.soap.webservice.otus.ru/DateProvider/getCurrentDateRequest", output = "http://date.server.soap.webservice.otus.ru/DateProvider/getCurrentDateResponse")
    public String getCurrentDate();

}
