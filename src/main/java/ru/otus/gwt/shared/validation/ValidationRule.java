package ru.otus.gwt.shared.validation;

import ru.otus.gwt.shared.User;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

public class ValidationRule {

    public static boolean isValid(User user){
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        return violations.isEmpty();
    }
}
