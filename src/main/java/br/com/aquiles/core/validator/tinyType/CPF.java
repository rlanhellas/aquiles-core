package br.com.aquiles.core.validator.tinyType;

import br.com.aquiles.core.validator.format.CPFFormatter;
import br.com.aquiles.core.validator.validation.CPFValidator;

/**
 * Classe que representa um cadastro de pessoa fIsica - CPF.
*/
public final class CPF {

    private final String numero;

    private final String numeroFormatado;

    public CPF(String numero) {
    	CPFFormatter formatador = new CPFFormatter();
		if (formatador.isFormatted(numero)) {
			this.numero = formatador.unformat(numero);
			this.numeroFormatado = numero;
		} else if (formatador.canBeFormatted(numero)) {
			this.numero = numero;
			this.numeroFormatado = formatador.format(numero);
		} else {
			this.numero = this.numeroFormatado = numero;
		}
    }

    public String getNumero() {
        return numero;
    }

    public String getNumeroFormatado() {
    	return numeroFormatado;
    }

    public boolean isValido() {
		return new CPFValidator().invalidMessagesFor(numero).isEmpty();
	}

    @Override
    public String toString() {
        return getNumeroFormatado();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((numero == null) ? 0 : numero.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
			return true;
		}
        if (obj == null) {
			return false;
		}
        if (getClass() != obj.getClass()) {
			return false;
		}
        final br.com.aquiles.core.validator.tinyType.CPF other = (br.com.aquiles.core.validator.tinyType.CPF) obj;
        if (numero == null) {
            if (other.numero != null) {
				return false;
			}
        } else if (!numero.equals(other.numero)) {
			return false;
		}
        return true;
    }
}
