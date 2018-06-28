package br.com.aquiles.security.bean;

import br.com.aquiles.persistence.bean.AbstractEntity;
import br.com.aquiles.persistence.bean.Selectable;
import br.com.aquiles.security.bean.pk.DocumentoPK;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Ronaldo Lanhellas on 11/11/2016.
 */
@Entity
@Table(name = "documento")
public class Documento extends AbstractEntity implements Selectable {

    @EmbeddedId
    private DocumentoPK id;

    @Column(name = "de_doc")
    private String deDoc;

    @Column(name = "nr_ord_web")
    private Integer nrOrdWeb;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(insertable = false, updatable = false, name = "sg_mdl")
    private Modulo modulo;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "documento")
    private Set<Transacao> transacoes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({@JoinColumn(name = "sg_doc", referencedColumnName = "sg_doc", insertable = false, updatable = false),
            @JoinColumn(name = "sg_mdl", referencedColumnName = "sg_mdl", insertable = false, updatable = false)})
    private SmDocuIcone icone;

    @Transient
    private boolean selected;

    @Override
    public Object getId() {
        return id;
    }

    public void setId(DocumentoPK id) {
        this.id = id;
    }

    public String getDeDoc() {
        return deDoc;
    }

    public void setDeDoc(String deDoc) {
        this.deDoc = deDoc;
    }

    public Integer getNrOrdWeb() {
        return nrOrdWeb;
    }

    public void setNrOrdWeb(Integer nrOrdWeb) {
        this.nrOrdWeb = nrOrdWeb;
    }

    public Modulo getModulo() {
        return modulo;
    }

    public void setModulo(Modulo modulo) {
        this.modulo = modulo;
    }

    public void setTransacoes(Set<Transacao> transacoes) {
        this.transacoes = transacoes;
    }

    public Set<Transacao> getTransacoes() {
        return transacoes;
    }

    public List<Transacao> getTransacoesAsList(){
        return new ArrayList<>(this.transacoes);
    }

    @Override
    public boolean isSelected() {
        return selected;
    }

    @Override
    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public SmDocuIcone getIcone() {
        return icone;
    }

    public void setIcone(SmDocuIcone icone) {
        this.icone = icone;
    }
}
