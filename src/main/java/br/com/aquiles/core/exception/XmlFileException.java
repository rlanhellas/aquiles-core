package br.com.aquiles.core.exception;

/**
 * Created by Diorge on 04/10/2016.
 */
public class XmlFileException extends Exception{
    public XmlFileException() {

    }

    public XmlFileException(Throwable t) {
        super(t);
    }

    public XmlFileException(String msg) {
        super(msg);
    }
}
