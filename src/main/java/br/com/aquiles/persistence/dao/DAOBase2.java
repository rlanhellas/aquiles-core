package br.com.aquiles.persistence.dao;

import br.com.aquiles.persistence.annotation.Base2;
import org.apache.log4j.Logger;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


/**
 * @author Ronaldo Lanhellas
 */
@Named
@Base2
public class DAOBase2 extends DAO {

    private final transient Logger logger = Logger.getLogger(DAOBase2.class);

    @PersistenceContext(unitName = "base2")
    private EntityManager em;

    @Override
    public EntityManager getEm() {
        return em;
    }
}