package br.com.aquiles.core.validation.logic;

import br.com.aquiles.core.validation.IE;
import br.com.aquiles.core.validator.type.Estado;
import br.com.aquiles.core.validator.validation.Validator;
import net.vidageek.mirror.dsl.Mirror;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Valida a cadeia gerada através do método {@linkplain #toString()} para
 * verificar se ela esta de acordo com o padrão de Inscricao Estadual, no estado
 * epecificado.
 */
public class AquilesIEValidator implements ConstraintValidator<IE, Object> {

	private Validator<String> validator;

	private IE ie;

	public boolean isValid(Object value, ConstraintValidatorContext context) {
		if (value != null) {
			String ieValue = getIEValue(value);
			String estadoValue = getEstadoValue(value);
			final AnnotationMessageProducer annotationMessageProducer = new AnnotationMessageProducer(
					ie);
			if (ieValue.trim().length() == 0) {
				return true;
			} else {
				try {
					final Estado estado = Estado.valueOf(estadoValue);
					validator = estado.getIEValidator(annotationMessageProducer, ie.formatted());
				} catch (IllegalArgumentException e) {
					return false;
				}
				return validator.invalidMessagesFor(ieValue).isEmpty();
			}
		} else {
			return true;
		}
	}

	public void initialize(IE ie) {
		this.ie = ie;
	}

	private String getEstadoValue(final Object obj) {
		return new Mirror().on(obj).invoke()
				.method(camelCaseGetFieldName(ie.estadoField())).withoutArgs()
				.toString();
	}

	private String getIEValue(final Object obj) {
		return new Mirror().on(obj).invoke()
				.method(camelCaseGetFieldName(ie.ieField())).withoutArgs()
				.toString();
	}

	private String camelCaseGetFieldName(final String fieldName) {
		return "get" + fieldName.substring(0, 1).toUpperCase()
				+ fieldName.substring(1);
	}

}
