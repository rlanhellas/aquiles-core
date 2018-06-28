package br.com.aquiles.security.dto;

/**
 * Esta classe é responsável por parâmetros adicionais utilizados na
 * autenticação AD via protocolo LDAP.
 *
 * @author Igor Pereira Dutra, em 09/03/2015
 *
 */
public class LDAPParametrosConfig {

	/**
	 * Propriedade Ambiente. Esta classe de fábrica deve implementar a
	 * InitialContextFactory (na documentação de referência API) interface. a
	 * classe inicial fábrica contexto é com.sun.jndi.ldap.LdapCtxFactory.
	 */
	public static final String INITIAL_CONTEXT_FACTORY = "com.sun.jndi.ldap.LdapCtxFactory";

	/**
	 * Especifica o mecanismo de autenticação para usar. Para o prestador de
	 * serviços Sun LDAP, este pode ser um dos seguintes textos: "none",
	 * "simples", sasl_mech, onde sasl_mech é uma lista separada por espaços de
	 * SASL mecanismo nomes.
	 */
	public static final String SECURITY_AUTHENTICATION = "simple";

	/**
	 * NamingException: Excessão não tratada na aplicação.
	 */
	public static final String LDAP_NAMING_ERROR = "Ocorreu um erro desconhecido.";

	/**
	 * LDAP PROTOCOL ERROR (CommunicationException): Indica que o servidor
	 * recebeu um pedido inválido ou mal formado a partir do cliente.
	 */
	public static final String LDAP_PROTOCOL_ERROR = "LDAP PROTOCOL ERROR:\n Não foi possível estabelecer uma conexão com os servidores de ActiveDirectory";

	/**
	 * TimeLimitExceededException: Essa exceção é lançada quando um método não
	 * terminar dentro do prazo especificado. Isto pode acontecer, por exemplo,
	 * se o utilizador especifica que o método não deve demorar de 10 segundos,
	 * e o método não completar com 10 segundos.
	 */
	public static final String LDAP_TIME_LIMIT_EXCEEDED_ERROR = "LDAP TIME LIMIT EXCEEDE:\n Não foi possível conectar ao servidor LDAP dentro do tempo estimado.";

	/**
	 * ServiceUnavailableException: Indica que o servidor LDAP não pode
	 * processar solicitação de ligação do cliente, geralmente porque ele está
	 * sendo desligado.
	 */
	public static final String LDAP_UNAVAILABLE_ERROR = "LDAP SERVER UNAVAILABLE:\n Servidor LDAP indisponivel.";

	/**
	 * Código 52e: Indica um Active Directory (AD) AcceptSecurityContext erro,
	 * que é devolvido quando o nome de usuário é válido, mas a combinação de
	 * senha e usuário credencial é inválido. Isso é o equivalente AD de código
	 * de erro LDAP 49.
	 */
	public static final String LDAP_AUTHENTICATION_ERROR_52e = "LDAP AUTHENTICATION ERROR 52e:\n A senha informada está incorreta.";

	/**
	 * Código 525: Indica um Active Directory (AD) erro de dados
	 * AcceptSecurityContext que é devolvido quando o nome de usuário é
	 * inválido.
	 */
	public static final String LDAP_AUTHENTICATION_ERROR_525 = "LDAP AUTHENTICATION ERROR 525:\n O código do usuário informado é inválido.";

	/**
	 * Código 530: Indica um Active Directory (AD) erro de dados
	 * AcceptSecurityContext que é falha de logon causado porque o usuário não
	 * tem permissão para fazer logon neste momento. Retorna somente quando
	 * apresentado com um nome de usuário válido e credencial de senha válida.
	 */
	public static final String LDAP_AUTHENTICATION_ERROR_530 = "LDAP AUTHENTICATION ERROR 530:\n O usuário não possui permissão para efetuar Logon neste momento.";

	/**
	 * Código 531: Indica um Active Directory (AD) erro de dados
	 * AcceptSecurityContext que é falha de logon causado porque o usuário não
	 * tem permissão para fazer logon a partir deste computador. Retorna somente
	 * quando apresentado com um nome de usuário válido e credencial de senha
	 * válida.
	 */
	public static final String LDAP_AUTHENTICATION_ERROR_531 = "LDAP AUTHENTICATION ERROR 531:\n O usuário não possui permissão para efetuar Logon a partir deste computador.";

	/**
	 * Código 532: Indica um Active Directory (AD) erro de dados
	 * AcceptSecurityContext que é uma falha de logon. A senha da conta
	 * especificada expirou. Retorna apenas quando se apresenta com nome de
	 * usuário e senha válidos credencial.
	 *
	 */
	public static final String LDAP_AUTHENTICATION_ERROR_532 = "LDAP AUTHENTICATION ERROR 532:\n Falha ao efetuar o Logon, senha do usuário expirou.";

	/**
	 * Código 533: Indica um Active Directory (AD) erro de dados
	 * AcceptSecurityContext que é uma falha de logon. A conta está atualmente
	 * desativado. Retorna apenas quando se apresenta com nome de usuário e
	 * senha válidos credencial.
	 *
	 */
	public static final String LDAP_AUTHENTICATION_ERROR_533 = "LDAP AUTHENTICATION ERROR 533:\n A conta do usuário está desativada.";

	/**
	 * Código 568: Indica que durante uma tentativa de log-on, o contexto de
	 * segurança do usuário acumulou muitas identificações de segurança. Este é
	 * um problema com o usuário LDAP objeto / conta específica que deve ser
	 * investigado pelo administrador LDAP.
	 */
	public static final String LDAP_AUTHENTICATION_ERROR_568 = "LDAP AUTHENTICATION ERROR 568:\n O contexto de segurança do usuário acumulou muitas identificações de segurança.";

	/**
	 * Código 701: Indica um Active Directory (AD) erro de dados
	 * AcceptSecurityContext que é uma falha de logon. A conta do usuário
	 * expirou. Retorna apenas quando se apresenta com nome de usuário e senha
	 * válidos credencial.
	 */
	public static final String LDAP_AUTHENTICATION_ERROR_701 = "LDAP AUTHENTICATION ERROR 701:\n Conta do usuário expirada.";

	/**
	 * Código 773: Indica uma Directory (AD) erro de dados AcceptSecurityContext
	 * Ativa. A senha do usuário deve ser alterada antes de iniciar sessão pela
	 * primeira vez. Retorna apenas quando se apresenta com nome de usuário e
	 * senha válidos credencial.
	 */
	public static final String LDAP_AUTHENTICATION_ERROR_773 = "LDAP AUTHENTICATION ERROR 773:\n A senha do usuário deve ser alterada antes de iniciar a sessão pela primeira vez.";

	/**
	 * Código 775: Indica uma Directory (AD) erro de dados AcceptSecurityContext
	 * Ativa. O usuário se encontra com acesso bloqueado, e deverá ser
	 * solicitado ao administrador do LDAP o desbloqueio do mesmo.
	 */
	public static final String LDAP_AUTHENTICATION_ERROR_775 = "LDAP AUTHENTICATION ERROR 775:\n O usuário informado está com acesso bloqueado. Favor solicitar o desbloqueio.";

}
