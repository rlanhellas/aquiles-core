package br.com.aquiles.security.exception;

public class UsuarioInexistenteException extends Exception {
    public UsuarioInexistenteException(String e){
        super(e);
    }
    public UsuarioInexistenteException(Throwable e){
        super(e);
    }
}
