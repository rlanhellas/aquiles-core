package br.com.aquiles.core.validator.inword;

/**
 * Representa o formato de para números inteiros com precisão de miléssimos.
 */
public class FormatoDeInteiro implements FormatoDeExtenso {

    public String getUnidadeDecimalNoPlural() {
        return "milésimos";
    }

    public String getUnidadeDecimalNoSingular() {
        return "milésimo";
    }

    public String getUnidadeInteiraNoSingular() {
        return "inteiro";
    }

    public String getUnidadeInteiraNoPlural() {
        return "inteiros";
    }

    public int getCasasDecimais() {
        return 3;
    }

}
