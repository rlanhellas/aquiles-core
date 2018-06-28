package br.com.aquiles.core.validator;

import br.com.aquiles.core.annotation.validator.HTMLContentValidator;
import br.com.aquiles.core.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by rlanhellas on 23/05/2017.
 */
public class HTMLContentValidatorImpl implements ConstraintValidator<HTMLContentValidator, String> {

    @Override
    public void initialize(HTMLContentValidator constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if (value == null || value.trim().equalsIgnoreCase("")) {
            return true;
        }

        return !StringUtils.containsHTMLTag(value);
    }
}
