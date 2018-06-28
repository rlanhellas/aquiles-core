package br.com.aquiles.security.bean;

import br.com.aquiles.persistence.bean.AbstractEntity;
import br.com.aquiles.security.bean.pk.PerfilModuloPK;

import javax.persistence.*;

@Entity
@Table(name = "perfil_modulo")
public class PerfilModulo extends AbstractEntity {

    @EmbeddedId
    private PerfilModuloPK id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sg_pfl", referencedColumnName = "sg_pfl", insertable = false, updatable = false)
    private Perfil perfil;

    @Override
    public PerfilModuloPK getId() {
        return id;
    }

    public void setId(PerfilModuloPK id) {
        this.id = id;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }
}
