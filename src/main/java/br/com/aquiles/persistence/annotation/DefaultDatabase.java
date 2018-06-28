package br.com.aquiles.persistence.annotation;

import javax.enterprise.inject.Default;
import javax.inject.Qualifier;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by Ronaldo Lanhellas
 */
@Retention(RUNTIME)
@Target({TYPE})
public @interface DefaultDatabase {
    Class qualifier() default Default.class;
}
