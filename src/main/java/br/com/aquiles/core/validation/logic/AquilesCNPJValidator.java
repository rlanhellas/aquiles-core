package br.com.aquiles.core.validation.logic;

import br.com.aquiles.core.validation.CNPJ;
import br.com.aquiles.core.validator.validation.CNPJValidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


/**
 * Valida a cadeia gerada através do método {@linkplain #toString()} para
 * verificar se ela esta de acordo com o padrão de CNPJ.
 */
public class AquilesCNPJValidator implements ConstraintValidator<CNPJ, String> {
	private CNPJValidator validator;

	public void initialize(CNPJ cnpj) {
		AnnotationMessageProducer messageProducer = new AnnotationMessageProducer(cnpj);
		validator = new CNPJValidator(messageProducer, cnpj.formatted());
	}

	public boolean isValid(String cnpj, ConstraintValidatorContext context) {
		if (cnpj != null) {
			if (cnpj.trim().length() == 0) {
				return true;
			} else {
				return validator.invalidMessagesFor(cnpj).isEmpty();
			}
		} else {
			return true;
		}
	}
}
