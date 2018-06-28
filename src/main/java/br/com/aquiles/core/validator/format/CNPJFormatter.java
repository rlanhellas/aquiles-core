package br.com.aquiles.core.validator.format;

import br.com.aquiles.core.validator.validation.CNPJValidator;

/**
 * Classe responsavel pela formtacao de CNPJ
 */
public class CNPJFormatter implements Formatter {
    private final BaseFormatter base;

    public CNPJFormatter() {
        this.base = new BaseFormatter(CNPJValidator.FORMATED, "$1.$2.$3/$4-$5", CNPJValidator.UNFORMATED, "$1$2$3$4$5");
    }

    @Override
	public String format(String value) {
        return base.format(value);
    }

    @Override
	public String unformat(String value) {
        return base.unformat(value);
    }

	@Override
	public boolean isFormatted(String value) {
		return base.isFormatted(value);
	}

	@Override
	public boolean canBeFormatted(String value) {
		return base.canBeFormatted(value);
	}

}
