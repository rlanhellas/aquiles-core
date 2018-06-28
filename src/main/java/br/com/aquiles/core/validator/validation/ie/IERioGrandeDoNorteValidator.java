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
 * <p> Documentacao de referência: </p>
 * <a href="http://www.sintegra.gov.br/Cad_Estados/cad_RN.html">
 * SINTEGRA - ROTEIRO DE CRITICA DA INSCRIcao ESTADUAL </a>
 * 
 */
public class IERioGrandeDoNorteValidator extends AbstractIEValidator {

    public static final Pattern FORMATED = Pattern.compile("20\\.(\\d\\.)?\\d{3}\\.\\d{3}\\-\\d{1}");

    public static final Pattern UNFORMATED = Pattern.compile("20\\d{7,8}");
	
    /**
	 * Este considera, por padrão, que as cadeias estão formatadas e utiliza um
	 * {@linkplain SimpleMessageProducer} para geracao de mensagens.
	 */
	public IERioGrandeDoNorteValidator() {
		super(true);
	}

	/**
	 * O validador utiliza um {@linkplain SimpleMessageProducer} para geracao de
	 * mensagens.
	 * 
	 * @param isFormatted
	 *            considerar cadeia formatada quando <code>true</code>
	 */
	public IERioGrandeDoNorteValidator(boolean isFormatted) {
		super(isFormatted);
	}

	public IERioGrandeDoNorteValidator(MessageProducer messageProducer, boolean isFormatted) {
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
		return new DigitoPara(iESemDigito).comMultiplicadoresDeAte(2, 10).complementarAoModulo().trocandoPorSeEncontrar("0", 10, 11).calcula();
	}

	private String formata(String valor) {
		try {
			final MaskFormatter formatador = new MaskFormatter("##.#.###.###-#");
			formatador.setValidCharacters("1234567890");
			formatador.setValueContainsLiteralCharacters(false);
			return formatador.valueToString(valor);
		} catch (ParseException e) {
			throw new RuntimeException("Valor gerado não bate com o padrão: " + valor, e);
		}
	}

	/**
	 * @see Validator#generateRandomValid()
	 * @return uma inscricao estadual valida com 10 dIgitos
	 */
	@Override
	public String generateRandomValid() {
		final String ieSemDigito = "20" + new DigitoGerador().generate(7);
		final String ieComDigito = ieSemDigito + calculaDigito(ieSemDigito);
		if (isFormatted) {
			return formata(ieComDigito);
		}
		return ieComDigito;
	}
}
