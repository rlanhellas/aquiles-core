package br.com.aquiles.core.validator.validation.error;

import br.com.aquiles.core.validator.validation.InvalidValue;

/**
 * Representa possIveis erros na validacao do tamanho de dIgitos.
 * 
 */
public class LengthError implements InvalidValue {

    private final int validLength;

    /**
     * @param validLength
     *            tamanho esperado para considerar valida a cadeia.
     */
    public LengthError(int validLength) {
        this.validLength = validLength;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + validLength;
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
        br.com.aquiles.core.validator.validation.error.LengthError other = (br.com.aquiles.core.validator.validation.error.LengthError) obj;
        return validLength == other.validLength;
    }

    /**
     * @return tamanho esperado para considerar valida a cadeia.
     */
    public int getValidLength() {
        return validLength;
    }

    public String name() {
        return "INVALID_LENGTH";
    }


}
