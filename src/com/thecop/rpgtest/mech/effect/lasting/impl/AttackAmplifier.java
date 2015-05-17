package com.thecop.rpgtest.mech.effect.lasting.impl;

import com.thecop.rpgtest.mech.damage.Damage;
import com.thecop.rpgtest.mech.effect.AttackModifier;
import com.thecop.rpgtest.mech.effect.lasting.LastingEffect;

/**
 * Created by Admin on 01.04.2015.
 */
public class AttackAmplifier extends LastingEffect<AttackAmplifier> implements AttackModifier {
    double multiplier;

    public AttackAmplifier(String name, String description,int length,  double multiplier) {
        super(name,description, length);
        this.multiplier = multiplier;
    }


    private AttackAmplifier(AttackAmplifier other) {
        super(other);
        this.multiplier = other.multiplier;
    }

    @Override
    public Damage modifyAttack(Damage damage) {
        damage.setAmount((int)(damage.getAmount()*multiplier));
//        print("Damage is amplified by " + multiplier);
        return damage;
    }

    @Override
    public AttackAmplifier getCopy() {
        return new AttackAmplifier(this);
    }
}
