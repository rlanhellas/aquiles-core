package br.com.aquiles.core.validator.validation.ie;

import br.com.aquiles.core.validator.DigitoGerador;
import br.com.aquiles.core.validator.DigitoPara;
import br.com.aquiles.core.validator.MessageProducer;
import br.com.aquiles.core.validator.SimpleMessageProducer;

import javax.swing.text.MaskFormatter;
import java.text.ParseException;
import java.util.regex.Pattern;

public class IEParanaValidator extends AbstractIEValidator {

    public static final Pattern FORMATED = Pattern.compile("\\d{3}\\.?\\d{5}\\-\\d{2}");

    public static final Pattern UNFORMATED = Pattern.compile("(\\d{10})");

	
    /**
	 * Este considera, por padrão, que as cadeias estão formatadas e utiliza um
	 * {@linkplain SimpleMessageProducer} para geracao de mensagens.
	 */
	public IEParanaValidator() {
		super(true);
	}

	/**
	 * O validador utiliza um {@linkplain SimpleMessageProducer} para geracao de
	 * mensagens.
	 * 
	 * @param isFormatted
	 *            considerar cadeia formatada quando <code>true</code>
	 */
	public IEParanaValidator(boolean isFormatted) {
		super(isFormatted);
	}

	public IEParanaValidator(MessageProducer messageProducer, boolean isFormatted) {
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

		String digitosCalculados = calculaDigitos(iESemDigito);

		return digitos.equals(digitosCalculados);
    }

	private String calculaDigitos(String iESemDigito) {
		DigitoPara digitoPara = new DigitoPara(iESemDigito);
		digitoPara.comMultiplicadoresDeAte(2, 7).complementarAoModulo().trocandoPorSeEncontrar("0", 10, 11);

		String digito1 = digitoPara.calcula();
		digitoPara.addDigito(digito1);
		String digito2 = digitoPara.calcula();

		return digito1 + digito2;
	}

	private String formata(String valor) {
		try {
			final MaskFormatter formatador = new MaskFormatter("###.#####-##");
			formatador.setValidCharacters("1234567890");
			formatador.setValueContainsLiteralCharacters(false);
			return formatador.valueToString(valor);
		} catch (ParseException e) {
			throw new RuntimeException("Valor gerado não bate com o padrão: " + valor, e);
		}
	}

	@Override
	public String generateRandomValid() {
		final String ieSemDigitos = new DigitoGerador().generate(8);
		final String ieComDigitos = ieSemDigitos + calculaDigitos(ieSemDigitos);
		if (isFormatted) {
			return formata(ieComDigitos);
		}
		return ieComDigitos;
	}
}
