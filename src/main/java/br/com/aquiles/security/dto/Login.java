package br.com.aquiles.security.dto;

public class Login {

    public Login(String login, String senha, String modulo, String cnpj) {
        this.login = login;
        this.senha = senha;
        this.modulo = modulo;
        this.cnpj = cnpj;
    }

    private String login;
    private String senha;
    private String modulo;
    private String cnpj;

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

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
}
