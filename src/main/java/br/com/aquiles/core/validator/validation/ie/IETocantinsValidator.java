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
 * <a href="http://www.sintegra.gov.br/Cad_Estados/cad_TO.html">SINTEGRA - ROTEIRO
 * DE CRITICA DA INSCRIcao ESTADUAL </a> <br>
 * <a href="http://www2.sefaz.to.gov.br/Servicos/Sintegra/calinse.htm"> ESTADO DO
 * TOCANTINS SECRETARIA DA FAZENDA ASSESSORIA DE MODERNIZAcao E INFORMAcao</a>
 * 
 */
public class IETocantinsValidator extends AbstractIEValidator {

    public static final Pattern FORMATED = Pattern.compile("\\d{2}(.\\d{2}.)?(\\.)?\\d{3}\\.\\d{3}\\-\\d{1}");

    public static final Pattern UNFORMATED = Pattern.compile("(\\d{9}|\\d{11})");


    /**
	 * Este considera, por padrão, que as cadeias estão formatadas e utiliza um
	 * {@linkplain SimpleMessageProducer} para geracao de mensagens.
	 */
	public IETocantinsValidator() {
		super(true);
	}

	/**
	 * O validador utiliza um {@linkplain SimpleMessageProducer} para geracao de
	 * mensagens.
	 * 
	 * @param isFormatted
	 *            considerar cadeia formatada quando <code>true</code>
	 */
	public IETocantinsValidator(boolean isFormatted) {
		super(isFormatted);
	}

	public IETocantinsValidator(MessageProducer messageProducer, boolean isFormatted) {
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
		
		if(iESemDigito.length() == 10){
			iESemDigito = removeCaracteresIgnorados(iESemDigito);
		}
		
		String digitoCalculado = calculaDigito(iESemDigito);
		return digito.equals(digitoCalculado);
	}

	private String removeCaracteresIgnorados(String iESemDigito) {
		return iESemDigito.substring(0,2) + iESemDigito.substring(4);
	}

	private String calculaDigito(String iESemDigito) {
		return new DigitoPara(iESemDigito).complementarAoModulo().trocandoPorSeEncontrar("0", 10, 11).calcula();
	}

	private String formata(String valor) {
		try {
			final MaskFormatter formatador = new MaskFormatter("##.###.###-#");
			formatador.setValidCharacters("1234567890");
			formatador.setValueContainsLiteralCharacters(false);
			return formatador.valueToString(valor);
		} catch (ParseException e) {
			throw new RuntimeException("Valor gerado não bate com o padrão: " + valor, e);
		}
	}

	/**
	 * @see Validator#generateRandomValid()
	 * @return uma inscricao estadual valida com 9 dIgitos
	 */
	@Override
	public String generateRandomValid() {
		final String ieSemDigito = new DigitoGerador().generate(8);
		final String ieComDigito = ieSemDigito + calculaDigito(ieSemDigito);
		if (isFormatted) {
			return formata(ieComDigito);
		}
		return ieComDigito;
	}
}
