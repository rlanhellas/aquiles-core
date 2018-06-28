package br.com.aquiles.security.exception;

import br.com.aquiles.core.exception.CoreException;

public class LoginException extends CoreException{
    public LoginException(String e){
        super(e);
    }
    public LoginException(Throwable e){
        super(e);
    }
}
