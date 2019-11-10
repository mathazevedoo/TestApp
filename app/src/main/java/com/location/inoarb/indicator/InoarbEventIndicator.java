package com.location.inoarb.indicator;

public enum InoarbEventIndicator {

        DEFAULT_EVENT("QUEDA DE ARVORE");

    private String value;

    InoarbEventIndicator(String value){
        this.value = value;
    }

    @Override
    public String toString(){
        return this.value;
    }
}
