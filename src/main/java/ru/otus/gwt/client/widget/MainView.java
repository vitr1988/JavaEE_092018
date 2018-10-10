package ru.otus.gwt.client.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import ru.otus.gwt.client.ApplicationServiceAsync;
import ru.otus.gwt.shared.User;
import ru.otus.gwt.shared.exception.WrongCredentialException;
import ru.otus.gwt.shared.validation.ValidationRule;

public class MainView extends Composite {
    @UiTemplate("MainPart.ui.xml")
    interface MainViewUiBinder extends UiBinder<VerticalPanel, MainView> {
    }

    @UiField
    TextBox loginTextField;

    @UiField
    PasswordTextBox passwordTextField;

    @UiField
    Button submit;

    private static MainViewUiBinder ourUiBinder = GWT.create(MainViewUiBinder.class);

    public MainView(ApplicationServiceAsync service) {
        initWidget(ourUiBinder.createAndBindUi(this));

        submit.addClickHandler((event) -> {
            User user = new User(loginTextField.getValue(), passwordTextField.getValue());
            if (ValidationRule.isValid(user)) {
                service.authorize(user, new AsyncCallback<Void>() {
                    @Override
                    public void onFailure(Throwable caught) {
                        if (caught instanceof WrongCredentialException) {
                            Window.alert(caught.getLocalizedMessage());
                        }
                    }
                    @Override
                    public void onSuccess(Void result) {
                        Window.alert("Вход успешен!");
                    }
                });
            }
        });
    }

    public HandlerRegistration addClickHandler(ClickHandler handler) {
        return submit.addClickHandler(handler);
    }
}