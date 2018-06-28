package br.com.aquiles.security.bean;

import br.com.aquiles.persistence.bean.AbstractEntity;
import br.com.aquiles.security.bean.pk.FuncaoPK;

import javax.persistence.*;

/**
 * Created by Ronaldo Lanhellas on 11/11/2016.
 */
@Entity
@Table(name = "funcao")
public class Funcao extends AbstractEntity {

    //lista de funcoes padroes
    public static final String SAVE  = "SAVE"; //salva
    public static final String ADIC = "ADIC"; //adiciona
    public static final String VISU  = "VISU"; //visualiza
    public static final String ALTR  = "ALTR"; //altera
    public static final String EXCL  = "EXCL"; //exclui
    public static final String RETR  = "RETR"; //busca(retrieve)
    public static final String CANC  = "CANC"; //cancela

    @EmbeddedId
    private FuncaoPK id;

    @Column(name = "de_fuc")
    private String deFuc;

    @Column(name = "cd_trf")
    private Integer cdTrf;

    @Column(name = "nr_ord_web")
    private Integer nrOrdWeb;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({@JoinColumn(name = "sg_mdl", referencedColumnName="sg_mdl", insertable = false, updatable = false),
            @JoinColumn(name = "sg_doc", referencedColumnName="sg_doc", insertable = false, updatable = false),
            @JoinColumn(name = "sg_trn", referencedColumnName="sg_trn", insertable = false, updatable = false)})
    private Transacao transacao;

    @Transient
    private boolean selected;

    @Override
    public Object getId() {
        return id;
    }

    public void setId(FuncaoPK id) {
        this.id = id;
    }

    public String getDeFuc() {
        return deFuc;
    }

    public void setDeFuc(String deFuc) {
        this.deFuc = deFuc;
    }

    public Integer getCdTrf() {
        return cdTrf;
    }

    public void setCdTrf(Integer cdTrf) {
        this.cdTrf = cdTrf;
    }

    public Integer getNrOrdWeb() {
        return nrOrdWeb;
    }

    public void setNrOrdWeb(Integer nrOrdWeb) {
        this.nrOrdWeb = nrOrdWeb;
    }

    public Transacao getTransacao() {
        return transacao;
    }

    public void setTransacao(Transacao transacao) {
        this.transacao = transacao;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
