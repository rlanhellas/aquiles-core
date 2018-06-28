package br.com.aquiles.persistence.dao;

import br.com.aquiles.core.dto.NamedParams;
import br.com.aquiles.core.dto.ParamInOut;
import br.com.aquiles.persistence.bean.AbstractEntity;
import br.com.aquiles.persistence.exception.DAOException;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Map;


/**
 * @author Ronaldo Lanhellas, Enemias Junior
 * @since 1.0.0, em: 30.03.16
 */
public interface IDAO {


    void delete(AbstractEntity entidade) throws DAOException;

    void delete(List<? extends AbstractEntity> entidade) throws DAOException;

    AbstractEntity findByClass(Class<? extends AbstractEntity> clazz, Object id) throws DAOException;

    AbstractEntity find(AbstractEntity classe) throws DAOException;

    void refresh(AbstractEntity entidade) throws DAOException;

    AbstractEntity save(AbstractEntity entity) throws DAOException;

    void save(List<? extends AbstractEntity> entidades) throws DAOException;

    List findList(String hql, Map<String, Object> namedParams) throws DAOException;

    List findList(String hql, Map<String, Object> namedParams, Integer first,
                  Integer maxResult, boolean buscarTodos) throws DAOException;

    List findList(String hql, Map<String, Object> namedParams, Integer first,
                  Integer maxResult) throws DAOException;

    Object findSingleResult(String hql, Map<String, Object> namedParams) throws DAOException;

    <T extends AbstractEntity> List<T> findListByModel(AbstractEntity example, Integer maxResults, NamedParams namedParamsRange, String columnOrderBy) throws DAOException;

    <T extends AbstractEntity> List<T> findListByModel(AbstractEntity example, Integer maxResults, NamedParams namedParamsRange) throws DAOException;

    <T extends AbstractEntity> List<T> findListByModel(T example, NamedParams namedParamsPeriodo) throws DAOException;

    <T extends AbstractEntity> List<T> findListByModel(T example) throws DAOException;

    <T extends AbstractEntity> T findByModelSingleResult(AbstractEntity example, NamedParams namedParamsPeriodo) throws DAOException;

    <T extends AbstractEntity> T findByModelSingleResult(T example) throws DAOException;

    Object callFunction(String hql, NamedParams namedParams) throws DAOException;
    
    Object callFunction(String hql, NamedParams namedParams, boolean singleResult) throws DAOException;

    List callProcedure(String procedureName, List<ParamInOut> params) throws DAOException;

    EntityManager getEm();
}
