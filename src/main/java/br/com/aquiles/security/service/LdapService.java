package br.com.aquiles.security.service;

import br.com.aquiles.core.exception.ServiceException;
import br.com.aquiles.core.service.GenericService;
import br.com.aquiles.persistence.annotation.Seguranca;
import br.com.aquiles.security.bean.Dominios;
import br.com.aquiles.security.dataquery.DominioDataQuery;
import br.com.aquiles.security.dto.LDAPParametrosConfig;
import br.com.aquiles.security.exception.LoginException;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.naming.*;
import javax.naming.directory.InitialDirContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

@Named(value = "ldapService")
@RequestScoped
public class LdapService extends GenericService {

    List<Dominios> dominiosAtivos;
    Hashtable env;

    @Transactional
    public List<Dominios> dominiosAtivos() {
        List<Dominios> dominios;
        try {
            dominios = (List<Dominios>) this.findList(DominioDataQuery.FINDATIVOS, null, Seguranca.class);
        } catch (ServiceException e) {
            dominios = new ArrayList<>();
        }
        return dominios;
    }

    @PostConstruct
    public void init() {
        dominiosAtivos = dominiosAtivos();
        env = new Hashtable();
    }

    public InitialDirContext proceedLdap(String usuario, String senha) throws LoginException {
        if (dominiosAtivos==null||dominiosAtivos.isEmpty()){
            throw new LoginException("Não existem domínios ativos para autenticação via LDAP");
        }
        dominios:
        for (Dominios dominio : dominiosAtivos) {
            int i = 1;
            try {
                env.put(Context.INITIAL_CONTEXT_FACTORY, LDAPParametrosConfig.INITIAL_CONTEXT_FACTORY);
                env.put(Context.SECURITY_AUTHENTICATION, LDAPParametrosConfig.SECURITY_AUTHENTICATION);
                env.put(Context.PROVIDER_URL, getLdapProvider(dominio.getDescricaoIp()));
                env.put(Context.SECURITY_PRINCIPAL, getLdapSecurityPrincipals(usuario, dominio.getNomeDominio()));
                env.put(Context.SECURITY_CREDENTIALS, senha);
                if (!"".equalsIgnoreCase(senha)) {
                    return new InitialDirContext(env);
                } else {
                    throw new LoginException("Senha ou Usuario invalidos");
                }

            } catch (NamingException e) {

                /**
                 * Tratamento de excessão quanto a autenticação do usuário
                 */
                if (e instanceof AuthenticationException) {
                    String codigo = e.getExplanation().substring(100, 103);
                    if (codigo.equalsIgnoreCase("52e")) {
                        throw new LoginException(LDAPParametrosConfig.LDAP_AUTHENTICATION_ERROR_52e);
                    } else if (codigo.equalsIgnoreCase("525")) {
                        if (i++ < dominiosAtivos.size()) {
                            continue dominios;
                        }
                        throw new LoginException(LDAPParametrosConfig.LDAP_AUTHENTICATION_ERROR_525);
                    } else if (codigo.equalsIgnoreCase("530")) {
                        throw new LoginException(LDAPParametrosConfig.LDAP_AUTHENTICATION_ERROR_530);
                    } else if (codigo.equalsIgnoreCase("531")) {
                        throw new LoginException(LDAPParametrosConfig.LDAP_AUTHENTICATION_ERROR_531);
                    } else if (codigo.equalsIgnoreCase("532")) {
                        throw new LoginException(LDAPParametrosConfig.LDAP_AUTHENTICATION_ERROR_532);
                    } else if (codigo.equalsIgnoreCase("533")) {
                        throw new LoginException(LDAPParametrosConfig.LDAP_AUTHENTICATION_ERROR_533);
                    } else if (codigo.equalsIgnoreCase("568")) {
                        throw new LoginException(LDAPParametrosConfig.LDAP_AUTHENTICATION_ERROR_568);
                    } else if (codigo.equalsIgnoreCase("701")) {
                        throw new LoginException(LDAPParametrosConfig.LDAP_AUTHENTICATION_ERROR_701);
                    } else if (codigo.equalsIgnoreCase("773")) {
                        throw new LoginException(LDAPParametrosConfig.LDAP_AUTHENTICATION_ERROR_773);
                    } else if (codigo.equalsIgnoreCase("775")) {
                        throw new LoginException(LDAPParametrosConfig.LDAP_AUTHENTICATION_ERROR_775);
                    }
                }

                if (e instanceof CommunicationException) {
                    if (i++ < dominiosAtivos.size()) {
                        continue dominios;
                    }
                    throw new LoginException(LDAPParametrosConfig.LDAP_PROTOCOL_ERROR + "\n" + e.getLocalizedMessage());
                }
                if (e instanceof TimeLimitExceededException) {
                    if (i++ < dominiosAtivos.size()) {
                        continue dominios;
                    }
                    throw new LoginException(LDAPParametrosConfig.LDAP_TIME_LIMIT_EXCEEDED_ERROR);
                }
                if (e instanceof ServiceUnavailableException) {
                    if (i++ < dominiosAtivos.size()) {
                        continue dominios;
                    }
                    throw new LoginException(LDAPParametrosConfig.LDAP_UNAVAILABLE_ERROR);
                }

                throw new LoginException(LDAPParametrosConfig.LDAP_NAMING_ERROR + "\n" + e.getLocalizedMessage());
            }
        }
        throw new LoginException("Não foi possível realizar o login via LDAP");
    }

    /**
     * Método que retorna o provider para conexão LDAP
     *
     * @param ip
     * @return ldap provider url
     * @version 1.0
     * @author Igor Pereira Dutra
     * @since 09/07/2014
     */
    private String getLdapProvider(String ip) {
        String providers = "ldap://" + ip + ":389";
        return new String(providers);
    }

    private String getLdapSecurityPrincipals(String cdUsu, String domnio) {
        String principals = domnio + "\\" + cdUsu;
        return new String(principals);
    }

}
