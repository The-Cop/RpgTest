package com.thecop.rpgtest.mech.effect.item;

import com.thecop.rpgtest.mech.effect.Effect;

/**
 * Created by Admin on 31.03.2015.
 */
public abstract class ItemEffect<T> extends Effect<T> {

    public ItemEffect(String name, String description) {
        super(name, description);
    }

    public ItemEffect(ItemEffect other) {
        super(other);
    }

    public String getDescription() {
        return description;
    }
}
