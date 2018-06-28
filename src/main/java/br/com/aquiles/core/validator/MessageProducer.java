package br.com.aquiles.core.validator;

import br.com.aquiles.core.validator.validation.InvalidValue;

/**
 * <p>
 * MessageProducers responsavel pela busca de mensagens de validacao.
 * </p>
 * 
 */
public interface MessageProducer {
    /**
     * @param invalidValue
     *            valor invalido ao qual se procura a mensagem associada.
     * @return mensagem de validacao associada ao erro.
     */
    ValidationMessage getMessage(InvalidValue valorInvalido);
}
