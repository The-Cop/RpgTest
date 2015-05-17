package com.thecop.rpgtest.mech.effect.instant;

import com.thecop.rpgtest.mech.effect.Effect;
import com.thecop.rpgtest.object.GameChar;

/**
 * Created by TheCop on 25.03.2015.
 */
public abstract class InstantEffect<T> extends Effect<T> {

    public InstantEffect(String name, String description) {
        super(name, description);
    }

    protected InstantEffect(InstantEffect other) {
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
