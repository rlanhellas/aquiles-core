package br.com.aquiles.persistence.dao;

import br.com.aquiles.persistence.annotation.Base1;
import org.apache.log4j.Logger;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Ronaldo Lanhellas
 */
@Named
@Base1
public class DAOBase1 extends DAO {

    private final transient Logger logger = Logger.getLogger(DAOBase1.class);

    @PersistenceContext(unitName = "base1")
    private EntityManager em;

    @Override
    public EntityManager getEm() {
        return em;
    }
}