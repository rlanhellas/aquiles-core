package br.com.aquiles.core.validator.inword;

/**
 * Representa o formato para números em dolares com até duas casas decimais.
 */
public class FormatoDeDolar implements FormatoDeExtenso {
	@Override
    public String getUnidadeInteiraNoSingular() {
        return "dollar";
    }

	@Override
    public String getUnidadeInteiraNoPlural() {
        return "dollars";
    }

	@Override
    public String getUnidadeDecimalNoSingular() {
        return "cent";
    }

	@Override
	public String getUnidadeDecimalNoPlural() {
        return "cents";
    }

	@Override
    public int getCasasDecimais() {
        return 2;
    }
}
