package br.com.aquiles.security.bean;

import br.com.aquiles.persistence.bean.AbstractEntity;
import br.com.aquiles.persistence.bean.Selectable;
import br.com.aquiles.security.bean.pk.TransacaoPK;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Ronaldo Lanhellas on 11/11/2016.
 */
@Entity
@Table(name = "transacao")
public class Transacao extends AbstractEntity implements Selectable {

    @EmbeddedId
    private TransacaoPK id;

    @Column(name = "de_trn")
    private String deTrn;

    @Column(name = "id_aud")
    private Character idAud;

    @Column(name = "nr_ord_web")
    private Integer nrOrdWeb;

    @Column(name = "sg_agt")
    private String sgAgt;

    @Column(name = "sg_flx")
    private String sgFlx;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({@JoinColumn(name = "sg_mdl", referencedColumnName="sg_mdl", insertable = false, updatable = false),
            @JoinColumn(name = "sg_doc", referencedColumnName="sg_doc", insertable = false, updatable = false)})
    private Documento documento;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "transacao")
    private Set<Funcao> funcoes;

    @Transient
    private boolean selected;

    @Override
    public Object getId() {
        return id;
    }

    public void setId(TransacaoPK id) {
        this.id = id;
    }

    public String getDeTrn() {
        return deTrn;
    }

    public void setDeTrn(String deTrn) {
        this.deTrn = deTrn;
    }

    public Character getIdAud() {
        return idAud;
    }

    public void setIdAud(Character idAud) {
        this.idAud = idAud;
    }

    public Integer getNrOrdWeb() {
        return nrOrdWeb;
    }

    public void setNrOrdWeb(Integer nrOrdWeb) {
        this.nrOrdWeb = nrOrdWeb;
    }

    public String getSgAgt() {
        return sgAgt;
    }

    public void setSgAgt(String sgAgt) {
        this.sgAgt = sgAgt;
    }

    public String getSgFlx() {
        return sgFlx;
    }

    public void setSgFlx(String sgFlx) {
        this.sgFlx = sgFlx;
    }

    public Documento getDocumento() {
        return documento;
    }

    public void setDocumento(Documento documento) {
        this.documento = documento;
    }

    public Set<Funcao> getFuncoes() {
        return funcoes;
    }

    public List<Funcao> getFuncoesAsList(){
        return new ArrayList<>(this.funcoes);
    }

    public void setFuncoes(Set<Funcao> funcoes) {
        this.funcoes = funcoes;
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
