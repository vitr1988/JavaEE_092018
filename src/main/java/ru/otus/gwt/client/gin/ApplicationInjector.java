package ru.otus.gwt.client.gin;

import com.google.gwt.core.client.GWT;
import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import ru.otus.gwt.client.service.ApplicationServiceAsync;
import ru.otus.gwt.client.text.ApplicationConstants;
import ru.otus.gwt.client.validation.ValidatorFactory.GwtValidator;
import ru.otus.gwt.client.widget.MainView.MainViewUiBinder;
import ru.otus.gwt.client.widget.image.ApplicationImages;

@GinModules(ApplicationGinModule.class)
public interface ApplicationInjector extends Ginjector {

    ApplicationInjector INSTANCE = GWT.create(ApplicationInjector.class);

    ApplicationServiceAsync getService();
    ApplicationConstants getConstants();
    MainViewUiBinder getUiBinder();
    GwtValidator getValidator();
    ApplicationImages getImages();
}
