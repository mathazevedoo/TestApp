package com.location.inoarb;

public class Usuario extends Default{

    private int id;
    private String nome;
    private String email;
    private String telefone;


    public Usuario(){
        super();
        this.id = -1;
        this.email = "";
        this.nome = "";
        this.telefone="";
    }
}
