package br.com.aquiles.core.producer;

import org.quartz.Job;
import org.quartz.Scheduler;
import org.quartz.spi.JobFactory;
import org.quartz.spi.TriggerFiredBundle;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.inject.Inject;
import javax.inject.Named;

@Named("cdiJobFactory")
public class CdiJobFactory implements JobFactory{
    @Inject
    BeanManager beanManager;

    @Override
    public Job newJob(TriggerFiredBundle bundle, Scheduler scheduler) {
        Class jobClazz = bundle.getJobDetail().getJobClass();
        Bean bean = beanManager.getBeans(jobClazz).iterator().next();
        CreationalContext ctx = beanManager.createCreationalContext(bean);
        return (Job) beanManager.getReference(bean, jobClazz, ctx);
    }
}
