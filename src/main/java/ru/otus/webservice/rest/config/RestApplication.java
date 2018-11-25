package ru.otus.webservice.rest.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.glassfish.jersey.jackson.internal.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.validation.ValidationFeature;
import ru.otus.webservice.rest.CalculatorImpl;
import ru.otus.webservice.rest.exception.RuntimeExceptionMapper;

import javax.ws.rs.ApplicationPath;
import java.nio.charset.StandardCharsets;

import static ru.otus.webservice.rest.config.RestApplication.API_URL;

@ApplicationPath(API_URL)
public class RestApplication extends ResourceConfig {

    public static final String API_URL = "/api";

    private static final String ENCODING_PROPERTY = "encoding";

    public RestApplication(){
        registerResourceClasses();
        registerExceptionMapperClasses();
        registerValidationClasses();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        JacksonJaxbJsonProvider jacksonProvider = new JacksonJaxbJsonProvider();
        jacksonProvider.setMapper(objectMapper);
        register(jacksonProvider);

        property(ENCODING_PROPERTY, StandardCharsets.UTF_8.toString());
    }

    private void registerResourceClasses() {
        register(CalculatorImpl.class);
    }

    private void registerExceptionMapperClasses() {
        register(RuntimeExceptionMapper.class);
    }

    private void registerValidationClasses() {
        register(ValidationFeature.class);
    }
}
