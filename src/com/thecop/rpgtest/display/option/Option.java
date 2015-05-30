package com.thecop.rpgtest.display.option;

/**
 * Created by TheCop on 30.05.2015.
 */
public abstract class Option<T> {
    private String key;
    private T object;

    public Option(String key, T object) {
        this.key = key;
        this.object = object;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }

    public abstract void print();
}
