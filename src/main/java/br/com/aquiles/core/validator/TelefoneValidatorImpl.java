package br.com.aquiles.core.validator;

import br.com.aquiles.core.annotation.validator.TelefoneValidator;
import br.com.aquiles.core.util.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by rlanhellas on 23/05/2017.
 */
public class TelefoneValidatorImpl implements ConstraintValidator<TelefoneValidator, String> {

    @Override
    public void initialize(TelefoneValidator constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        String valueSemMascara = StringUtils.removerMascaras(value);
        if (valueSemMascara == null || valueSemMascara.trim().equals("")) {
            return true;
        }

        if (valueSemMascara.trim().length() < 8) {
            return false;
        }

        if (!NumberUtils.isNumber(valueSemMascara.trim())) {
            return false;
        }

        for (int i = 0; i < 10; i++) {
            if (valueSemMascara.replace(String.valueOf(i), "").trim().equals("")) {
                return false;
            }
        }

        return true;
    }
}
