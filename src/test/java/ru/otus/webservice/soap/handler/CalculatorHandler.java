package ru.otus.webservice.soap.handler;

import javax.xml.namespace.QName;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import java.util.Collections;
import java.util.Set;

public class CalculatorHandler implements SOAPHandler<SOAPMessageContext> {

    @Override
    public boolean handleMessage(SOAPMessageContext mc) {
        System.out.println("Server : handleMessage()......");

        return true;
    }

    @Override
    public Set<QName> getHeaders() {
        System.out.println("Server : getHeaders()......");
        return Collections.emptySet();
    }

    @Override
    public void close(MessageContext mc) {
        System.out.println("Server : close()......");
    }

    @Override
    public boolean handleFault(SOAPMessageContext mc) {
        System.out.println("Server : handleFault()......");
        return true;
    }
}