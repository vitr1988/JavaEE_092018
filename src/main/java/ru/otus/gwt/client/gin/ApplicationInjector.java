package ru.otus.gwt.client.gin;

import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import ru.otus.gwt.client.service.ApplicationServiceAsync;
import ru.otus.gwt.client.text.ApplicationConstants;

@GinModules(ApplicationGinModule.class)
public interface ApplicationInjector extends Ginjector {
    ApplicationServiceAsync getService();
    ApplicationConstants getConstants();
}
