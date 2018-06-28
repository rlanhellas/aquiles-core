package br.com.aquiles.core.validator.inword;

/**
 * Representa o formato para números em dolares com até duas casas decimais.
 */
public class FormatoDeReal implements FormatoDeExtenso {

    public String getUnidadeDecimalNoPlural() {
        return "centavos";
    }

    public String getUnidadeDecimalNoSingular() {
        return "centavo";
    }

    public String getUnidadeInteiraNoSingular() {
        return "real";
    }

    public String getUnidadeInteiraNoPlural() {
        return "reais";
    }

    public int getCasasDecimais() {
        return 2;
    }

}
