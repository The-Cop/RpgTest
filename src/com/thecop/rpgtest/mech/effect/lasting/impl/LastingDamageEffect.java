package com.thecop.rpgtest.mech.effect.lasting.impl;

import com.thecop.rpgtest.mech.damage.Damage;
import com.thecop.rpgtest.mech.effect.EffectTargetType;
import com.thecop.rpgtest.mech.effect.lasting.LastingEffect;
import com.thecop.rpgtest.mech.effect.types.GameCharEffect;
import com.thecop.rpgtest.object.GameChar;

/**
 * Created by TheCop on 25.03.2015.
 */
public class LastingDamageEffect extends LastingEffect<LastingDamageEffect> implements GameCharEffect {
    private Damage damage;

    public LastingDamageEffect(String name, String description, EffectTargetType targetType, int length, Damage damage) {
        super(name, description, targetType, length);
        this.damage = damage;
    }

    private LastingDamageEffect(LastingDamageEffect other) {
        super(other);
        this.damage = other.damage;
    }


    @Override
    public void apply(GameChar c) {
        c.takeEffectDamage(getDamage(), name);
    }

    public Damage getDamage() {
        return damage.getCopy();
    }

    @Override
    public LastingDamageEffect getCopy() {
        return new LastingDamageEffect(this);
    }
}
