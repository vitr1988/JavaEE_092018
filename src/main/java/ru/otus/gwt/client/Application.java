package ru.otus.gwt.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import ru.otus.gwt.client.gin.ApplicationInjector;
import ru.otus.gwt.client.service.ApplicationServiceAsync;
import ru.otus.gwt.client.text.ApplicationConstants;
import ru.otus.gwt.client.widget.MainView;
import ru.otus.gwt.shared.User;
import ru.otus.gwt.shared.exception.WrongCredentialException;
import ru.otus.gwt.shared.validation.ValidationRule;

import javax.validation.ConstraintViolationException;

/**
 * Entry point classes define <code>onModuleLoad()</code>
 */
public class Application implements EntryPoint {

    private static final ApplicationInjector INSTANCE = GWT.create(ApplicationInjector.class);

    private static ApplicationServiceAsync service = INSTANCE.getService();
    private static ApplicationConstants dictionary = INSTANCE.getConstants();

    public static final String LABEL_CLASS_NAME = "firstColumnWidth";
    public static final String INPUT_CLASS_NAME = "inputWidth";

    private TextBox loginTextBox;
    private PasswordTextBox passwordTextBox;

    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {
        initHeaderAndTitle();
        initMainSlot();
    }

    private Panel initAndGetPasswordPanel() {
        HorizontalPanel passwordPanel = new HorizontalPanel();
        Label passwordLabel = new Label(dictionary.password_label_alt());
        passwordPanel.add(passwordLabel);
        passwordLabel.addStyleName(LABEL_CLASS_NAME);
        passwordTextBox = new PasswordTextBox();
        passwordPanel.add(passwordTextBox);
        passwordTextBox.addStyleName(INPUT_CLASS_NAME);
        return passwordPanel;
    }

    private Panel initAndGetLoginPanel() {
        HorizontalPanel loginPanel = new HorizontalPanel();
        Label loginLabel = new Label(dictionary.login_label_alt());
        loginPanel.add(loginLabel);
        loginLabel.addStyleName(LABEL_CLASS_NAME);
        loginTextBox = new TextBox();
        loginPanel.add(loginTextBox);
        loginTextBox.addStyleName(INPUT_CLASS_NAME);
        loginTextBox.getElement().setAttribute("placeholder", dictionary.login_placeholder_alt());
        return loginPanel;
    }

    public String getLogin() {
        return loginTextBox.getText();
    }

    public String getPassword() {
        return passwordTextBox.getText();
    }

    private Panel initAndGetSubmitPanel(Button submit) {
        HorizontalPanel buttonPanel = new HorizontalPanel();
        buttonPanel.add(submit);
        return buttonPanel;
    }

    private void initHeaderAndTitle(){
        Document.get().getElementById("header").setInnerText(dictionary.form_header());
        Document.get().getElementById("title").setInnerText(dictionary.title());
    }

    private void initMainSlot(){
        Panel loginPanel = initAndGetLoginPanel();
        Panel passwordPanel = initAndGetPasswordPanel();
        final Button button = new Button(dictionary.logon_button_alt());
        Panel buttonPanel = initAndGetSubmitPanel(button);

        VerticalPanel mainPanel = new VerticalPanel();
        mainPanel.add(loginPanel);
        mainPanel.add(passwordPanel);
        mainPanel.add(buttonPanel);
        mainPanel.setCellHorizontalAlignment(buttonPanel, HasHorizontalAlignment.ALIGN_CENTER);

        button.addClickHandler((event) -> {
            User user = new User(getLogin(), getPassword());
            if (ValidationRule.isValid(user)) {
                service.authorize(user, new AsyncCallback<Void>() {
                    @Override
                    public void onFailure(Throwable caught) {
                        if (caught instanceof WrongCredentialException) {
                            Window.alert(caught.getLocalizedMessage());
                        }
                        else if (caught instanceof ConstraintViolationException) {
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

        RootPanel.get("slot").add(mainPanel);
//        RootPanel.get("slot").add(new MainView(service));
    }
}
