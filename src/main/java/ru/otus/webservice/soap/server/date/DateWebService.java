package ru.otus.webservice.soap.server.date;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@WebService(serviceName = "DateWebService", name = "DateProvider")
//@SOAPBinding(style = SOAPBinding.Style.RPC)
public class DateWebService implements DateProvider {

    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    @WebMethod
    public String getCurrentDate() {
        return dateTimeFormatter.format(LocalDateTime.now());
    }
}
