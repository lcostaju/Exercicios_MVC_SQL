package br.edu.iftm.pmvc.aula20241209.domain;

public class Artista {
    
    private String nome;

    private Integer codigo;

    public Artista() {
    }

    public Artista(String nome, Integer codigo) {
        this.nome = nome;
        this.codigo = codigo;
    }

    public Artista(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }
}
