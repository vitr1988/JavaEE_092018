package ru.otus.gwt.shared.validation;

import ru.otus.gwt.shared.User;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

public class ValidationRule {

    public static final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    public static boolean isValid(User user){
        return getErrors(user).isEmpty();
    }

    public static Set<ConstraintViolation<User>> getErrors(User user){
        return validator.validate(user);
    }
}
