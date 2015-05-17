package com.thecop.rpgtest.mech.effect;


import com.thecop.rpgtest.mech.Copyable;

/**
 * Created by TheCop on 24.03.2015.
 */
public abstract class Effect<T> implements Copyable<T> {
    protected String name;
    protected String description;

    public Effect(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Effect(Effect other) {
        this.name = other.name;
        this.description = other.description;
    }


    public  String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Effect{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
