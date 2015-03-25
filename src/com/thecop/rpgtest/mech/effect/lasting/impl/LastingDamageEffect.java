package com.thecop.rpgtest.mech.effect.lasting.impl;

import com.thecop.rpgtest.mech.effect.GameCharEffect;
import com.thecop.rpgtest.mech.effect.lasting.LastingEffect;
import com.thecop.rpgtest.mech.fight.Damage;
import com.thecop.rpgtest.mech.fight.DamageProcessor;
import com.thecop.rpgtest.mech.fight.DamageType;
import com.thecop.rpgtest.object.GameChar;

/**
 * Created by TheCop on 25.03.2015.
 */
public class LastingDamageEffect extends LastingEffect implements GameCharEffect {
    Damage damage;

    public LastingDamageEffect(String name, int length, int damage, DamageType damageType) {
        super(name, length);
        this.damage = new Damage(damage,damageType);
    }

    @Override
    public void apply(GameChar c) {
        DamageProcessor.effectDamage(c,damage,name);
    }
}
