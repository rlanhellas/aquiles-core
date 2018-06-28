package br.com.aquiles.core.producer;

import org.apache.log4j.Logger;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Inject;
import java.io.Serializable;


/**
 * Classe responsavel por informar ao container como ele deve instanciar o
 * {@link Logger}. Esta tecnica simplifica o processo de instancia do
 * {@link Logger}.
 * <p>
 * Exemplo:
 *
 * <pre>
 * &#064;{@link Inject}
 * private {@link Logger} logger;
 * </pre>
 *
 * Sem utilizar esta tecnica seria necessario informar a classe utilizada para
 * log:
 *
 * <pre>
 * public class MinhaClasse{
 *     private static final {@link Logger} LOGGER = Logger.getLogger(MinhaClasse.class.getName());
 * }
 * </pre>
 *
 * @author Renan Celso, em 09/06/2015 *
 * @since 1.0.0
 *
 */
@Deprecated
public class LogProducer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Informa ao container que todas as injecoes de dependencia da classe
	 * {@link Logger} devem ser instanciadas a partir do metodo
	 * Logger.getLogger() passando como parametro o nome da classe na qual foi
	 * realizada a injecao do {@link Logger}.
	 *
	 * @param ip
	 *            Ponto de injecao (parametro passado pelo container atraves do
	 *            CDI)
	 * @return Nova instancia de @{link Logger} para a classe na qual a variavel
	 *         foi instanciada
	 */
	@Produces
	public Logger createLogger(InjectionPoint ip) {
		return Logger.getLogger(ip.getMember().getDeclaringClass().getName());
	}
}