package br.com.aquiles.core.util;

import br.com.aquiles.core.exception.PropertiesLoaderException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Diorge on 06/10/2016.
 */
public class PropertiesUtilities {

    private final static String ISSUE_PROPERTY_FILE_NAME = "aquiles-issue-tracker.properties";
    private static final String URL_LOGGER_REST = "http://192.168.40.17:8084/issue-tracker/ws/loggerService/createIssue";


    public static String getUrlLoggerTracker() {
        try {
            String property = PropertiesUtilities.readPropertieFromFile(ISSUE_PROPERTY_FILE_NAME, "logger-tracker-url");
            return property == null ? URL_LOGGER_REST : property;
        } catch (PropertiesLoaderException e) {
            return URL_LOGGER_REST;
        }
    }

    public static String getProjectRedmineId() {
        try {
            String property = PropertiesUtilities.readPropertieFromFile(ISSUE_PROPERTY_FILE_NAME, "redmineProjectId");
            return property == null ? "fabrica" : property;
        } catch (PropertiesLoaderException e) {
            return "fabrica";
        }
    }

    public static Boolean getUsaLoggerTracker() {
        try {
            String property = PropertiesUtilities.readPropertieFromFile(ISSUE_PROPERTY_FILE_NAME, "usaLogTracker");
            return property == null ? false : Boolean.valueOf(property);
        } catch (PropertiesLoaderException e) {
            return false;
        }
    }

    public static Properties loadProperties(String name) throws PropertiesLoaderException {
        InputStream input = PropertiesUtilities.class.getClassLoader().getResourceAsStream(name);
        Properties prop = new Properties();
        try {
            if (input != null) {
                prop.load(input);
            }
        } catch (IOException e) {
            throw new PropertiesLoaderException("Falha ao Carregar arquivo (" + name + "). " + e.getMessage());
        }
        return prop;
    }

    public static String readPropertieFromFile(String name, String key) throws PropertiesLoaderException {
        Properties prop = loadProperties(name);
        return prop.getProperty(key);
    }
}
