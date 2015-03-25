package com.thecop.rpgtest.mech.effect;

import com.thecop.rpgtest.mech.fight.Damage;

/**
 * Created by TheCop on 24.03.2015.
 */
public interface AttackModifier {
    public abstract Damage modifyAttack(Damage damage);
}
