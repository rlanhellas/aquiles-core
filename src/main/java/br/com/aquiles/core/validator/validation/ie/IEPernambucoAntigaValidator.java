package br.com.aquiles.core.validator.validation.ie;

import br.com.aquiles.core.validator.DigitoGerador;
import br.com.aquiles.core.validator.DigitoPara;
import br.com.aquiles.core.validator.MessageProducer;
import br.com.aquiles.core.validator.SimpleMessageProducer;

import javax.swing.text.MaskFormatter;
import java.text.ParseException;
import java.util.regex.Pattern;

class IEPernambucoAntigaValidator extends AbstractIEValidator {

    public static final Pattern FORMATED = Pattern.compile("([1][8])\\.?([1-9])\\.?(\\d{3})\\.?(\\d{7})\\-?(\\d{1})");

    public static final Pattern UNFORMATED = Pattern.compile("([1][8])([1-9])(\\d{3})(\\d{7})(\\d{1})");
	
    /**
	 * Este considera, por padrão, que as cadeias estão formatadas e utiliza um
	 * {@linkplain SimpleMessageProducer} para geracao de mensagens.
	 */
	public IEPernambucoAntigaValidator() {
		super(true);
	}

	/**
	 * O validador utiliza um {@linkplain SimpleMessageProducer} para geracao de
	 * mensagens.
	 * 
	 * @param isFormatted
	 *            considerar cadeia formatada quando <code>true</code>
	 */
	public IEPernambucoAntigaValidator(boolean isFormatted) {
		super(isFormatted);
	}

	public IEPernambucoAntigaValidator(MessageProducer messageProducer, boolean isFormatted) {
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
		String iESemDigito = unformattedIE.substring(0, unformattedIE.length() - 1);
		String digito = unformattedIE.substring(unformattedIE.length() - 1);
		String digitoCalculado = calculaDigito(iESemDigito);

		return digito.equals(digitoCalculado);
	}

	private String calculaDigito(String iESemDigito) {
		return new DigitoPara(iESemDigito + "0").comMultiplicadoresDeAte(1, 9).complementarAoModulo()
						.trocandoPorSeEncontrar("0", 10).trocandoPorSeEncontrar("1", 11).calcula();
	}

	private String formata(String valor) {
		try {
			final MaskFormatter formatador = new MaskFormatter("##.#.###.#######-#");
			formatador.setValidCharacters("1234567890");
			formatador.setValueContainsLiteralCharacters(false);
			return formatador.valueToString(valor);
		} catch (ParseException e) {
			throw new RuntimeException("Valor gerado não bate com o padrão: " + valor, e);
		}
	}

	@Override
	public String generateRandomValid() {
		final String ieSemDigito = "18" + new DigitoGerador().generate(11);
		final String ieComDigito = ieSemDigito + calculaDigito(ieSemDigito);
		if (isFormatted) {
			return formata(ieComDigito);
		}
		return ieComDigito;
	}
}
