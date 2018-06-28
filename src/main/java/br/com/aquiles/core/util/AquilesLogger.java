package br.com.aquiles.core.util;

import br.com.aquiles.core.exception.CoreException;
import br.com.aquiles.core.exception.ServiceException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.ws.rs.core.UriBuilder;
import java.io.IOException;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Diorge Jorge on 04/07/2017.
 * logar
 * criando assim a possibilidade de rastreamento e categorização dos mesmos
 */
public class AquilesLogger implements Serializable {

    private Class clazz;
    private Logger logger;

    public AquilesLogger(Class clazz) {
        this.clazz = clazz;
        logger = Logger.getLogger(clazz);
    }

    /**
     * Send to server an error message
     *
     * @param exception
     * @throws ServiceException
     */
    public void error(Exception exception) {
        try {
            logger.error(exception);
            doRequest((HttpURLConnection) buildURL(Level.ERROR, exception).openConnection());
        } catch (IOException e) {
            logger.error(e);
        } catch (CoreException e) {
            logger.error(e);
        }
    }

    public void error(String exception) {
        error(new Exception(exception));
    }

    /**
     * Send to server an warn message
     */
    public void warn(String warMsg) {
        try {
            logger.warn(warMsg);
            doRequest((HttpURLConnection) buildURL(Level.WARN, warMsg).openConnection());
        } catch (IOException e) {
            logger.error(e);
        } catch (CoreException e) {
            logger.error(e);
        }
    }

    /**
     * Send to server an info message
     */
    public void info(String infoMsg) {
        try {
            logger.info(infoMsg);
            doRequest((HttpURLConnection) buildURL(Level.INFO, infoMsg).openConnection());
        } catch (IOException e) {
            logger.error(e);
        } catch (CoreException e) {
            logger.error(e);
        }
    }

    private URL buildURL(Level severity, Exception message) throws CoreException {
        try {
            UriBuilder builder = UriBuilder
                    .fromPath(PropertiesUtilities.getUrlLoggerTracker())
                    .path(PropertiesUtilities.getProjectRedmineId())
                    .path(severity.toString())
                    .path(message.getMessage())
                    .path(ExceptionUtils.getStackTrace(message))
                    .path(clazz.getCanonicalName());
            return builder.build().toURL();
        } catch (IOException e) {
            throw new CoreException(e.getMessage());
        }
    }

    private URL buildURL(Level severity, String message) throws CoreException {
        try {
            UriBuilder builder = UriBuilder
                    .fromPath(PropertiesUtilities.getUrlLoggerTracker())
                    .path(PropertiesUtilities.getProjectRedmineId())
                    .path(severity.toString())
                    .path(message)
                    .path(message)
                    .path(clazz.getCanonicalName());
            return builder.build().toURL();
        } catch (IOException e) {
            throw new CoreException(e.getMessage());
        }
    }

    /**
     * execute HttpURLConnection and getResponseCode
     *
     * @param connection
     * @throws IOException
     * @throws ServiceException
     */
    private void doRequest(HttpURLConnection connection) throws IOException, CoreException {
        if (PropertiesUtilities.getUsaLoggerTracker()) {
            connection.setRequestMethod("POST");
            if (connection.getResponseCode() != 200) {
                logger.error("erro ao conectar ao serviço de issue tracker");
                throw new CoreException("erro ao conectar ao serviço de issue tracker");
            }
        }
    }
}
