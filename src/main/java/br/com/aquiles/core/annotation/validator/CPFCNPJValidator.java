package br.com.aquiles.core.annotation.validator;

import br.com.aquiles.core.validator.CPFCNPJValidatorImpl;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by rlanhellas on 23/05/2017.
 */
@Target({FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = CPFCNPJValidatorImpl.class)
@Documented
public @interface CPFCNPJValidator {

    String message() default "CPF/CNPJ inválido";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };

}
