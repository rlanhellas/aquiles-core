package br.com.aquiles.core.validator;

import br.com.aquiles.core.annotation.validator.CPFCNPJValidator;
import br.com.aquiles.core.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.InputMismatchException;

/**
 * Created by rlanhellas on 23/05/2017.
 */
public class CPFCNPJValidatorImpl implements ConstraintValidator<CPFCNPJValidator, String> {

    @Override
    public void initialize(CPFCNPJValidator constraintAnnotation) {
        //não usado
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        String valueSemMascara = StringUtils.removerMascaras(value);

        //CAso não seja informado nenhum CPF/CNPJ então considera valido para evitar erros de tela
        if (valueSemMascara == null || valueSemMascara.trim().equalsIgnoreCase("")) {
            return true;
        }

        //Não é CPF nem CNPJ
        if (valueSemMascara.length() != 11 && valueSemMascara.length() != 14) {
            return false;
        } else if (valueSemMascara.length() == 11) {
            return validaCPF(valueSemMascara);
        } else {
            return validaCNPJ(valueSemMascara);
        }
    }

    private boolean validaCNPJ(String CNPJ) {
        // considera-se erro CNPJ's formados por uma sequencia de numeros iguais
        if (CNPJ.equals("00000000000000") || CNPJ.equals("11111111111111") ||
                CNPJ.equals("22222222222222") || CNPJ.equals("33333333333333") ||
                CNPJ.equals("44444444444444") || CNPJ.equals("55555555555555") ||
                CNPJ.equals("66666666666666") || CNPJ.equals("77777777777777") ||
                CNPJ.equals("88888888888888") || CNPJ.equals("99999999999999") ||
                (CNPJ.length() != 14))
            return (false);

        char dig13, dig14;
        int sm, i, r, num, peso;

        try {
            sm = 0;
            peso = 2;
            for (i = 11; i >= 0; i--) {
                num = CNPJ.charAt(i) - 48;
                sm = sm + (num * peso);
                peso = peso + 1;
                if (peso == 10)
                    peso = 2;
            }

            r = sm % 11;
            if ((r == 0) || (r == 1))
                dig13 = '0';
            else dig13 = (char) ((11 - r) + 48);

            sm = 0;
            peso = 2;
            for (i = 12; i >= 0; i--) {
                num = CNPJ.charAt(i) - 48;
                sm = sm + (num * peso);
                peso = peso + 1;
                if (peso == 10)
                    peso = 2;
            }

            r = sm % 11;
            if ((r == 0) || (r == 1))
                dig14 = '0';
            else dig14 = (char) ((11 - r) + 48);

            return (dig13 == CNPJ.charAt(12)) && (dig14 == CNPJ.charAt(13));
        } catch (InputMismatchException erro) {
            return (false);
        }
    }

    private boolean validaCPF(String strCpf) {
        if (strCpf.equals("00000000000") ||
                strCpf.equals("11111111111") || strCpf.equals("22222222222") || strCpf.equals("33333333333")
                || strCpf.equals("44444444444") || strCpf.equals("55555555555")
                || strCpf.equals("66666666666") || strCpf.equals("77777777777")
                || strCpf.equals("88888888888") || strCpf.equals("99999999999") || strCpf.length() != 11) {
            return false;
        }

        int d1, d2;
        int digito1, digito2, resto;
        int digitoCPF;
        String nDigResult;

        d1 = d2 = 0;
        digito1 = digito2 = resto = 0;

        for (int nCount = 1; nCount < strCpf.length() - 1; nCount++) {
            digitoCPF = Integer.valueOf(strCpf.substring(nCount - 1, nCount)).intValue();

            //multiplique a ultima casa por 2 a seguinte por 3 a seguinte por 4 e assim por diante.
            d1 = d1 + (11 - nCount) * digitoCPF;

            //para o segundo digito repita o procedimento incluindo o primeiro digito calculado no passo anterior.
            d2 = d2 + (12 - nCount) * digitoCPF;
        }

        //Primeiro resto da divisão por 11.
        resto = (d1 % 11);

        //Se o resultado for 0 ou 1 o digito é 0 caso contrário o digito é 11 menos o resultado anterior.
        if (resto < 2)
            digito1 = 0;
        else
            digito1 = 11 - resto;

        d2 += 2 * digito1;

        //Segundo resto da divisão por 11.
        resto = (d2 % 11);

        //Se o resultado for 0 ou 1 o digito é 0 caso contrário o digito é 11 menos o resultado anterior.
        if (resto < 2)
            digito2 = 0;
        else
            digito2 = 11 - resto;

        //Digito verificador do CPF que está sendo validado.
        String nDigVerific = strCpf.substring(strCpf.length() - 2, strCpf.length());

        //Concatenando o primeiro resto com o segundo.
        nDigResult = String.valueOf(digito1) + String.valueOf(digito2);

        //comparar o digito verificador do cpf com o primeiro resto + o segundo resto.
        return nDigVerific.equals(nDigResult);
    }
}
