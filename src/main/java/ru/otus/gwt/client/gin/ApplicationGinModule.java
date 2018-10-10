package ru.otus.gwt.client.gin;

import com.google.gwt.inject.client.AbstractGinModule;
import ru.otus.gwt.client.service.ApplicationService;
import ru.otus.gwt.client.text.ApplicationConstants;

public class ApplicationGinModule extends AbstractGinModule {
    protected void configure() {
        bind(ApplicationService.class);
        bind(ApplicationConstants.class);
    }
}
