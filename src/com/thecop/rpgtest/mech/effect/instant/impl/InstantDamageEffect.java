package com.thecop.rpgtest.mech.effect.instant.impl;

import com.thecop.rpgtest.mech.damage.Damage;
import com.thecop.rpgtest.mech.effect.EffectTargetType;
import com.thecop.rpgtest.mech.effect.types.GameCharEffect;
import com.thecop.rpgtest.mech.effect.instant.InstantEffect;
import com.thecop.rpgtest.object.GameChar;

/**
 * Created by TheCop on 25.03.2015.
 */
public class InstantDamageEffect extends InstantEffect<InstantDamageEffect> implements GameCharEffect {
    private Damage damage;

    public InstantDamageEffect(String name, String description, EffectTargetType targetType, Damage damage) {
        super(name, description, targetType);
        this.damage = damage;
    }

    private InstantDamageEffect(InstantDamageEffect other) {
        super(other);
        this.damage = other.getDamage();
    }


    @Override
    public void apply(GameChar c) {
        c.takeEffectDamage(getDamage(), name);
    }


    public Damage getDamage() {
        return damage.getCopy();
    }

    @Override
    public void applyInstantEffect(GameChar c) {
        apply(c);
    }

    @Override
    public InstantDamageEffect getCopy() {
        return new InstantDamageEffect(this);
    }
}
