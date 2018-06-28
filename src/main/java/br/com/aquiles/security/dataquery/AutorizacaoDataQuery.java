package br.com.aquiles.security.dataquery;

public class AutorizacaoDataQuery {
    public static final String FINDPERFILMODULO = "SELECT c FROM Autorizacao c  " +
            "JOIN FETCH c.documento doc LEFT JOIN FETCH c.transacao LEFT JOIN FETCH c.funcao " +
            "LEFT JOIN FETCH doc.icone where c.id.sgPfl =:perfil and c.id.sgMdl =:modulo";
}
