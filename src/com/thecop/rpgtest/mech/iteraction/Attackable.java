package com.thecop.rpgtest.mech.iteraction;

import com.thecop.rpgtest.mech.Named;
import com.thecop.rpgtest.mech.fight.AttackType;

/**
 * Created by Admin on 19.03.2015.
 */
public interface Attackable extends Named{
    public void attack(Damageable target,AttackType type);
}
