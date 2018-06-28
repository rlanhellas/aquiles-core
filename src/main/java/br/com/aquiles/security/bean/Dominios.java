package br.com.aquiles.security.bean;

import br.com.aquiles.persistence.bean.AbstractEntity;
import br.com.aquiles.persistence.bean.Selectable;

import javax.persistence.*;

@Entity
@Table(name = "dominios")
public class Dominios extends AbstractEntity implements Selectable {

    @Id
    @Column(name = "nm_domi")
    private String nomeDominio;

    @Column(name = "ds_ip")
    private String descricaoIp;

    @Column(name = "id_sit")
    private String situacaoDominio;

    @Transient
    public boolean selected;

    public String getDescricaoIp() {
        return descricaoIp;
    }

    public void setDescricaoIp(String descricaoIp) {
        this.descricaoIp = descricaoIp;
    }

    public String getNomeDominio() {
        return nomeDominio;
    }

    public void setNomeDominio(String nomeDominio) {
        this.nomeDominio = nomeDominio;
    }

    public String getSituacaoDominio() {
        return situacaoDominio;
    }

    public void setSituacaoDominio(String situacaoDominio) {
        this.situacaoDominio = situacaoDominio;
    }

    @Override
    public Object getId() {
        return nomeDominio;
    }

    @Override
    public boolean isSelected() {
        return selected;
    }

    @Override
    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
