package com.location.inoarb;

public class Default {

    private String mensagem;
    private boolean status;

    public Default(){
        this.mensagem= "" ;
        this.status = true;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
