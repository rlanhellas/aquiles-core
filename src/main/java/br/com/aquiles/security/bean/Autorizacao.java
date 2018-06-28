package br.com.aquiles.security.bean;

import br.com.aquiles.persistence.bean.AbstractEntity;
import br.com.aquiles.security.bean.pk.AutorizacaoPK;

import javax.persistence.*;

/**
 * Created by Ronaldo Lanhellas on 11/11/2016.
 */
@Entity
@Table(name = "autorizacao")
public class Autorizacao extends AbstractEntity {

    @EmbeddedId
    private AutorizacaoPK id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({@JoinColumn(name = "sg_doc",referencedColumnName="sg_doc", insertable = false, updatable = false),
            @JoinColumn(name = "sg_mdl",referencedColumnName="sg_mdl", insertable = false, updatable = false)})
    private Documento documento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({@JoinColumn(name = "sg_doc",referencedColumnName="sg_doc" ,insertable = false, updatable = false),
            @JoinColumn(name = "sg_mdl",referencedColumnName="sg_mdl" , insertable = false, updatable = false),
            @JoinColumn(name = "sg_trn",referencedColumnName="sg_trn" , insertable = false, updatable = false)})
    private Transacao transacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({@JoinColumn(name = "sg_doc",referencedColumnName="sg_doc" ,insertable = false, updatable = false),
            @JoinColumn(name = "sg_mdl",referencedColumnName="sg_mdl" , insertable = false, updatable = false),
            @JoinColumn(name = "sg_trn",referencedColumnName="sg_trn" , insertable = false, updatable = false),
            @JoinColumn(name = "sg_fuc",referencedColumnName="sg_fuc" , insertable = false, updatable = false)})
    private Funcao funcao;

    @Override
    public Object getId() {
        return id;
    }

    public void setId(AutorizacaoPK id) {
        this.id = id;
    }

    public Documento getDocumento() {
        return documento;
    }

    public void setDocumento(Documento documento) {
        this.documento = documento;
    }

    public Transacao getTransacao() {
        return transacao;
    }

    public void setTransacao(Transacao transacao) {
        this.transacao = transacao;
    }

    public Funcao getFuncao() {
        return funcao;
    }

    public void setFuncao(Funcao funcao) {
        this.funcao = funcao;
    }
}
