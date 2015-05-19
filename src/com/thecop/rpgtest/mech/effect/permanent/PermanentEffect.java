package com.thecop.rpgtest.mech.effect.permanent;

import com.thecop.rpgtest.mech.effect.Effect;

/**
 * Created by TheCop on 19.05.2015.
 */
public abstract class PermanentEffect<T> extends Effect<T> {
    public PermanentEffect(String name, String description) {
        super(name, description);
    }

    public PermanentEffect(Effect other) {
        super(other);
    }
}
