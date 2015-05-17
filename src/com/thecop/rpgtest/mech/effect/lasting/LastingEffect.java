package com.thecop.rpgtest.mech.effect.lasting;

import com.thecop.rpgtest.mech.effect.Effect;

/**
 * Created by TheCop on 25.03.2015.
 */
public abstract class LastingEffect<T> extends Effect<T> {
    protected int length;


    public LastingEffect(String name, String description, int length) {
        super(name, description);
        this.length = length;
    }

    public LastingEffect(LastingEffect other) {
        super(other);
        this.length = other.length;
    }

    public int getLength() {
        return length;
    }

    public void tick() {
        if (length == -1) {
            return;
        }
        length = length - 1;
    }

    public boolean ended() {
        return length == 0;
    }

}
