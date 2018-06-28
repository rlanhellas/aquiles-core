package br.com.aquiles.core.validator.validation;

import br.com.aquiles.core.validator.ValidationMessage;

import java.util.List;

/**
 * <p>
 * Interface que os validadores devem implementar.</p>
 * <p>
 * {@link br.com.aquiles.core.validator.validation.Validator#invalidMessagesFor(Object)} devolve os problemas de validacao
 * associado ao objeto.</p>
 * <p>
 * {@link br.com.aquiles.core.validator.validation.Validator#assertValid(Object)} valida o objeto e lança uma exception no caso
 * invalido.
 * 
 * </p>
 * 
 * @param <T>
 *            tipo de objeto a ser validado
 */
public interface Validator<T> {
    /**
     * @param object
     *            objeto que se deseja validar
     * @throws InvalidStateException se o parametro não for valido,
     *         contendo a lista de ValidationMessage
     */
    void assertValid(T object);

    /**
     * @param object Objeto a ser validado.
     * @return as mensagens de de validacao relativas ao paremetro object
     */
    List<ValidationMessage> invalidMessagesFor(T object);

    /**
     * @param object objeto que se deseja avaliar
     * @return <code>true</code> se o objeto é elegIvel para a validacao por
     *         este validador, e <code>false</code> caso contrario
     */
    boolean isEligible(T object);

    /**
     * @return um objeto aleatorio valido de acordo com as regras de validacao deste validador
     */
    T generateRandomValid();
}
