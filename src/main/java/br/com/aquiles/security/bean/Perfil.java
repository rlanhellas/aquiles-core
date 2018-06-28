package br.com.aquiles.security.bean;

import br.com.aquiles.persistence.bean.AbstractEntity;
import br.com.aquiles.persistence.bean.Selectable;

import javax.persistence.*;

/**
 * Created by Ronaldo Lanhellas on 11/11/2016.
 */
@Entity
@Table(name = "perfil")
public class Perfil extends AbstractEntity implements Selectable {

    @Id
    @Column(name = "sg_pfl")
    private String sgPfl;

    @Column(name = "de_pfl")
    private String dePfl;

    @Transient
    private String sgMdl;

    @Transient
    private boolean selected;

    @Override
    public Object getId() {
        return sgPfl;
    }

    public String getSgPfl() {
        return sgPfl;
    }

    public void setSgPfl(String sgPfl) {
        this.sgPfl = sgPfl;
    }

    public String getDePfl() {
        return dePfl;
    }

    public void setDePfl(String dePfl) {
        this.dePfl = dePfl;
    }

    public String getSgMdl() {
        return sgMdl;
    }

    public void setSgMdl(String sgMdl) {
        this.sgMdl = sgMdl;
    }

    @Override
    public boolean isSelected() {
        return this.selected;
    }

    @Override
    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
