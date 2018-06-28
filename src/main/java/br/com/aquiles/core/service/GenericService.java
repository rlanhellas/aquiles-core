package br.com.aquiles.core.service;

import br.com.aquiles.core.dto.NamedParams;
import br.com.aquiles.core.dto.ParamInOut;
import br.com.aquiles.core.exception.CoreException;
import br.com.aquiles.core.exception.ServiceException;
import br.com.aquiles.core.util.StringUtils;
import br.com.aquiles.persistence.annotation.*;
import br.com.aquiles.persistence.bean.AbstractEntity;
import br.com.aquiles.persistence.dao.IDAO;
import br.com.aquiles.persistence.exception.DAOException;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.log4j.Logger;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Transactional(rollbackOn = CoreException.class)
public abstract class GenericService implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private final transient Logger logger = Logger.getLogger(GenericService.class);

    @Inject
    @Default
    protected IDAO dao;

    @Inject
    @Seguranca
    protected IDAO daoSeguranca;

    @Inject
    @Base1
    protected IDAO daoBase1;

    @Inject
    @Base2
    protected IDAO daoBase2;

    @Inject
    @Base3
    protected IDAO daoBase3;

    public IDAO getDAO() throws DAOException {
        return getDAO(null);
    }

    public IDAO getDAO(Class qualifier) throws DAOException {

        if (getClass().getSuperclass() != null) {
            if (qualifier == null && getClass().getSuperclass().isAnnotationPresent(DefaultDatabase.class)) {
                qualifier = getClass().getSuperclass().getAnnotation(DefaultDatabase.class).qualifier();
            }
        }

        if (qualifier == null || qualifier.equals(Default.class)) {
            return dao;
        } else if (qualifier.equals(Seguranca.class) || qualifier.isAnnotationPresent(Seguranca.class)) {
            return daoSeguranca;
        } else if (qualifier.equals(Base1.class) || qualifier.isAnnotationPresent(Base1.class)) {
            return daoBase1;
        } else if (qualifier.equals(Base2.class) || qualifier.isAnnotationPresent(Base2.class)) {
            return daoBase2;
        } else if (qualifier.equals(Base3.class) || qualifier.isAnnotationPresent(Base3.class)) {
            return daoBase3;
        } else {
            throw new DAOException("UnitName não configurado para qualifier = " + qualifier.getName());
        }
    }

    @SuppressWarnings("rawtypes")
    @Transactional
    public void delete(AbstractEntity entidade) throws ServiceException {
        delete(entidade, null);
    }

    @Transactional
    public void delete(AbstractEntity entidade, Class qualifier) throws ServiceException {
        try {
            getDAO(qualifier).delete(entidade);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    public void delete(List<? extends AbstractEntity> entidades, Class qualifier) throws ServiceException {
        try {
            getDAO(qualifier).delete(entidades);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    public void delete(List<? extends AbstractEntity> entidades) throws ServiceException {
        try {
            getDAO(null).delete(entidades);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    protected AbstractEntity find(AbstractEntity entidade) throws ServiceException {
        return find(entidade, null);
    }


    @SuppressWarnings("rawtypes")
    protected AbstractEntity find(AbstractEntity entidade, Class qualifier) throws ServiceException {
        try {
            return getDAO(qualifier).find(entidade);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @SuppressWarnings("rawtypes")
    public void refresh(AbstractEntity entidade, Class qualifier) throws ServiceException {
        try {
            getDAO(qualifier).refresh(entidade);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    public void refresh(AbstractEntity entidade) throws ServiceException {
        try {
            refresh(entidade, null);
        } catch (ServiceException e) {
            throw new ServiceException(e);
        }
    }

    @SuppressWarnings("rawtypes")
    @Transactional
    public AbstractEntity save(AbstractEntity entidade, Class qualifier) throws ServiceException {
        try {
            return getDAO(qualifier).save(entidade);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Transactional
    public AbstractEntity save(AbstractEntity entidade) throws ServiceException {
        return save(entidade, null);
    }

    @SuppressWarnings("rawtypes")
    public void save(List<? extends AbstractEntity> entidades, Class qualifier) throws ServiceException {
        try {
            getDAO(qualifier).save(entidades);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    public void save(List<? extends AbstractEntity> entidades) throws ServiceException {
        save(entidades, null);
    }

    @SuppressWarnings("rawtypes")
    protected List findList(String hql, Map<String, Object> namedParams, Integer first,
                            Integer maxResult, Class qualifier, boolean buscarTodos) throws ServiceException {
        try {
            return getDAO(qualifier).findList(hql, namedParams, first, maxResult, buscarTodos);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @SuppressWarnings("rawtypes")
    protected List findList(String hql, Map<String, Object> namedParams) throws ServiceException {
        return findList(hql, namedParams, null, null, null, false);
    }

    protected List findList(String hql, Map<String, Object> namedParams, boolean buscarTodos) throws ServiceException {
        return findList(hql, namedParams, null, null, null, buscarTodos);
    }

    protected List findList(String hql, Map<String, Object> namedParams, Class qualifier, boolean buscarTodos) throws ServiceException {
        return findList(hql, namedParams, null, null, qualifier, buscarTodos);
    }

    protected List findList(String hql, Map<String, Object> namedParams, Class qualifier) throws ServiceException {
        return findList(hql, namedParams, null, null, qualifier, false);
    }

    protected List findList(String hql) throws ServiceException {
        return findList(hql, null);
    }

    protected List findList(String hql, boolean buscarTodos) throws ServiceException {
        return findList(hql, null, buscarTodos);
    }

    protected List findList(String hql, Map<String, Object> namedParams, Integer first,
                            Integer maxResult) throws ServiceException {
        return findList(hql, namedParams, first, maxResult, null, false);
    }

    @SuppressWarnings("rawtypes")
    protected Object findSingleResult(String hql, Map<String, Object> namedParams, Class qualifier) throws ServiceException {
        try {
            return getDAO(qualifier).findSingleResult(hql, namedParams);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    protected Object findSingleResult(String hql, Map<String, Object> namedParams) throws ServiceException {
        return findSingleResult(hql, namedParams, null);
    }

    protected Object callFunction(String hql, NamedParams namedParams, Class qualifier) throws ServiceException {
        try {
            return getDAO(qualifier).callFunction(hql, namedParams);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    protected Object callFunction(String hql) throws ServiceException {
        try {
            return getDAO(null).callFunction(hql, null);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    protected Object callFunction(String hql, NamedParams namedParams) throws ServiceException {
        return callFunction(hql, namedParams, null);
    }

    protected Object callFunction(String hql, NamedParams namedParams, boolean singleResult) throws ServiceException {
        try {
            return getDAO(null).callFunction(hql, namedParams, singleResult);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    protected List callProcedure(String procedureName, List<ParamInOut> params, Class qualifier) throws ServiceException {
        try {
            return getDAO(qualifier).callProcedure(procedureName, params);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    protected List callProcedure(String procedureName, List<ParamInOut> params) throws ServiceException {
        try {
            return getDAO(null).callProcedure(procedureName, params);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @SuppressWarnings("rawtypes")
    public AbstractEntity findByClass(Class<? extends AbstractEntity> clazz, Object id, Class qualifier) throws ServiceException {
        try {
            return getDAO(qualifier).findByClass(clazz, id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    public AbstractEntity findByClass(Class<? extends AbstractEntity> clazz, Object id) throws ServiceException {
        return findByClass(clazz, id, null);
    }

    @SuppressWarnings("rawtypes")
    protected List<? extends AbstractEntity> findListByModel(AbstractEntity example, Class qualifier, Integer maxResults, NamedParams namedParamsRange) throws ServiceException {
        return findListByModel(example, qualifier, maxResults, namedParamsRange, null);
    }

    @SuppressWarnings("rawtypes")
    public List<? extends AbstractEntity> findListByModel(AbstractEntity example, NamedParams namedParamsRange, String columnOrderBy) throws ServiceException {
        return findListByModel(example, null, null, namedParamsRange, columnOrderBy);
    }

    @SuppressWarnings("rawtypes")
    public List<? extends AbstractEntity> findListByModel(AbstractEntity example, String columnOrderBy) throws ServiceException {
        return findListByModel(example, null, null, null, columnOrderBy);
    }

    protected List<? extends AbstractEntity> findListByModel(AbstractEntity example, Class qualifier, Integer maxResults, NamedParams namedParamsRange, String columnOrderBy) throws ServiceException {
        try {
            return getDAO(qualifier).findListByModel(example, maxResults, namedParamsRange, columnOrderBy);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    protected List<? extends AbstractEntity> findListByModel(AbstractEntity example, int maxResults, NamedParams namedParams) throws ServiceException {
        return findListByModel(example, null, maxResults, namedParams);
    }

    protected List<? extends AbstractEntity> findListByModel(AbstractEntity example, Class qualifier, NamedParams namedParamsPeriodo) throws ServiceException {
        return findListByModel(example, qualifier, null, namedParamsPeriodo);
    }

    public List<? extends AbstractEntity> findListByModel(AbstractEntity example, NamedParams namedParamsPeriodo) throws ServiceException {
        return findListByModel(example, null, null, namedParamsPeriodo);
    }

    protected List<? extends AbstractEntity> findListByModel(AbstractEntity example, int maxResults) throws ServiceException {
        return findListByModel(example, null, maxResults, null);
    }

    protected List<? extends AbstractEntity> findListByModel(AbstractEntity example, Class qualifier, int maxResults) throws ServiceException {
        return findListByModel(example, qualifier, maxResults, null);
    }

    protected List<? extends AbstractEntity> findListByModel(AbstractEntity example, Class qualifier) throws ServiceException {
        return findListByModel(example, qualifier, null, null);
    }

    public List<? extends AbstractEntity> findListByModel(AbstractEntity example) throws ServiceException {
        return findListByModel(example, null, null, null);
    }

    @Deprecated
    protected List<? extends AbstractEntity> findByModelSingleResult(AbstractEntity example, Class qualifier, NamedParams namedParamsRange) throws ServiceException {
        try {
            return getDAO(qualifier).findByModelSingleResult(example, namedParamsRange);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Deprecated
    protected List<? extends AbstractEntity> findByModelSingleResult(AbstractEntity example, NamedParams namedParams) throws ServiceException {
        return findByModelSingleResult(example, null, namedParams);
    }

    @Deprecated
    protected List<? extends AbstractEntity> findByModelSingleResult(AbstractEntity example) throws ServiceException {
        return findByModelSingleResult(example, null, null);
    }

    /**
     * Find all beans based on Class argument
     *
     * @param clazz            Entity Class to findn
     * @param orderBy          OrderBy clause, if exists
     * @param first            return list starting in this index
     * @param maxResult        return list until this index
     * @param qualifier        Database qualifier, @Default is used if nothing is passed
     * @param fetchAllDatabase retrieve all rows in database, this is dangerous and can crash your system
     */
    public List<? extends AbstractEntity> findAll(Class<? extends AbstractEntity> clazz,
                                                  String orderBy,
                                                  Integer first,
                                                  Integer maxResult,
                                                  Class qualifier, boolean fetchAllDatabase) throws ServiceException {
        return findList("SELECT c FROM " + clazz.getName() + " c " + (StringUtils.isEmpty(orderBy) ? "" : "ORDER BY c." + orderBy),
                null, first, maxResult, qualifier, fetchAllDatabase);
    }

    /**
     * Find all beans based on Class argument
     *
     * @param clazz Entity Class to findn
     */
    public List<? extends AbstractEntity> findAll(Class<? extends AbstractEntity> clazz) throws ServiceException {
        return findAll(clazz, "id DESC", null, null, null, false);
    }

    /**
     * Find all beans based on Class argument
     *
     * @param clazz   Entity Class to findn
     * @param orderBy OrderBy clause, if exists
     */
    public List<? extends AbstractEntity> findAll(Class<? extends AbstractEntity> clazz, String orderBy) throws ServiceException {
        return findAll(clazz, orderBy, null, null, null, false);
    }

    public void flushAndClear(Class qualifier) throws ServiceException {
        try {
            getDAO(qualifier).getEm().flush();
            getDAO(qualifier).getEm().clear();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    public void flushAndClear() throws ServiceException {
        flushAndClear(null);
    }

    protected static <T> T assertNotNull(T object, String message, Object... args) throws ServiceException {
        if (!Objects.isNull(object)) {
            return object;
        } else {
            throw new ServiceException(args != null && args.length > 0 ? String.format(message, args) : message);
        }
    }

    protected static <T extends Collection<?>> T assertNotEmpty(T coll, String message, Object... args) throws ServiceException {
        if (!CollectionUtils.isEmpty(coll)) {
            return coll;
        } else {
            throw new ServiceException(args != null && args.length > 0 ? String.format(message, args) : message);
        }
    }

    protected static String assertNotEmpty(String str, String message, Object... args) throws ServiceException {
        if (!org.apache.commons.lang3.StringUtils.isEmpty(str)) {
            return str;
        } else {
            throw new ServiceException(args != null && args.length > 0 ? String.format(message, args) : message);
        }
    }

    protected static String assertNotBlank(String str, String message, Object... args) throws ServiceException {
        if (!org.apache.commons.lang3.StringUtils.isBlank(str)) {
            return str;
        } else {
            throw new ServiceException(args != null && args.length > 0 ? String.format(message, args) : message);
        }
    }

    protected static void assertNull(Object object, String message, Object... args) throws ServiceException {
        if (Objects.nonNull(object)) {
            throw new ServiceException(args != null && args.length > 0 ? String.format(message, args) : message);
        }
    }

    protected static void assertEmpty(Collection<?> coll, String message, Object... args) throws ServiceException {
        if (CollectionUtils.isNotEmpty(coll)) {
            throw new ServiceException(args != null && args.length > 0 ? String.format(message, args) : message);
        }
    }

    protected static void assertEmpty(String str, String message, Object... args) throws ServiceException {
        if (org.apache.commons.lang3.StringUtils.isNotEmpty(str)) {
            throw new ServiceException(args != null && args.length > 0 ? String.format(message, args) : message);
        }
    }

    protected static void assertBlank(String str, String message, Object... args) throws ServiceException {
        if (org.apache.commons.lang3.StringUtils.isNotBlank(str)) {
            throw new ServiceException(args != null && args.length > 0 ? String.format(message, args) : message);
        }
    }

    protected static void assertTrue(boolean expression, String message, Object... args) throws ServiceException {
        if (!expression) {
            throw new ServiceException(args != null && args.length > 0 ? String.format(message, args) : message);
        }
    }

    protected static void assertFalse(boolean expression, String message, Object... args) throws ServiceException {
        if (expression) {
            throw new ServiceException(args != null && args.length > 0 ? String.format(message, args) : message);
        }
    }
}