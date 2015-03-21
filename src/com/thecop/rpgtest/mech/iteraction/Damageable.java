package com.thecop.rpgtest.mech.iteraction;

import com.thecop.rpgtest.mech.Named;
import com.thecop.rpgtest.mech.fight.Resistance;
import com.thecop.rpgtest.mech.fight.ResistanceType;

/**
 * Created by Admin on 20.03.2015.
 */
public interface Damageable extends Named {
    public void takeDamage(int damageAmount);

    public Resistance getResistance(ResistanceType type);

    public int getHealthLeft();

    public String getHealthString();

    public boolean isAlive();
}
