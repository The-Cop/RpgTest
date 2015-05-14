package com.thecop.rpgtest.mech.effect.lasting.impl;

import com.thecop.rpgtest.mech.effect.GameCharEffect;
import com.thecop.rpgtest.mech.effect.lasting.LastingEffect;
import com.thecop.rpgtest.mech.fight.Damage;
import com.thecop.rpgtest.mech.fight.DamageType;
import com.thecop.rpgtest.object.GameChar;

/**
 * Created by TheCop on 25.03.2015.
 */
public class LastingDamageEffect extends LastingEffect<LastingDamageEffect> implements GameCharEffect {
    Damage damage;

    public LastingDamageEffect(String name, int length, int damage, DamageType damageType, String desc) {
        super(name, length,desc);
        this.damage = new Damage(damage,damageType);
    }

    private LastingDamageEffect(LastingDamageEffect other) {
        super(other);
        this.damage = other.damage;
    }


    @Override
    public void apply(GameChar c) {
        c.takeEffectDamage(getDamage(),name);
    }

    public Damage getDamage() {
        return new Damage(damage);
    }

    @Override
    public LastingDamageEffect getCopy() {
        return new LastingDamageEffect(this);
    }
}
