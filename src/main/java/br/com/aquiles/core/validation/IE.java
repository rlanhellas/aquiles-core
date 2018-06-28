package br.com.aquiles.core.validation;

import br.com.aquiles.core.validation.logic.AquilesIEValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;

/**
 * Restricao que pode ser associada a classes que contenham um objeto que
 * represente uma Inscricao Estadual e outro objeto identificando o estado a que
 * este documento pertence.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ TYPE })
@Constraint(validatedBy = AquilesIEValidator.class)
public @interface IE {
	String message() default "{br.com.aquiles.core.bean.validation.IE.message}";

	String ieField() default "ie";

	String estadoField() default "estado";

	boolean formatted() default false;
	
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};


}
