package br.com.aquiles.security.service;

import br.com.aquiles.core.exception.ServiceException;
import br.com.aquiles.core.service.GenericService;
import br.com.aquiles.persistence.annotation.Seguranca;
import br.com.aquiles.security.bean.UserKey;
import br.com.aquiles.security.dto.Subject;
import br.com.aquiles.security.util.Md5Utils;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.transaction.Transactional;

@Named
@RequestScoped
public class ApiKeysService extends GenericService {

    private static final String SALT = "e94eab00-75e4-4eb6-a38f-98379f449f3a";

    @Transactional
    public boolean validateToken(Subject subject)throws ServiceException {
      return getUserKey(subject.getApiKey())!=null;
    }

    @Transactional
    public UserKey getUserKey(String apiKey)throws ServiceException {
        UserKey userKey = new UserKey();
        userKey.setApiKey(apiKey);
        return (UserKey) this.find(userKey, Seguranca.class) ;
    }
    @Transactional
    public UserKey getUserKey(Subject subject)throws ServiceException {
        UserKey userKey = new UserKey();
        userKey.setUser(subject.getLogin());
        userKey.setModulo(subject.getModulo());
        return (UserKey) this.find(userKey, Seguranca.class) ;
    }

    @Transactional
    public UserKey createApiKey(Subject subject) throws ServiceException{
        UserKey userKey = new UserKey();
        userKey.setApiKey(Md5Utils.convertToMd5(SALT+subject.getLogin()));
        userKey.setUser(subject.getLogin());
        userKey.setModulo(subject.getModulo());
        return (UserKey) this.save(userKey, Seguranca.class);
    }

    public static String getApiKey(String login){
        return Md5Utils.convertToMd5(SALT+login);
    }
}
