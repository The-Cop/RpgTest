package com.thecop.rpgtest.mech.iteraction;

import com.thecop.rpgtest.mech.Named;
import com.thecop.rpgtest.mech.fight.DamageType;
import com.thecop.rpgtest.mech.fight.Resistance;

/**
 * Created by Admin on 20.03.2015.
 */
public interface Fightable extends Named {
    public void takeDamage(int damageAmount);

    public Resistance getResistance(DamageType type);

    public int getHealthLeft();

    public String getHealthString();

    public boolean isAlive();

    public void attack(Fightable target);

    public boolean canAttack();
}
