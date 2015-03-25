package com.thecop.rpgtest.mech.effect.instant.impl;

import com.thecop.rpgtest.mech.effect.GameCharEffect;
import com.thecop.rpgtest.mech.effect.instant.InstantEffect;
import com.thecop.rpgtest.object.GameChar;

/**
 * Created by TheCop on 24.03.2015.
 */
public class Heal extends InstantEffect implements GameCharEffect {

    private int healStrength;

    public Heal(String name, int healStrength) {
        super(name);
        this.healStrength = healStrength;
    }

    @Override
    public void apply(GameChar c) {
        c.setHealth(c.getHealth()+healStrength);
    }


}
