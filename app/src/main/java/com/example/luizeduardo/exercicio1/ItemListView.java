package com.example.luizeduardo.exercicio1;

/**
 * Created by Luiz Eduardo on 14/10/2016.
 */

public class ItemListView {
    private String nome, email, categoria;
    private int iconeRid;

    public ItemListView() {
    }

    public ItemListView(String nome, String email, String categoria, int iconeRid) {
        this.nome = nome;
        this.email = email;
        this.categoria = categoria;
        this.iconeRid = iconeRid;
    }

    public int getIconeRid() {
        return iconeRid;
    }

    public void setIconeRid(int iconeRid) {
        this.iconeRid = iconeRid;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}