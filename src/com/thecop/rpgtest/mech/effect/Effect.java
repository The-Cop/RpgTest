package com.thecop.rpgtest.mech.effect;


/**
 * Created by TheCop on 24.03.2015.
 */
public abstract class Effect {
    protected String name;


    public Effect(String name) {
        this.name=name;
    }

    public String getName() {
        return name;
    }

}
