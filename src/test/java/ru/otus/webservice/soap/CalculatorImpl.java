package ru.otus.webservice.soap;

import ru.otus.webservice.soap.server.Calculator;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService
//@HandlerChain(file = "handlers.xml")
public class CalculatorImpl implements Calculator {

    /**
     * @param number1      first argument
     * @param number2       second argument
     * @return The sum of number1 and number2.
     */
    @WebMethod
    @WebResult
    public int add(
            @WebParam int number1,
            @WebParam int number2){
        return number1 + number2;
    }
}
