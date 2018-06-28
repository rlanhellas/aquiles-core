package br.com.aquiles.core.validation;

import br.com.aquiles.core.validation.logic.AquilesCPFValidator;

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
 * {@linkplain #toString()} represente um CPF.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ FIELD, METHOD })
@Constraint(validatedBy = AquilesCPFValidator.class)
public @interface CPF {
	String message() default "{br.com.aquiles.core.bean.validation.CPF.message}";

	boolean formatted() default false;
	
	boolean ignoreRepeated() default false;
    
	Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
