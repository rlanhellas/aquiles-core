package br.com.aquiles.core.validation;

import br.com.aquiles.core.validation.logic.AquilesCNPJValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;

/**
 * Restricao que pode ser associada a objetos em que o método
 * {@linkplain #toString()} represente um CNPJ.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target( { FIELD, METHOD })
@Constraint(validatedBy = AquilesCNPJValidator.class)
public @interface CNPJ {
    String message() default "{br.com.aquiles.core.bean.validation.CNPJ.message}";

    boolean formatted() default false;
    
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
