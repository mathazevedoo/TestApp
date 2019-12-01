package com.location.inoarb.singleton.impl;

import com.location.inoarb.singleton.PublicContentSingleton;

import java.util.LinkedHashMap;

public final class SessionSingleton extends PublicContentSingleton<String, String> {

    public static final SessionSingleton getInstance(){
        return new SessionSingleton();
    }

    @Override
    public String save(String key, String value) {
        if(this.sessionMapInstance == null) {
            this.sessionMapInstance = new LinkedHashMap<>();
        }
        this.sessionMapInstance.put(key, value);
        return value;
    }

    @Override
    public String get(String label) {
        if(this.sessionMapInstance == null) {
            return label;
        }
        return this.sessionMapInstance.get(label);
    }
}
