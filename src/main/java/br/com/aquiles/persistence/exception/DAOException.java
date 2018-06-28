package br.com.aquiles.persistence.exception;

import br.com.aquiles.core.exception.CoreException;

public class DAOException extends CoreException {

	public DAOException(Throwable cause) {
		super(cause);
	}

	public DAOException(String string) {
		super(string);
	}

	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

}
