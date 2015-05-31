package com.thecop.rpgtest.display.option;

import com.thecop.rpgtest.Logger;
import com.thecop.rpgtest.display.Display;

/**
 * Created by TheCop on 30.05.2015.
 */
public class Option<T> {
    private String key;
    private T object;
    private String printInfo;

    public Option(String key,String printInfo, T object) {
        this.key = key;
        this.printInfo=printInfo;
        this.object = object;
        if(key.equalsIgnoreCase(Display.BACK_KEY)) {
            throw new IllegalArgumentException("Option key can not be \""+Display.BACK_KEY+"\"");
        }
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

    public void print(){
        Logger.print(key + " - " + printInfo);
    }
}
