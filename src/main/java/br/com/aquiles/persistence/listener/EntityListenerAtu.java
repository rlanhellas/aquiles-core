package br.com.aquiles.persistence.listener;

import br.com.aquiles.security.service.SessionService;
import org.apache.log4j.Logger;

import javax.enterprise.inject.spi.CDI;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;

import static java.util.Objects.nonNull;

public class EntityListenerAtu {

    private final Logger logger = Logger.getLogger(EntityListenerAtu.class);

    @PrePersist
    public void prePersist(final Object o) {
        try {
            atualizarCdUsuEDhAtu(o);
        } catch (Exception e) {
            logger.error(e);
        }
    }

    @PreUpdate
    public void preUpdate(final Object o) {
        try {
            atualizarCdUsuEDhAtu(o);
        } catch (Exception e) {
            logger.error(e);
        }
    }

    private void atualizarCdUsuEDhAtu(Object o) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        SessionService sessionService = CDI.current()
                .select(SessionService.class).get();
        Method method = o.getClass().getMethod("setCdUsuAtu", String.class);
        if (nonNull(method)) {
            method.invoke(o, sessionService.getSubject().getLogin());
        }

        method = o.getClass().getMethod("setDhAtu", Date.class);
        if (nonNull(method)) {
            method.invoke(o, new Date());
        }

    }
}
