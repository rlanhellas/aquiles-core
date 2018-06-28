package br.com.aquiles.persistence.dialect;

import org.hibernate.dialect.PostgreSQL9Dialect;
import org.hibernate.dialect.function.StandardSQLFunction;

/**
 * Dialect criado especificamente para ser utilizado quando a base de dados for
 * PostgreSQL 9 e for necessário o uso da função <pre>replace</pre>, pois esta função
 * não funciona nativamente.
 * @author Ronaldo Lanhellas
 */
public class AquilesPostgreSQL9Dialect extends PostgreSQL9Dialect {

    public AquilesPostgreSQL9Dialect() {
        super();
        registerFunction("replace", new StandardSQLFunction("replace"));
    }
}
