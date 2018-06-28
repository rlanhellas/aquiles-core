package br.com.aquiles.security.dto;

import br.com.aquiles.security.bean.Documento;
import br.com.aquiles.security.bean.Funcao;
import br.com.aquiles.security.bean.Transacao;

import java.io.Serializable;

/**
 * Created by rlanhellas on 06/02/2017.
 */
public class AutorizacaoDTO implements Serializable {

    private String sgDoc;
    private String sgTrn;
    private String sgFuc;
    private Documento documento;
    private Transacao transacao;
    private Funcao funcao;
    private boolean esconderMenu;

    public Documento getDocumento() {
        return documento;
    }

    public void setDocumento(Documento documento) {
        this.documento = documento;
    }

    public String getSgDoc() {
        return sgDoc;
    }

    public void setSgDoc(String sgDoc) {
        this.sgDoc = sgDoc;
    }

    public String getSgTrn() {
        return sgTrn;
    }

    public void setSgTrn(String sgTrn) {
        this.sgTrn = sgTrn;
    }

    public String getSgFuc() {
        return sgFuc;
    }

    public void setSgFuc(String sgFuc) {
        this.sgFuc = sgFuc;
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

    public boolean isEsconderMenu() {
        return esconderMenu;
    }

    public void setEsconderMenu(boolean esconderMenu) {
        this.esconderMenu = esconderMenu;
    }
}
