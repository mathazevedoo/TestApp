package com.location.inoarb.indicator;

public enum InoarbEventIndicator {

    DEFAULT_EVENT("EVENTO");

    private String value;

    InoarbEventIndicator(String value){
        this.value = value;
    }

    @Override
    public String toString(){
        return this.value;
    }
}
