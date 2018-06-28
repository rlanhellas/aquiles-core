package br.com.aquiles.core.dto;

import java.io.Serializable;

/**
 * Created by rlanhellas on 31/03/2017.
 */
public class RetornoConsultaCEP implements Serializable {

    private String cep;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String localidade;
    private String uf;
    private String unidade;
    private String ibge;
    private String gia;

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public String getIbge() {
        return ibge;
    }

    public void setIbge(String ibge) {
        this.ibge = ibge;
    }

    public String getGia() {
        return gia;
    }

    public void setGia(String gia) {
        this.gia = gia;
    }

    @Override
    public String toString() {
        return "RetornoConsultaCEP{" +
                "logradouro='" + logradouro + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RetornoConsultaCEP that = (RetornoConsultaCEP) o;

        if (cep != null ? !cep.equals(that.cep) : that.cep != null) return false;
        if (logradouro != null ? !logradouro.equals(that.logradouro) : that.logradouro != null) return false;
        if (complemento != null ? !complemento.equals(that.complemento) : that.complemento != null) return false;
        if (bairro != null ? !bairro.equals(that.bairro) : that.bairro != null) return false;
        if (localidade != null ? !localidade.equals(that.localidade) : that.localidade != null) return false;
        if (uf != null ? !uf.equals(that.uf) : that.uf != null) return false;
        if (unidade != null ? !unidade.equals(that.unidade) : that.unidade != null) return false;
        if (ibge != null ? !ibge.equals(that.ibge) : that.ibge != null) return false;
        return gia != null ? gia.equals(that.gia) : that.gia == null;
    }

    @Override
    public int hashCode() {
        int result = cep != null ? cep.hashCode() : 0;
        result = 31 * result + (logradouro != null ? logradouro.hashCode() : 0);
        result = 31 * result + (complemento != null ? complemento.hashCode() : 0);
        result = 31 * result + (bairro != null ? bairro.hashCode() : 0);
        result = 31 * result + (localidade != null ? localidade.hashCode() : 0);
        result = 31 * result + (uf != null ? uf.hashCode() : 0);
        result = 31 * result + (unidade != null ? unidade.hashCode() : 0);
        result = 31 * result + (ibge != null ? ibge.hashCode() : 0);
        result = 31 * result + (gia != null ? gia.hashCode() : 0);
        return result;
    }
}
