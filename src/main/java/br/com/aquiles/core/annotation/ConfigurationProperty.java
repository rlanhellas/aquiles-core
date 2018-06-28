package br.com.aquiles.core.annotation;

import javax.enterprise.util.Nonbinding;
import javax.inject.Qualifier;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Qualifier
public @interface ConfigurationProperty {

    @Nonbinding
    String name();

    @Nonbinding
    String configurationFile() default "aquiles-core.properties";

}
