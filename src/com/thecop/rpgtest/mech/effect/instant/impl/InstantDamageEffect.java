package com.thecop.rpgtest.mech.effect.instant.impl;

import com.thecop.rpgtest.mech.effect.GameCharEffect;
import com.thecop.rpgtest.mech.effect.instant.InstantEffect;
import com.thecop.rpgtest.mech.fight.Damage;
import com.thecop.rpgtest.mech.fight.DamageProcessor;
import com.thecop.rpgtest.mech.fight.DamageType;
import com.thecop.rpgtest.object.GameChar;

/**
 * Created by TheCop on 25.03.2015.
 */
public class InstantDamageEffect extends InstantEffect<InstantDamageEffect> implements GameCharEffect {
    private Damage damage;

    public InstantDamageEffect(String name, int damage, DamageType damageType) {
        super(name);
        this.damage = new Damage(damage,damageType);
    }

    private InstantDamageEffect(InstantDamageEffect other) {
        super(other);
        this.damage = other.getDamage();
    }


    @Override
    public void apply(GameChar c) {
        DamageProcessor.effectDamage(c,damage,name);
    }


    public Damage getDamage() {
        return new Damage(damage);
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
