package br.com.aquiles.security.dto;

import br.com.aquiles.core.exception.ServiceException;
import br.com.aquiles.security.bean.Perfil;
import br.com.aquiles.security.bean.UserKey;
import br.com.aquiles.security.service.ApiKeysService;
import org.apache.commons.lang3.StringUtils;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.*;

public class Subject implements Serializable {
    @Inject
    private ApiKeysService apiKeysService;
    private String uuid;
    private String login;
    private String nome;
    private String senha;
    private String modulo;
    private boolean ldap;
    private Perfil perfil;
    private List<AutorizacaoDTO> autorizacao;
    private Map<String, Object> customValues; //Valores customizados para cada projeto
    private Integer cdLoc; //unidade de localização do usuário

    public Subject() {
        this.uuid = UUID.randomUUID().toString();
        autorizacao = new ArrayList<AutorizacaoDTO>();
        customValues = new HashMap<String, Object>();
    }

    public String getUuid() {
        return uuid;
    }

    public List<AutorizacaoDTO> getAutorizacao() {
        return autorizacao;
    }

    public void setAutorizacao(List<AutorizacaoDTO> autorizacao) {
        this.autorizacao = autorizacao;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getModulo() {
        return modulo;
    }

    public void setModulo(String modulo) {
        this.modulo = modulo;
    }

    public boolean isLdap() {
        return ldap;
    }

    public void setLdap(boolean ldap) {
        this.ldap = ldap;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public Map<String, Object> getCustomValues() {
        return customValues;
    }

    public void setCustomValues(Map<String, Object> customValues) {
        this.customValues = customValues;
    }

    public String getApiKey() {
        UserKey userKey;
        try {
           userKey = apiKeysService.createApiKey(this);
        } catch (ServiceException e) {
            return ApiKeysService.getApiKey(this.login);
    }
        return userKey.getApiKey();
    }

    public Integer getCdLoc() {
        return cdLoc;
    }

    public void setCdLoc(Integer cdLoc) {
        this.cdLoc = cdLoc;
    }

    @Override
    public String toString() {
        if (!StringUtils.isEmpty(login)){
            return "Subject: "+login;
        }else{
            return super.toString();
        }
    }
}
