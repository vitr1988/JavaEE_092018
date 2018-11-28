package ru.otus.webservice.rest.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;
import org.glassfish.jersey.jackson.internal.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.validation.ValidationFeature;
import ru.otus.webservice.rest.CalculatorImpl;
import ru.otus.webservice.rest.ReportResource;
import ru.otus.webservice.rest.UserResource;
import ru.otus.webservice.rest.exception.RuntimeExceptionMapper;
import ru.otus.webservice.rest.filter.CorsFilter;

import javax.ws.rs.ApplicationPath;
import java.nio.charset.StandardCharsets;

import static ru.otus.webservice.rest.config.RestApplication.API_URL;

@ApplicationPath(API_URL)
public class RestApplication extends ResourceConfig {

    public static final String API_URL = "/api";

    private static final String ENCODING_PROPERTY = "encoding";

    private static final String DEFAULT_SCHEME_NAME = "http";

    private static final String RESOURCE_PACKAGE = UserResource.class.getPackage().getName();

    public RestApplication(){
        registerResourceClasses();
        registerExceptionMapperClasses();
        registerValidationClasses();
        registerFilterClasses();

        // publish swagger doc
        registerSwaggerClasses();
        publishSwaggerDescription();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        JacksonJaxbJsonProvider jacksonProvider = new JacksonJaxbJsonProvider();
        jacksonProvider.setMapper(objectMapper);
        register(jacksonProvider);

        property(ENCODING_PROPERTY, StandardCharsets.UTF_8.toString());
    }

    private void registerResourceClasses() {
        register(CalculatorImpl.class);
        register(UserResource.class);
        register(ReportResource.class);
    }

    private void registerExceptionMapperClasses() {
        register(RuntimeExceptionMapper.class);
    }

    private void registerValidationClasses() {
        register(ValidationFeature.class);
    }

    private void registerFilterClasses() {
        register(CorsFilter.class);
    }

    private void registerSwaggerClasses() {
        register(ApiListingResource.class);
        register(SwaggerSerializers.class);
    }

    public void publishSwaggerDescription(){
        //SwaggerUI http://localhost:8080/api-docs
        //Tomcat suitable approach swagger declaration
//        BeanConfig beanConfig = new BeanConfig();
//        beanConfig.setTitle("Swagger JAX-RS Integration Example");
//        beanConfig.setDescription("A simple Maven JAX-RS project.");
//        beanConfig.setVersion("1.0.0");
//        beanConfig.setSchemes(new String[]{DEFAULT_SCHEME_NAME});
//        beanConfig.setBasePath(API_URL);
//        beanConfig.setResourcePackage(RESOURCE_PACKAGE);
//        beanConfig.setPrettyPrint(true);
//        beanConfig.setScan(true);
    }
}
