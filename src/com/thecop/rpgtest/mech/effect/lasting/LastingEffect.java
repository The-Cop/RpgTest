package com.thecop.rpgtest.mech.effect.lasting;

import com.thecop.rpgtest.mech.effect.EffectTargetType;
import com.thecop.rpgtest.mech.effect.TargetableEffect;

/**
 * Created by TheCop on 25.03.2015.
 */
public abstract class LastingEffect<T> extends TargetableEffect<T> {
    protected int length;

    public LastingEffect(String name, String description, EffectTargetType targetType, int length) {
        super(name, description, targetType);
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
