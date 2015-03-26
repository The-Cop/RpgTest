package com.thecop.rpgtest.mech.effect;

import com.thecop.rpgtest.mech.fight.Damage;

/**
 * Created by TheCop on 24.03.2015.
 */
public interface IncomingDamageEffect {
    public abstract Damage modifyIncomingDamage(Damage damage);
}
