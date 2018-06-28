package br.com.aquiles.core.exception;

public class ServiceException extends CoreException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public ServiceException(Throwable cause) {
        super(cause);
    }

    public ServiceException(String message) {
        super(message);
    }

}
