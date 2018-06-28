package br.com.aquiles.core.util;

import br.com.aquiles.persistence.dao.IDAO;
import org.apache.commons.lang3.StringUtils;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.internal.configuration.ConfigUtils;
import org.hibernate.engine.jdbc.connections.internal.DatasourceConnectionProviderImpl;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import org.hibernate.jpa.internal.EntityManagerFactoryImpl;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by Diorge Jorge on 09/04/2018.
 */
public abstract class FlywayDBListener implements ServletContextListener {

    public abstract IDAO getDao();
    public abstract String getLocation();

    public void contextInitialized(ServletContextEvent sce) {
        setupFlyWay();
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
    }

    private DataSource getDataSource() {
        DatasourceConnectionProviderImpl connectionProvider =
                (DatasourceConnectionProviderImpl) ((EntityManagerFactoryImpl) getDao().getEm().getEntityManagerFactory())
                        .getSessionFactory()
                        .getServiceRegistry()
                        .getService(ConnectionProvider.class);
        return connectionProvider.getDataSource();
    }

    private String getLocationFormatado(){
        return StringUtils.join("db/",getLocation());
    }

    public void setupFlyWay() {
        Flyway flyway = new Flyway();
        Properties properties = new Properties();
        properties.put(ConfigUtils.BASELINE_ON_MIGRATE, "true");
        properties.put(ConfigUtils.BASELINE_VERSION, "0");
        properties.put(ConfigUtils.LOCATIONS, getLocationFormatado());
        properties.put(ConfigUtils.SQL_MIGRATION_SEPARATOR,"@");
        if (!getLocation().equalsIgnoreCase("default")) {
            properties.put(ConfigUtils.TABLE, "flyway_schema_history_" + getLocation());
        }
        flyway.configure(properties);
        flyway.setDataSource(getDataSource());
        flyway.migrate();
    }

}
