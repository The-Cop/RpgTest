package com.thecop.rpgtest.mech.effect;


import com.thecop.rpgtest.mech.Copyable;

/**
 * Created by TheCop on 24.03.2015.
 */
public abstract class Effect<T> implements Copyable<T> {
    protected String name;

    public Effect(String name) {
        this.name=name;
    }

    protected Effect(Effect other) {
        this.name = other.name;
    }


    public  String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Effect{" +
                "name='" + name + '\'' +
                '}';
    }
}
