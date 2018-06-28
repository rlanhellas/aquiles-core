package br.com.aquiles.core.util;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import java.util.ArrayList;
import java.util.List;

public class ValidationUtil {
    /**
     * executar validação de constraint da classe para um parametro
     * @param parameter
     * @return lista de erros
     */
    public static <T extends Object>  List<String> validateParameter(T bean, String parameter){
        List<String> messages = new ArrayList<>();
        for (ConstraintViolation<T> errors: Validation.buildDefaultValidatorFactory().getValidator().validateProperty(bean,parameter)) {
            messages.add(errors.getMessage());
        }
        return messages;
    }

    /**
     * executar validação de constraint da classe
     * @param bean
     * @param <T>
     * @return
     */
    public static <T extends Object>  List<String> validate(T bean){
        List<String> messages = new ArrayList<>();
        for (ConstraintViolation<T> errors: Validation.buildDefaultValidatorFactory().getValidator().validate(bean)) {
            messages.add(errors.getMessage());
        }
        return messages;
    }
}
