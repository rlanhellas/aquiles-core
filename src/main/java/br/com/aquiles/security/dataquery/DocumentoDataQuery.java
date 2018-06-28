package br.com.aquiles.security.dataquery;

public class DocumentoDataQuery {
    public static final String FINDBY_SGMDL = "SELECT distinct(d) FROM Documento d  " +
            "LEFT JOIN FETCH d.transacoes tr LEFT JOIN FETCH tr.funcoes where d.id.sgMdl = :sgMdl";
}
