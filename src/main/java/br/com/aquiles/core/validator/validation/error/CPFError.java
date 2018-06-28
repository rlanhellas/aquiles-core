package br.com.aquiles.core.validator.validation.error;

import br.com.aquiles.core.validator.validation.InvalidValue;

/**
 * Representa possIveis erros na validacao do CPF
 * 
 */
public enum CPFError implements InvalidValue {
    INVALID_CHECK_DIGITS, INVALID_DIGITS, REPEATED_DIGITS, INVALID_FORMAT
}
