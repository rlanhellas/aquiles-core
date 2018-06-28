package br.com.aquiles.core.validator.validation.ie;

import br.com.aquiles.core.validator.DigitoGerador;
import br.com.aquiles.core.validator.DigitoPara;
import br.com.aquiles.core.validator.MessageProducer;
import br.com.aquiles.core.validator.SimpleMessageProducer;
import br.com.aquiles.core.validator.validation.Validator;

import javax.swing.text.MaskFormatter;
import java.text.ParseException;
import java.util.regex.Pattern;

public class IEMinasGeraisValidator extends AbstractIEValidator {

    public static final Pattern FORMATED = Pattern.compile("(((\\d{3})\\.){2}\\d{3}/\\d{4})|(\\d{9}\\.\\d{2}-\\d{2})");

    public static final Pattern UNFORMATED = Pattern.compile("(\\d{13})");

	
    /**
	 * Este considera, por padrão, que as cadeias estão formatadas e utiliza um
	 * {@linkplain SimpleMessageProducer} para geracao de mensagens.
	 */
	public IEMinasGeraisValidator() {
		super(true);
	}

	/**
	 * O validador utiliza um {@linkplain SimpleMessageProducer} para geracao de
	 * mensagens.
	 * 
	 * @param isFormatted
	 *            considerar cadeia formatada quando <code>true</code>
	 */
	public IEMinasGeraisValidator(boolean isFormatted) {
		super(isFormatted);
	}

	public IEMinasGeraisValidator(MessageProducer messageProducer, boolean isFormatted) {
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
		String ieComZero = iESemDigito.substring(0, 3) + "0" + iESemDigito.substring(3);
		String digito1 = new DigitoPara(ieComZero).complementarAoModulo().comMultiplicadores(2,1).somandoIndividualmente()
									.mod(10).trocandoPorSeEncontrar("0", 10).calcula();

		String digito2 = new DigitoPara(iESemDigito + digito1).complementarAoModulo().comMultiplicadoresDeAte(2, 11)
									.trocandoPorSeEncontrar("0", 10, 11).calcula();

		return digito1 + digito2;
	}

	private String formata(String valor) {
		try {
			final MaskFormatter formatador = new MaskFormatter("###.###.###/####");
			formatador.setValidCharacters("1234567890");
			formatador.setValueContainsLiteralCharacters(false);
			return formatador.valueToString(valor);
		} catch (ParseException e) {
			throw new RuntimeException("Valor gerado não bate com o padrão: " + valor, e);
		}
	}

	/**
	 * @see Validator#generateRandomValid()
	 * @return uma inscricao estadual valida com 13 dIgitos; se o validador estiver configurado para
	 *         validar um número formatado, devolve o número gerado no formato
	 *         <code>###.###.###/####</code>
	 */
	@Override
	public String generateRandomValid() {
		final String ieSemDigitos = new DigitoGerador().generate(11);
		final String ieComDigitos = ieSemDigitos + calculaDigitos(ieSemDigitos);
		if (isFormatted) {
			return formata(ieComDigitos);
		}
		return ieComDigitos;
	}
}
