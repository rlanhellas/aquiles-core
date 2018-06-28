package br.com.aquiles.core.validation.logic;

import br.com.aquiles.core.validation.CPF;
import br.com.aquiles.core.validator.validation.CPFValidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Valida a cadeia gerada através do método {@linkplain #toString()} para
 * verificar se ela esta de acordo com o padrão de CPF.
 */
public class AquilesCPFValidator implements ConstraintValidator<CPF, String> {
	private CPFValidator validator;

	@Override
	public void initialize(CPF cpf) {
		AnnotationMessageProducer messageProducer = new AnnotationMessageProducer(cpf);
		validator = new CPFValidator(messageProducer, cpf.formatted(),cpf.ignoreRepeated());
	}

	@Override
	public boolean isValid(String cpf, ConstraintValidatorContext context) {
		if (cpf != null) {
			if (cpf.trim().length() == 0) {
				return true;
			} else {
				return validator.invalidMessagesFor(cpf).isEmpty();
			}
		} else {
			return true;
		}
	}
}
