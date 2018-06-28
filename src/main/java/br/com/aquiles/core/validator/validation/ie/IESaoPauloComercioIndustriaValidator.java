package br.com.aquiles.core.validator.validation.ie;

import br.com.aquiles.core.validator.DigitoGerador;
import br.com.aquiles.core.validator.DigitoPara;
import br.com.aquiles.core.validator.MessageProducer;
import br.com.aquiles.core.validator.SimpleMessageProducer;

import javax.swing.text.MaskFormatter;
import java.text.ParseException;
import java.util.regex.Pattern;

class IESaoPauloComercioIndustriaValidator extends AbstractIEValidator {

    public static final Pattern FORMATED = Pattern.compile("(\\d{3}\\.){3}\\d{3}");

    public static final Pattern UNFORMATED = Pattern.compile("\\d{12}");

    /**
	 * Este considera, por padrão, que as cadeias estão formatadas e utiliza um
	 * {@linkplain SimpleMessageProducer} para geracao de mensagens.
	 */
	public IESaoPauloComercioIndustriaValidator() {
		super(true);
	}

	/**
	 * O validador utiliza um {@linkplain SimpleMessageProducer} para geracao de
	 * mensagens.
	 * 
	 * @param isFormatted
	 *            considerar cadeia formatada quando <code>true</code>
	 */
	public IESaoPauloComercioIndustriaValidator(boolean isFormatted) {
		super(isFormatted);
	}

	public IESaoPauloComercioIndustriaValidator(MessageProducer messageProducer, boolean isFormatted) {
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
		int length = unformattedIE.length();

		String iESemDigitoParte1 = unformattedIE.substring(0, length - 4);
		String iESemDigitoParte2 = unformattedIE.substring(length -3, length - 1);

		String digitos = unformattedIE.substring(length - 4, length -3) + unformattedIE.substring(length -1);
		String digitosCalculados = calculaDigitos(iESemDigitoParte1, iESemDigitoParte2);

		return digitos.equals(digitosCalculados);
	}

	private String calculaDigitos(String iEParte1, String iEParte2) {
		String digito1 = new DigitoPara(iEParte1).comMultiplicadores(10,8,7,6,5,4,3,1)
					.trocandoPorSeEncontrar("0", 10).trocandoPorSeEncontrar("1", 11).calcula();

		String digito2 = new DigitoPara(iEParte1 + digito1 + iEParte2).comMultiplicadoresDeAte(2, 10)
					.trocandoPorSeEncontrar("0", 10).trocandoPorSeEncontrar("1", 11).calcula();

		return digito1 + digito2;
	}

	private String formata(String valor) {
		try {
			final MaskFormatter formatador = new MaskFormatter("###.###.###.###");
			formatador.setValidCharacters("1234567890");
			formatador.setValueContainsLiteralCharacters(false);
			return formatador.valueToString(valor);
		} catch (ParseException e) {
			throw new RuntimeException("Valor gerado não bate com o padrão: " + valor, e);
		}
	}

	@Override
	public String generateRandomValid() {
		final DigitoGerador digitoGenerator = new DigitoGerador();
		final String ieSemDigitosParte1 = digitoGenerator.generate(8);
		final String ieSemDigitosParte2 = digitoGenerator.generate(2);
		final String digitosCalculados = calculaDigitos(ieSemDigitosParte1, ieSemDigitosParte2);
		final String ieComDigitos = ieSemDigitosParte1 + digitosCalculados.charAt(0)
		        + ieSemDigitosParte2 + digitosCalculados.charAt(1);
		if (isFormatted) {
			return formata(ieComDigitos);
		}
		return ieComDigitos;
	}
}
