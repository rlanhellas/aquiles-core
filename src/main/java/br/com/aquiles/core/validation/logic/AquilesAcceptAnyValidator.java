package br.com.aquiles.core.validation.logic;

import br.com.aquiles.core.validation.AcceptAny;
import br.com.aquiles.core.validator.validation.AcceptAnyValidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Valida a cadeia gerada através do método {@linkplain #toString()} para
 * verificar se ela esta de acordo com o padrão de qualquer um dos documentos
 * passados como parametro.
 */
public class AquilesAcceptAnyValidator implements ConstraintValidator<AcceptAny, String> {
	private AcceptAnyValidator validator;

	public void initialize(AcceptAny annotation) {
		AnnotationMessageProducer messageProducer = new AnnotationMessageProducer(annotation);
		validator = new AcceptAnyValidator(messageProducer, annotation.formatted(), annotation.documentos());
	}

	public boolean isValid(String document, ConstraintValidatorContext context) {
		if (document != null) {
			if (document.trim().length() == 0) {
				return true;
			} else {
				return validator.invalidMessagesFor(document).isEmpty();
			}
		} else {
			return true;
		}
	}
}
