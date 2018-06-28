package br.com.aquiles.core.validator;

/**
 * Mensagens de erro sobre a validacao de um objeto devem implementar essa
 * interfaces.
*/
public interface ValidationMessage {
    /**
     * @return mensagem de validacao armazenda.
     */
    String getMessage();
}
