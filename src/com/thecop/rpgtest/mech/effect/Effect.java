package com.thecop.rpgtest.mech.effect;


/**
 * Created by TheCop on 24.03.2015.
 */
public abstract class Effect<T> {
    protected String name;

    public Effect(String name) {
        this.name=name;
    }

    protected Effect(Effect other) {
        this.name = other.name;
    }


    public String getName() {
        return name;
    }

    public abstract T getCopy();

}
