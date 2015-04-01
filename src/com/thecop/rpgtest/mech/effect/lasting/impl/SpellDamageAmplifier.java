package com.thecop.rpgtest.mech.effect.lasting.impl;

import com.thecop.rpgtest.mech.effect.SpellModifier;
import com.thecop.rpgtest.mech.effect.lasting.LastingEffect;
import com.thecop.rpgtest.mech.spell.Spell;

/**
 * Created by Admin on 01.04.2015.
 */
public class SpellDamageAmplifier extends LastingEffect<SpellDamageAmplifier> implements SpellModifier {
    public SpellDamageAmplifier(String name, int length, String description) {
        super(name, length, description);
    }

    private SpellDamageAmplifier(SpellDamageAmplifier other) {
        super(other);
    }


    @Override
    public SpellDamageAmplifier getCopy() {
        return new SpellDamageAmplifier(this);
    }

    @Override
    public Spell modifySpell(Spell spell) {
        //TODO implement
        return null;
    }
}
