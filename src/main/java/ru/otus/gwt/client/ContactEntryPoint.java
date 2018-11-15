package ru.otus.gwt.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.RootPanel;
import ru.otus.gwt.client.gin.ApplicationInjector;
import ru.otus.gwt.client.service.ContactService;
import ru.otus.gwt.client.service.ContactServiceAsync;

public class ContactEntryPoint implements EntryPoint {

  @Override
  public void onModuleLoad() {
    HandlerManager eventBus = new HandlerManager(null);
    AppController controller = new AppController(ApplicationInjector.INSTANCE.getContactService(), eventBus);
    controller.go(RootPanel.get());
  }
}
