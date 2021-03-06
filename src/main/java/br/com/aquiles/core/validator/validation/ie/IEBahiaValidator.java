package br.com.aquiles.core.validator.validation.ie;

import br.com.aquiles.core.validator.DigitoGerador;
import br.com.aquiles.core.validator.DigitoPara;
import br.com.aquiles.core.validator.MessageProducer;
import br.com.aquiles.core.validator.SimpleMessageProducer;
import br.com.aquiles.core.validator.validation.Validator;

import javax.swing.text.MaskFormatter;
import java.text.ParseException;
import java.util.regex.Pattern;

/**
 * <p>
 * Documentacao de referência:
 * </p>
 * <a href="http://www.pfe.fazenda.sp.gov.br/consist_ie.shtm">Secretaria da
 * Fazenda do Estado de São Paulo</a> <a
 * href="http://www.sintegra.gov.br/Cad_Estados/cad_BA.html">SINTEGRA - ROTEIRO
 * DE CRITICA DA INSCRIcao ESTADUAL </a>
 * 
 */
public class IEBahiaValidator extends AbstractIEValidator {

	/*
	 * 612345-57
	 * 
	 * 123456-63
	 */
	public static final Pattern FORMATED = Pattern.compile("(\\d{6,7}-\\d{2})|(\\d{2,3}\\.\\d{3}\\.\\d{3})");

	public static final Pattern UNFORMATED = Pattern.compile("\\d{8,9}");

	/**
	 * Este considera, por padrão, que as cadeias estão formatadas e utiliza um
	 * {@linkplain SimpleMessageProducer} para geracao de mensagens.
	 */
	public IEBahiaValidator() {
		super(true);
	}

	/**
	 * O validador utiliza um {@linkplain SimpleMessageProducer} para geracao de
	 * mensagens.
	 * 
	 * @param isFormatted
	 *            considerar cadeia formatada quando <code>true</code>
	 */
	public IEBahiaValidator(boolean isFormatted) {
		super(isFormatted);
	}

	public IEBahiaValidator(MessageProducer messageProducer, boolean isFormatted) {
		super(messageProducer, isFormatted);
	}

	@Override
	protected Pattern getUnformattedPattern() {
		return UNFORMATED;
	}

	@Override
	protected Pattern getFormattedPattern() {
		return FORMATED;
	}

	protected boolean hasValidCheckDigits(String unformattedIE) {
		String iESemDigito = unformattedIE.substring(0, unformattedIE.length() - 2);
		String digitos = unformattedIE.substring(unformattedIE.length() - 2);

		String digitosCalculados = calculaDigito(iESemDigito);

		return digitos.equals(digitosCalculados);
	}

	private String calculaDigito(String iESemDigito) {
		DigitoPara digitoPara = new DigitoPara(iESemDigito);
		digitoPara.complementarAoModulo().trocandoPorSeEncontrar("0", 10, 11);

		char charToCheck;
		if (iESemDigito.length() == 6) {
			charToCheck = iESemDigito.charAt(0);
		} else {
			charToCheck = iESemDigito.charAt(1);
		}

		switch (charToCheck) {
		case '6':
		case '7':
		case '9':
			digitoPara.mod(11);
			break;
		default:
			digitoPara.mod(10);
		}

		String digito2 = digitoPara.calcula();
		digitoPara.addDigito(digito2);
		String digito1 = digitoPara.calcula();

		return digito1 + digito2;
	}

	private String formata(String valor) {
		try {
			final MaskFormatter formatador = new MaskFormatter("######-##");
			formatador.setValidCharacters("1234567890");
			formatador.setValueContainsLiteralCharacters(false);
			return formatador.valueToString(valor);
		} catch (ParseException e) {
			throw new RuntimeException("Valor gerado não bate com o padrão: " + valor, e);
		}
	}

	/**
	 * Gera uma inscricao estadual com 8 dIgitos
	 * 
	 * @see Validator#generateRandomValid()
	 * @return uma inscricao estadual aleatoria valida
	 */
	@Override
	public String generateRandomValid() {
		final String ieSemDigitos = new DigitoGerador().generate(6);
		final String ieComDigitos = ieSemDigitos + calculaDigito(ieSemDigitos);
		if (isFormatted) {
			return formata(ieComDigitos);
		}
		return ieComDigitos;
	}
}
