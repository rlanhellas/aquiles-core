package br.com.aquiles.core.validation.logic;

import br.com.aquiles.core.validator.MessageProducer;
import br.com.aquiles.core.validator.SimpleValidationMessage;
import br.com.aquiles.core.validator.ValidationMessage;
import br.com.aquiles.core.validator.validation.InvalidValue;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Recupera mensagens de validacao definida na anotacao do Hibernate
 * Validator.
 */
public class AnnotationMessageProducer implements MessageProducer {

    private final Annotation constraint;

    public AnnotationMessageProducer(final Annotation constraint) {
        this.constraint = constraint;
    }

    /**
     * This method will always return the same ValidationMessage, as Hibernate
     * Validator only let one message per Validator, defined inside the
     * constraint annotation.
     * 
     * @param invalidValue
     *            will be ignored
     * @return the message defined by the related constraint annotation
     */
    public ValidationMessage getMessage(final InvalidValue invalidValue) {
        try {
            Method constraintMessage = constraint.annotationType().getDeclaredMethod("message");
            constraintMessage.setAccessible(true);
            String message = constraintMessage.invoke(constraint).toString();
            return new SimpleValidationMessage(message);
        } catch (NoSuchMethodException e) {
            throw new IllegalArgumentException("Annotation " + constraint
                    + " não tem um (acessIvel) atributo de mensagem");
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
