package br.com.aquiles.security.dataquery;

public class UserDataQuery {
    public static final String FINDBY_LOGIN_SENHA = "SELECT u FROM Usuario u JOIN FETCH u.perfil " +
            "where u.cdUsu =:login and u.deSnhMd5 =:senha";
    public static final String FINDBY_LOGIN = "SELECT u FROM Usuario u JOIN FETCH u.perfil " +
            "where u.cdUsu =:login";
}
