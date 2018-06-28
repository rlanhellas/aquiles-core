package br.com.aquiles.core.validator.validation.error;

import br.com.aquiles.core.validator.validation.InvalidValue;

/**
 * 
 * Representa possIveis erros do CNPJ
 * 
 */
public enum CNPJError implements InvalidValue {
    INVALID_CHECK_DIGITS, INVALID_DIGITS, INVALID_FORMAT
}
