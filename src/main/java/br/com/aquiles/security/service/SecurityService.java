package br.com.aquiles.security.service;

import br.com.aquiles.core.exception.ServiceException;
import br.com.aquiles.core.service.GenericService;
import br.com.aquiles.persistence.annotation.Seguranca;
import br.com.aquiles.persistence.bean.AbstractEntity;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

/**
 * Created by rlanhellas on 06/12/2016.
 */
@Named("securityService")
@RequestScoped
public class SecurityService extends GenericService {

    @Transactional
    public List<? extends AbstractEntity> findList(String hql, Map<String, Object> namedParams) throws ServiceException{
            return findList(hql, namedParams, Seguranca.class);
    }

}
