package ru.otus.webservice.soap.server.date;

import javax.jws.WebMethod;
import javax.jws.WebResult;

public interface DateProvider {

    @WebMethod
    @WebResult(name="currentDate")
    String getCurrentDate();
}
