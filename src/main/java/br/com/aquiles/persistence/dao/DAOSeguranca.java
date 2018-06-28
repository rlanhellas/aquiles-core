package br.com.aquiles.persistence.dao;

import br.com.aquiles.core.util.FileUtils;
import br.com.aquiles.persistence.annotation.Seguranca;
import org.apache.log4j.Logger;

import javax.inject.Named;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author Ronaldo Lanhellas
 */
@Named
@Seguranca
public class DAOSeguranca extends DAO {

    private final transient Logger logger = Logger.getLogger(DAOSeguranca.class);

    private EntityManager entityManager;

    @Override
    public EntityManager getEm() {
        if (entityManager == null) {
        /*
        * DEFAULT PROPERTIES
        *
        *   <property name="hibernate.hbm2ddl.auto" value="none"/>
            <property name="hibernate.connection.characterEncoding" value="UTF-8"/>
            <property name="hibernate.show_sql" value="true"/>
            <property name="hibernate.format_sql" value="false"/>
        *
        * */
            Map<String, String> props = new HashMap<>();
            props.put("hibernate.hbm2ddl.auto", "none");
            props.put("hibernate.connection.characterEncoding", "UTF-8");
            props.put("hibernate.show_sql", "false");
            props.put("hibernate.format_sql", "false");

            //Carrega as configurações customizadas, se existir
            try {
                InputStream is = FileUtils.readFileFromResourceFolder("aquiles-hibernate.properties");
                if (is != null) {
                    logger.info("Carregando configurações do arquivo aquiles-hibernate.properties");
                    Properties properties = new Properties();
                    properties.load(is);
                    for (String key : properties.stringPropertyNames()) {
                        props.put(key, properties.getProperty(key));
                    }
                }
            } catch (IOException e) {
                logger.error("Erro ao carregar o aquiles-hibernate.properties, ele será ignorado e as configurações padrões serão carregadas. Msg de Erro: " + e.getMessage());
            }

            entityManager = Persistence.createEntityManagerFactory("seguranca", props).createEntityManager();
        }
        return entityManager;
    }


}