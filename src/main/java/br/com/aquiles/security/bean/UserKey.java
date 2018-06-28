package br.com.aquiles.security.bean;

import br.com.aquiles.persistence.bean.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_key")
public class UserKey extends AbstractEntity {
    @Id
    @Column(name = "LOGIN")
    String login;
    @Id
    @Column(name = "MODULO")
    String modulo;
    @Column(name = "APIKEY")
    String apiKey;

    public String getUser() {
        return login;
    }

    public void setUser(String user) {
        this.login = user;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getModulo() {
        return modulo;
    }

    public void setModulo(String modulo) {
        this.modulo = modulo;
    }

    @Override
    public Object getId() {
        return login;
    }
}
