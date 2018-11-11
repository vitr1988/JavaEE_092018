package ru.otus.gwt.client.gin;

import com.google.gwt.inject.client.AbstractGinModule;
import ru.otus.gwt.client.service.ApplicationService;
import ru.otus.gwt.client.service.ContactService;
import ru.otus.gwt.client.text.ApplicationConstants;
import ru.otus.gwt.client.validation.ValidatorFactory.GwtValidator;
import ru.otus.gwt.client.view.ContactViewImpl;
import ru.otus.gwt.client.view.EditContactViewImpl;
import ru.otus.gwt.client.widget.MainView.MainViewUiBinder;
import ru.otus.gwt.client.widget.image.ApplicationImages;

public class ApplicationGinModule extends AbstractGinModule {
    protected void configure() {
        bind(ApplicationService.class);
        bind(ContactService.class);
        bind(ApplicationConstants.class);
        bind(MainViewUiBinder.class);
        bind(GwtValidator.class);
        bind(ApplicationImages.class);
        bind(ContactViewImpl.ContactView.class);
        bind(EditContactViewImpl.EditContactView.class);
    }
}
