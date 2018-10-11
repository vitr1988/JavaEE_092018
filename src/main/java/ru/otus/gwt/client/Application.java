package ru.otus.gwt.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import ru.otus.gwt.client.service.ApplicationServiceAsync;
import ru.otus.gwt.client.text.ApplicationConstants;
import ru.otus.gwt.client.widget.MainView;
import ru.otus.gwt.shared.User;
import ru.otus.gwt.shared.exception.WrongCredentialException;
import ru.otus.gwt.shared.validation.ValidationRule;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

import static ru.otus.gwt.client.gin.ApplicationInjector.INSTANCE;

/**
 * Entry point classes define <code>onModuleLoad()</code>
 */
public class Application implements EntryPoint {

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
        loginLabel.getElement().getStyle().setFontStyle(Style.FontStyle.ITALIC);
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

    private Image loginInvalidFieldImage, passwordInvalidFieldImage;

    private void initMainSlot(){
//        Panel loginPanel = initAndGetLoginPanel();
//        Panel passwordPanel = initAndGetPasswordPanel();
//        final Button button = new Button(dictionary.logon_button_alt());
//        Panel buttonPanel = initAndGetSubmitPanel(button);
//
//        VerticalPanel mainPanel = new VerticalPanel();
//        mainPanel.add(loginPanel);
//        mainPanel.add(passwordPanel);
//        mainPanel.add(buttonPanel);
//        mainPanel.setCellHorizontalAlignment(buttonPanel, HasHorizontalAlignment.ALIGN_CENTER);
//
//        button.addClickHandler(event -> {
//            User user = new User(getLogin(), getPassword());
//            Set<ConstraintViolation<User>> errors = ValidationRule.getErrors(user);
//            clearErrors();
//            if (errors.isEmpty()) {
//                service.authorize(user, new AsyncCallback<Void>() {
//                    @Override
//                    public void onFailure(Throwable caught) {
//                        if (caught instanceof WrongCredentialException) {
//                            Window.alert(caught.getLocalizedMessage());
//                        }
//                        else if (caught instanceof ConstraintViolationException) {
//                            Window.alert(caught.getLocalizedMessage());
//                        }
//                    }
//                    @Override
//                    public void onSuccess(Void result) {
//                        Window.alert(INSTANCE.getConstants().logon_success());
//                    }
//                });
//            }
//            else {
//                errors.stream().forEach(e -> {
//                    String propertyName = e.getPropertyPath().toString();
//                    if (propertyName.equals(User.LOGIN)) {
//                        loginInvalidFieldImage = showError(loginTextBox, loginPanel, e.getMessage());
//                    }
//                    else if (propertyName.equals(User.PASSWORD)){
//                        passwordInvalidFieldImage = showError(passwordTextBox, passwordPanel, e.getMessage());
//                    }
//                });
//            }
//        });
//        RootPanel.get("slot").add(mainPanel);
        RootPanel.get("slot").add(new MainView(service));
    }

    public Image showError(TextBox textBox, Panel panel, String error) {
        textBox.getElement().getStyle().setBorderColor("red");
        final Image fieldInvalidImage = new Image(INSTANCE.getImages().field_invalid());
        Style style = fieldInvalidImage.getElement().getStyle();
        style.setCursor(Style.Cursor.POINTER);
        style.setMargin(6, Style.Unit.PX);
        fieldInvalidImage.setTitle(error);
        panel.add(fieldInvalidImage);
        return fieldInvalidImage;
    }

    public void clearErrors(){
        loginTextBox.getElement().getStyle().clearBorderColor();
        if (loginInvalidFieldImage != null){
            loginInvalidFieldImage.removeFromParent();
        }
        passwordTextBox.getElement().getStyle().clearBorderColor();
        if (passwordInvalidFieldImage != null){
            passwordInvalidFieldImage.removeFromParent();
        }
    }
}
