package br.com.aquiles.core.validator.validation;

import br.com.aquiles.core.validator.MessageProducer;
import br.com.aquiles.core.validator.SimpleMessageProducer;
import br.com.aquiles.core.validator.ValidationMessage;

import java.util.ArrayList;
import java.util.List;


/**
 * <p>
 * Esta classe serve como base para validadores utilizados no 
 * Core. Ela é responsavel por extrair {@linkplain ValidationMessage} de um
 * {@link MessageProducer}.
 * </p>
 * <p>
 * O {@link MessageProducer} pode ser passado pelo construtor. Assim, através
 * dos métodos {@link #generateValidationMessages(List)} e
 * {@link #assertValid(List)} as mensagens de validacao são geradas.
 * </p>
 * 
 */
public class BaseValidator {
    private final MessageProducer messageProducer;

    /**
     * Utiliza um {@linkplain SimpleMessageProducer}.
     */
    public BaseValidator() {
        this.messageProducer = new SimpleMessageProducer();
    }

    /**
     * @param messageProducer
     *            produtor das mensagens de validacao.
     */
    public BaseValidator(MessageProducer messageProducer) {
        this.messageProducer = messageProducer;
    }

    /**
     * @param invalidValues
     *            lista de valores que descrevem erros de validacao.
     * @return lista de mensagens invalidas obtida pelo produto de mensagem.
     */
    public List<ValidationMessage> generateValidationMessages(List<InvalidValue> invalidValues) {
        List<ValidationMessage> messages = new ArrayList<ValidationMessage>();
        for (InvalidValue invalidValue : invalidValues) {
            ValidationMessage message = messageProducer.getMessage(invalidValue);
            messages.add(message);
        }
        return messages;
    }

    /**
     * @param invalidValues
     *            lista de valores que descrevem erros de validacao.
     * @throws InvalidStateException
     *             caso a lista não esteja vazia.
     */
    public void assertValid(List<InvalidValue> invalidValues) {
        if (!invalidValues.isEmpty()) {
            throw new InvalidStateException(generateValidationMessages(invalidValues));
        }
    }

}