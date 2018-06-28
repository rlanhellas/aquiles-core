package br.com.aquiles.core.annotation;

import javax.enterprise.util.Nonbinding;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Descricao {

    @Nonbinding
    String value();

    @Nonbinding
    String defaultValue() default "";

}
