package br.com.aquiles.core.exception;

public class CEPInvalidoException extends ServiceException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public CEPInvalidoException(Throwable cause) {
        super(cause);
    }

    public CEPInvalidoException(String message) {
        super(message);
    }

}
