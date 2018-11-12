package ru.otus.jsf.validator;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;

@FacesValidator(value = "fooValidator")
public class CustomValidator implements Validator {
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) {
                // use the injected artifact
    }
}
