package br.com.aquiles.core.util;

import org.apache.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by rlanhellas on 12/04/2017.
 */
@ApplicationScoped //Singleton
public class ScheduleManager {
    private ScheduledExecutorService scheduler;
    private Logger logger = Logger.getLogger(getClass().getName());

    @PostConstruct
    public void init() {
        logger.info("Starting scheduler.");
        scheduler = Executors.newScheduledThreadPool(3);
    }
    @PreDestroy
    public void cleanup() {
        logger.info("Shutting scheduler.");
        scheduler.shutdown();
    }
    public void schedule(Runnable r, int secondsLater) {
        scheduler.schedule(r, secondsLater, TimeUnit.SECONDS);
    }
}