package br.com.aquiles.core.validator;

/**
 * Implementacao basica e imutavel. Apenas guarda a mensagem.
 */
public class SimpleValidationMessage implements ValidationMessage {
    private final String message;

    public SimpleValidationMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
    
    @Override
    public String toString() {
    	return this.getMessage();
    }
}
