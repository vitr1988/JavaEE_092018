package ru.otus.gwt.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;
import ru.otus.gwt.client.event.*;
import ru.otus.gwt.client.presenter.ContactPresenter;
import ru.otus.gwt.client.presenter.EditContactPresenter;
import ru.otus.gwt.client.presenter.Presenter;
import ru.otus.gwt.client.service.ContactServiceAsync;
import ru.otus.gwt.client.view.ContactViewImpl;
import ru.otus.gwt.client.view.EditContactViewImpl;

public class AppController implements Presenter, ValueChangeHandler<String> {

  private static final String ADD_HISTORY_TOKEN_NAME = "add";
  private static final String EDIT_HISTORY_TOKEN_NAME = "edit";
  private static final String LIST_HISTORY_TOKEN_NAME = "list";

  private final HandlerManager eventBus;
  private final ContactServiceAsync rpcService;
  private HasWidgets container;
  private ContactViewImpl contactView;
  private EditContactViewImpl editContactView;

  public AppController(ContactServiceAsync rpcService, HandlerManager eventBus) {
    this.eventBus = eventBus;
    this.rpcService = rpcService;
    bind();
  }

  private void bind() {
    History.addValueChangeHandler(this);

    eventBus.addHandler(AddContactEvent.TYPE, event -> doAddNewContact());

    eventBus.addHandler(EditContactEvent.TYPE, event -> doEditContact(event.getId()));

    eventBus.addHandler(EditContactCancelledEvent.TYPE, event -> doEditContactCancelled());

    eventBus.addHandler(ContactUpdatedEvent.TYPE, event -> doContactUpdated());
  }

  private void doAddNewContact() {
    History.newItem(ADD_HISTORY_TOKEN_NAME);
  }

  private void doEditContact(String id) {
    History.newItem(EDIT_HISTORY_TOKEN_NAME, false);
    Presenter presenter = new EditContactPresenter(rpcService, eventBus,
            new EditContactViewImpl(), id);
    presenter.go(container);
  }

  private void doEditContactCancelled() {
    History.newItem(LIST_HISTORY_TOKEN_NAME);
  }

  private void doContactUpdated() {
    History.newItem(LIST_HISTORY_TOKEN_NAME);
  }

  @Override
  public void go(final HasWidgets container) {
    this.container = container;

    if ("".equals(History.getToken())) {
      History.newItem(LIST_HISTORY_TOKEN_NAME);
    }
    else {
      History.fireCurrentHistoryState();
    }
  }

  @Override
  public void onValueChange(ValueChangeEvent<String> event) {
    String token = event.getValue();
    if (token != null) {
      switch (token) {
        case LIST_HISTORY_TOKEN_NAME: {
          GWT.runAsync(new RunAsyncCallback() {
            @Override
            public void onFailure(Throwable caught) {
            }

            @Override
            public void onSuccess() {
              // lazily initialize our views, and keep them around to be reused
              if (contactView == null) {
                contactView = new ContactViewImpl();
              }
              new ContactPresenter(rpcService, eventBus, new ContactViewImpl()).go(container);
            }
          });
          break;
        }
        case EDIT_HISTORY_TOKEN_NAME:
        case ADD_HISTORY_TOKEN_NAME: {
          GWT.runAsync(new RunAsyncCallback() {
            @Override
            public void onFailure(Throwable caught) {
            }

            @Override
            public void onSuccess() {
              // lazily initialize our views, and keep them around to be reused
              if (editContactView == null) {
                editContactView = new EditContactViewImpl();
              }
              new EditContactPresenter(rpcService, eventBus, editContactView).go(container);
            }
          });
          break;
        }
      }
    }
  }
}
