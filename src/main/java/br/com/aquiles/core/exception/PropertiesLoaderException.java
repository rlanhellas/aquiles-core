package br.com.aquiles.core.exception;

/**
 * Created by Diorge on 04/10/2016.
 */
public class PropertiesLoaderException extends Exception{
    public PropertiesLoaderException() {

    }

    public PropertiesLoaderException(Throwable t) {
        super(t);
    }

    public PropertiesLoaderException(String msg) {
        super(msg);
    }
}
