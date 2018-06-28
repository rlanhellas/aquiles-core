package br.com.aquiles.security.service;

import br.com.aquiles.core.dto.NamedParams;
import br.com.aquiles.core.exception.ServiceException;
import br.com.aquiles.core.service.GenericService;
import br.com.aquiles.persistence.annotation.Seguranca;
import br.com.aquiles.security.bean.Autorizacao;
import br.com.aquiles.security.bean.Usuario;
import br.com.aquiles.security.bean.pk.AutorizacaoPK;
import br.com.aquiles.security.dataquery.AutorizacaoDataQuery;
import br.com.aquiles.security.dataquery.UserDataQuery;
import br.com.aquiles.security.dto.AutorizacaoDTO;
import br.com.aquiles.security.dto.Subject;
import br.com.aquiles.security.exception.LoginException;
import br.com.aquiles.security.util.Md5Utils;
import org.apache.log4j.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Named(value = "loginService")
@RequestScoped
public class LoginService extends GenericService {

    @Inject
    @Named(value = "ldapService")
    private LdapService ldapService;
    private Logger logger = Logger.getLogger(LoginService.class);

    @Transactional
    private Subject proceedLogin(String login, String senha, String modulo) throws LoginException {
        List users = new ArrayList();
        try {
            users = this.findList(UserDataQuery.FINDBY_LOGIN,
                    new NamedParams("login", login), Seguranca.class);
        } catch (ServiceException e) {
            throw new LoginException(e);
        }
        Usuario usuario;
        if (users.isEmpty()) {
            throw new LoginException("Usuário\\Senha inválido");
        } else {
            usuario = (Usuario) users.get(0);
        }

        if (usuario.getLDAP()) {
            ldapService.proceedLdap(login, senha);
        } else {
            if (!usuario.getDeSnhMd5().equals(Md5Utils.convertToMd5(senha))) {
                throw new LoginException("Usuário\\Senha inválido");
            }
        }
        List<Autorizacao> autorizacao;
        try {
            autorizacao = (List<Autorizacao>) this.findList(
                    AutorizacaoDataQuery.FINDPERFILMODULO,
                    new NamedParams("perfil", usuario.getPerfil().getId(), "modulo", modulo),
                    Seguranca.class, true);
        } catch (ServiceException e) {
            throw new LoginException(e);
        }
        List<AutorizacaoDTO> autorizacoes = new ArrayList<>();
        for (Autorizacao auto : autorizacao) {
            AutorizacaoPK autoPK = (AutorizacaoPK) auto.getId();
            AutorizacaoDTO dto = new AutorizacaoDTO();
            dto.setDocumento(auto.getDocumento());
            dto.setSgDoc(autoPK.getSgDoc());
            dto.setSgFuc(autoPK.getSgFuc());
            dto.setSgTrn(autoPK.getSgTrn());
            dto.setTransacao(auto.getTransacao());
            dto.setFuncao(auto.getFuncao());
            autorizacoes.add(dto);
        }

        Subject subject = new Subject();
        subject.setAutorizacao(autorizacoes);
        subject.setLdap(usuario.getLDAP());
        subject.setLogin(usuario.getCdUsu());
        subject.setModulo(modulo);
        subject.setNome(usuario.getNmUsu());
        subject.setPerfil(usuario.getPerfil());
        subject.setCdLoc(usuario.getCdLoc());
        return subject;
    }

    @Transactional
    public Subject login(String login, String senha, String modulo) throws LoginException {
        return proceedLogin(login, senha, modulo);
    }


}
