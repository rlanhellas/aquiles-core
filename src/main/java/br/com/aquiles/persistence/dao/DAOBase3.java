package br.com.aquiles.persistence.dao;

import br.com.aquiles.persistence.annotation.Base3;
import org.apache.log4j.Logger;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


/**
 * @author Ronaldo Lanhellas
 */
@Named
@Base3
public class DAOBase3 extends DAO {

    private final transient Logger logger = Logger.getLogger(DAOBase3.class);

    @PersistenceContext(unitName = "base3")
    private EntityManager em;

    @Override
    public EntityManager getEm() {
        return em;
    }
}