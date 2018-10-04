package ru.otus.json.util;

import javax.xml.bind.JAXB;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;

public class Util {

    public static String marshal(Object object) throws IOException {
        try(StringWriter sw = new StringWriter()) {
            JAXB.marshal(object, sw);
            return sw.toString();
        }
    }

    public static <T> T unmarshal(String xmlString, Class<T> clazz) throws IOException {
        try (Reader reader = new StringReader(xmlString)){
            return JAXB.unmarshal(reader, clazz);
        }
    }
}
