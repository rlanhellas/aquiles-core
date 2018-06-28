package br.com.aquiles.security.dataquery;

public class PerfilDataQuery {

    public static final String FINDBY_SGMDL = "SELECT c.perfil FROM PerfilModulo c WHERE c.id.sgMdl = :sgMdl ORDER BY c.id.sgMdl";
    public static final String FINDPEMO_BY_SGPFL = "SELECT c FROM PerfilModulo c WHERE c.id.sgPfl = :sgPfl ORDER BY c.id.sgPfl";
    public static final String FIND_BY_SG_PFL = "SELECT c FROM Perfil c WHERE c.sgPfl = :sgPfl";
}
