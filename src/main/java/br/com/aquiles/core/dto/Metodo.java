package br.com.aquiles.core.dto;

import java.util.LinkedHashMap;

public class Metodo {
    String nome;
    String descricao;
    LinkedHashMap<String,String> parametros = new LinkedHashMap<String, String>();

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LinkedHashMap getParametros() {
        return parametros;
    }

    public void setParametros(LinkedHashMap parametros) {
        this.parametros = parametros;
    }
}
