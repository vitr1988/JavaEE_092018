package ru.otus.webservice.soap.server.date;

import ru.otus.webservice.soap.server.client.DateProvider;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@WebService(serviceName = "DateWebService", name = "DateProvider")
public class DateWebService implements DateProvider {

    @WebMethod
    @WebResult(name="currentDate")
    public String getCurrentDate() {
        return DateTimeFormatter.ofPattern("dd.MM.yyyy").format(LocalDateTime.now());
    }
}
