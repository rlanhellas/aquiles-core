package br.com.aquiles.core.validator.inword;

/**
 * Representa o formato para um número inteiro por extenso, sem tipo de unidade.
 */
public class InteiroSemFormato implements FormatoDeExtenso {

    public String getUnidadeDecimalNoPlural() {
        return "";
    }

    public String getUnidadeDecimalNoSingular() {
        return "";
    }

    public String getUnidadeInteiraNoSingular() {
        return "";
    }

    public String getUnidadeInteiraNoPlural() {
        return "";
    }

    public int getCasasDecimais() {
        return 1;
    }

}
