package com.thecop.rpgtest.mech.effect.instant;

import com.thecop.rpgtest.mech.effect.EffectTargetType;
import com.thecop.rpgtest.mech.effect.TargetableEffect;
import com.thecop.rpgtest.object.GameChar;

/**
 * Created by TheCop on 25.03.2015.
 */
public abstract class InstantEffect<T> extends TargetableEffect<T> {

    public InstantEffect(String name, String description, EffectTargetType targetType) {
        super(name, description, targetType);
    }

    public InstantEffect(InstantEffect other) {
        super(other);
    }

    public abstract void applyInstantEffect(GameChar c);

    @Override
    public String toString() {
        return "InstantEffect{" +
                "name=" + name +
                "}";
    }
}
