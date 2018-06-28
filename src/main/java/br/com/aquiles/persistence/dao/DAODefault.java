package br.com.aquiles.persistence.dao;

import org.apache.log4j.Logger;

import javax.enterprise.inject.Default;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Ronaldo Lanhellas
 */
@Named
@Default
public class DAODefault extends DAO {

    private final transient Logger logger = Logger.getLogger(DAODefault.class);

    @PersistenceContext(unitName = "default")
    private EntityManager em;

    @Override
    public EntityManager getEm() {
        return em;
    }

}