package ru.otus.webservice.rest.config;

import ru.otus.ejb.entity.EmployeeResource;
import ru.otus.webservice.rest.CalculatorImpl;
import ru.otus.webservice.rest.ReportResource;
import ru.otus.webservice.rest.UserResource;
import ru.otus.webservice.rest.exception.RuntimeExceptionMapper;
import ru.otus.webservice.rest.filter.CorsFilter;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static ru.otus.webservice.rest.config.RestApplication.API_URL;

@ApplicationPath(API_URL)
public class RestApplication extends Application {

    public static final String API_URL = "/api";

    private static final String ENCODING_PROPERTY = "encoding";

    private Set<Class<?>> classes = new HashSet<>();

    public RestApplication(){
        registerResourceClasses();
        registerExceptionMapperClasses();
        registerFilterClasses();
    }

    private void registerResourceClasses() {
        register(CalculatorImpl.class);
        register(UserResource.class);
        register(ReportResource.class);
        register(EmployeeResource.class);
    }

    private void registerExceptionMapperClasses() {
        register(RuntimeExceptionMapper.class);
    }

    private void registerFilterClasses() {
        register(CorsFilter.class);
    }

    public void register(Class clazz){
        classes.add(clazz);
    }

    @Override
    public Set<Class<?>> getClasses() {
        return classes;
    }

    @Override
    public Map<String, Object> getProperties() {
        return new HashMap<String, Object>(){{
            put(ENCODING_PROPERTY, StandardCharsets.UTF_8.toString());
        }};
    }
}
