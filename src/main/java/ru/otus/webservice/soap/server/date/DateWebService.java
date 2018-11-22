package ru.otus.webservice.soap.server.date;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@WebService(serviceName = "DateWebService", name = "DateProvider")
public class DateWebService implements DateProvider {

    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    @WebMethod
    public String getCurrentDate() {
        return dateTimeFormatter.format(LocalDateTime.now());
    }
}
