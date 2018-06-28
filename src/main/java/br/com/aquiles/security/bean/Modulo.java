package br.com.aquiles.security.bean;

import br.com.aquiles.core.converter.GenericBooleanConverter;
import br.com.aquiles.persistence.bean.AbstractEntity;

import javax.persistence.*;

@Entity
@Table(name = "modulo")
public class Modulo extends AbstractEntity {

    @Id
    @Column(name = "sg_mdl")
    private String sgMdl;

    @Column(name = "de_mdl")
    private String deMdl;

    @Column(name = "id_ace_sup")
    @Convert(converter = GenericBooleanConverter.class)
    private Boolean idAceSup;

    @Column(name = "id_sit_web")
    private Character idSitWeb;

    @Column(name = "id_tp_sis")
    private String idTpSis;

    @Override
    public Object getId() {
        return sgMdl;
    }

    public String getSgMdl() {
        return sgMdl;
    }

    public void setSgMdl(String sgMdl) {
        this.sgMdl = sgMdl;
    }

    public String getDeMdl() {
        return deMdl;
    }

    public void setDeMdl(String deMdl) {
        this.deMdl = deMdl;
    }

    public Boolean getIdAceSup() {
        return idAceSup;
    }

    public void setIdAceSup(Boolean idAceSup) {
        this.idAceSup = idAceSup;
    }

    public Character getIdSitWeb() {
        return idSitWeb;
    }

    public void setIdSitWeb(Character idSitWeb) {
        this.idSitWeb = idSitWeb;
    }

    public String getIdTpSis() {
        return idTpSis;
    }

    public void setIdTpSis(String idTpSis) {
        this.idTpSis = idTpSis;
    }
}
