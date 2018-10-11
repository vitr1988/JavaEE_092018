package ru.otus.gwt.client.widget;

import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import ru.otus.gwt.client.service.ApplicationServiceAsync;
import ru.otus.gwt.shared.User;
import ru.otus.gwt.shared.exception.WrongCredentialException;
import ru.otus.gwt.shared.validation.ValidationRule;

import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import java.util.Set;

import static ru.otus.gwt.client.gin.ApplicationInjector.INSTANCE;

public class MainView extends Composite {
    @UiTemplate("MainPart.ui.xml")
    public interface MainViewUiBinder extends UiBinder<VerticalPanel, MainView> {
    }

    @UiField
    TextBox loginTextField;

    @UiField
    PasswordTextBox passwordTextField;

    @UiField
    HorizontalPanel loginPanel;

    @UiField
    HorizontalPanel passwordPanel;

    @UiHandler("submit")
    void clickHandler(ClickEvent evt){
        User user = new User(loginTextField.getValue(), passwordTextField.getValue());
        Set<ConstraintViolation<User>> errors = ValidationRule.getErrors(user);
        clearErrors();
        if (errors.isEmpty()) {
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
        else {
            errors.stream().forEach(e -> {
                String propertyName = e.getPropertyPath().toString();
                if (propertyName.equals(User.LOGIN)) {
                    loginInvalidFieldImage = showError(loginTextField, loginPanel, e.getMessage());
                }
                else if (propertyName.equals(User.PASSWORD)){
                    passwordInvalidFieldImage = showError(passwordTextField, passwordPanel, e.getMessage());
                }
            });
        }
    }

    private static MainViewUiBinder ourUiBinder = INSTANCE.getUiBinder();

    private Image loginInvalidFieldImage, passwordInvalidFieldImage;

    private ApplicationServiceAsync service;

    @Inject
    public MainView(ApplicationServiceAsync service) {
        initWidget(ourUiBinder.createAndBindUi(this));
        this.service = service;
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
        loginTextField.getElement().getStyle().clearBorderColor();
        if (loginInvalidFieldImage != null){
            loginInvalidFieldImage.removeFromParent();
        }
        passwordTextField.getElement().getStyle().clearBorderColor();
        if (passwordInvalidFieldImage != null){
            passwordInvalidFieldImage.removeFromParent();
        }
    }
}