package com.location.inoarb.indicator;

public enum UrlResourceIndicator {

    REQUEST_EVENT("http://167.71.181.29:7070/inoarb/request/event");

    private String value;

    UrlResourceIndicator(String value){
        this.value = value;
    }

    @Override
    public String toString(){
        return this.value;
    }
}
