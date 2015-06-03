package com.thecop.rpgtest.mech.effect.types;

import com.thecop.rpgtest.mech.rpg.Stats;

/**
 * Created by Admin on 03.06.2015.
 */
public interface StatsModifier {
    public abstract Stats modifyStats(Stats stats);
}
