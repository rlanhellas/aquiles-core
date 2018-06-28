package br.com.aquiles.security.exception;

import br.com.aquiles.core.exception.CoreException;

public class TokenException extends CoreException {
    public TokenException(String e){
        super(e);
    }
    public TokenException(Throwable e){
        super(e);
    }
}
