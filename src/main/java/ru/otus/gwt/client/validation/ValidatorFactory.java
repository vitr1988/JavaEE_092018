package ru.otus.gwt.client.validation;

import com.google.gwt.validation.client.AbstractGwtValidatorFactory;
import com.google.gwt.validation.client.GwtValidation;
import com.google.gwt.validation.client.impl.AbstractGwtValidator;
import ru.otus.gwt.shared.User;

import javax.validation.Validator;

import static ru.otus.gwt.client.gin.ApplicationInjector.INSTANCE;

public class ValidatorFactory extends AbstractGwtValidatorFactory {

    /**
     * Validator marker for the Validation Sample project. Only the classes and groups listed
     * in the {@link GwtValidation} annotation can be validated.
     */
    @GwtValidation(User.class)
    public interface GwtValidator extends Validator {
    }

    @Override
    public AbstractGwtValidator createValidator() {
        return (AbstractGwtValidator) INSTANCE.getValidator();
    }
}
