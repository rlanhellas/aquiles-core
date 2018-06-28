package br.com.aquiles.core.validator.validation.error;

import br.com.aquiles.core.validator.validation.InvalidValue;

/**
 * 
 * Representa possIveis erros durante validacao de uma inscricao estadual
 * 
 */
public enum IEError implements InvalidValue {
    INVALID_CHECK_DIGITS, INVALID_DIGITS, INVALID_FORMAT, UNDEFINED_STATE
}
