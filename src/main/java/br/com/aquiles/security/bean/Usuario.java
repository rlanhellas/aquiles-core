package br.com.aquiles.security.bean;

import br.com.aquiles.core.converter.GenericBooleanConverter;
import br.com.aquiles.persistence.bean.AbstractEntityAtu;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "usuario")
public class Usuario extends AbstractEntityAtu {

    @Id
    @Column(name = "CD_USU")
    private String cdUsu;

    @Column(name = "NM_USU")
    private String nmUsu;

    @Column(name = "DE_SNH_USU")
    private Long deSnhUsu;

    @Column(name = "DE_SNH_MD5")
    private String deSnhMd5;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sg_pfl")
    private Perfil perfil;

    @Column(name = "ID_AUT_LDP")
    @Convert(converter = GenericBooleanConverter.class)
    private Boolean isLDAP;

    @Column(name = "DT_ULT_ACO")
    @Temporal(TemporalType.DATE)
    private Date dtUltAco;

    @Column(name = "DH_ULT_TEN")
    @Temporal(TemporalType.DATE)
    private Date dhUltTen;

    @Column(name = "DT_PNU_ACO")
    @Temporal(TemporalType.DATE)
    private Date dtPnuAco;

    @Column(name = "NRO_TEN_ACE")
    private Integer nroTenAce;

    @Column(name = "CD_LOC")
    private Integer cdLoc;

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    @Override
    public Object getId() {
        return this.cdUsu;
    }

    public Boolean getLDAP() {
        return isLDAP;
    }

    public void setLDAP(Boolean LDAP) {
        isLDAP = LDAP;
    }

    public String getCdUsu() {
        return cdUsu;
    }

    public void setCdUsu(String cdUsu) {
        this.cdUsu = cdUsu;
    }

    public String getNmUsu() {
        return nmUsu;
    }

    public void setNmUsu(String nmUsu) {
        this.nmUsu = nmUsu;
    }

    public Long getDeSnhUsu() {
        return deSnhUsu;
    }

    public void setDeSnhUsu(Long deSnhUsu) {
        this.deSnhUsu = deSnhUsu;
    }

    public String getDeSnhMd5() {
        return deSnhMd5;
    }

    public void setDeSnhMd5(String deSnhMd5) {
        this.deSnhMd5 = deSnhMd5;
    }

    public Date getDtUltAco() {
        return dtUltAco;
    }

    public void setDtUltAco(Date dtUltAco) {
        this.dtUltAco = dtUltAco;
    }

    public Date getDhUltTen() {
        return dhUltTen;
    }

    public void setDhUltTen(Date dtUltTen) {
        this.dhUltTen = dtUltTen;
    }

    public Date getDtPnuAco() {
        return dtPnuAco;
    }

    public void setDtPnuAco(Date dtPnuAco) {
        this.dtPnuAco = dtPnuAco;
    }

    public Integer getNroTenAce() {
        return nroTenAce;
    }

    public void setNroTenAce(Integer nroTenAce) {
        this.nroTenAce = nroTenAce;
    }

    public Integer getCdLoc() {
        return cdLoc;
    }

    public void setCdLoc(Integer cdLoc) {
        this.cdLoc = cdLoc;
    }
}
