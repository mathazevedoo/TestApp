package com.location.inoarb.singleton;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

public abstract class PublicContentSingleton<K,E> implements Serializable {

    protected Date sessionDateInstance;

    protected Map<K, E> sessionMapInstance;

    public abstract E save(K key, E value);

    public abstract E get(String label);

}
