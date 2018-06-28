package br.com.aquiles.core.dto;

import java.util.ArrayList;
import java.util.List;

public class DocServico {
    String caminhoPrincipal;
    String descricao;
    List<Metodo> metodos = new ArrayList<Metodo>();

    public String getCaminhoPrincipal() {
        return caminhoPrincipal;
    }

    public void setCaminhoPrincipal(String caminhoPrincipal) {
        this.caminhoPrincipal = caminhoPrincipal;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<Metodo> getMetodos() {
        return metodos;
    }

    public void setMetodos(List<Metodo> metodos) {
        this.metodos = metodos;
    }
}
