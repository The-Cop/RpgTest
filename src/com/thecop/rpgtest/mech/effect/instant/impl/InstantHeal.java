package com.thecop.rpgtest.mech.effect.instant.impl;

import com.thecop.rpgtest.mech.effect.GameCharEffect;
import com.thecop.rpgtest.mech.effect.instant.InstantEffect;
import com.thecop.rpgtest.object.GameChar;

/**
 * Created by TheCop on 24.03.2015.
 */
public class InstantHeal extends InstantEffect<InstantHeal> implements GameCharEffect {

    private int healStrength;

    public InstantHeal(String name, String description, int healStrength) {
        super(name, description);
        this.healStrength = healStrength;
    }

    private InstantHeal(InstantHeal other) {
        super(other);
        this.healStrength = other.healStrength;
    }


    @Override
    public void apply(GameChar c) {
        c.setHealth(c.getHealth() + healStrength);
    }


    @Override
    public void applyInstantEffect(GameChar c) {
        apply(c);
    }

    @Override
    public InstantHeal getCopy() {
        return new InstantHeal(this);
    }
}
