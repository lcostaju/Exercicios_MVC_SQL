package br.edu.iftm.tspi.pmvc.aula20241104.domain;

public class Usuario {
    private String nome;
    private String login;
    private String senha;
    private Boolean administrador;

    public Usuario(){
        
    }

    public Usuario(String nome, String login, String senha, Boolean administrador) {
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.administrador = administrador;
    }

    public Boolean isAdministrador(){
        return administrador;
    }

    public String getNome() {
        return nome;
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    public Boolean getAdministrador() {
        return administrador;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setAdministrador(Boolean administrador) {
        this.administrador = administrador;
    }
    
}
