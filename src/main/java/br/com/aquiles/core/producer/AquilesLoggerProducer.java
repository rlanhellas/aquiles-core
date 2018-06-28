package br.com.aquiles.core.producer;

import br.com.aquiles.core.util.AquilesLogger;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

/**
 * Created by Diorge Jorge on 28/12/2017.
 */
public class AquilesLoggerProducer {

    @Produces
    public AquilesLogger createLogger(InjectionPoint ip) {
        return new AquilesLogger(ip.getMember().getDeclaringClass());
    }
}
