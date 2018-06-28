package br.com.aquiles.core.producer;

import br.com.aquiles.core.annotation.ConfigurationProperty;
import br.com.aquiles.core.exception.PropertiesLoaderException;
import br.com.aquiles.core.util.PropertiesUtilities;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.Annotated;
import javax.enterprise.inject.spi.InjectionPoint;

/**
 * Created by Diorge Jorge on 28/12/2017.
 */
public class ConfigurationPropertyProducer {

    @ConfigurationProperty(name = "")
    @Produces
    public String producer(InjectionPoint ip){
        Annotated annotated = ip.getAnnotated();
        ConfigurationProperty configurationProperty = annotated.getAnnotation(ConfigurationProperty.class);
        if( annotated != null ){
            String file = configurationProperty.configurationFile();
            if(file != null){
                try {
                    return PropertiesUtilities.readPropertieFromFile(file,configurationProperty.name());
                } catch (PropertiesLoaderException e) {
                    return null;
                }
            }
        }
        return null;
    }
}
