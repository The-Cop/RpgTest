package com.thecop.rpgtest.mech.effect.lasting.impl;

import com.thecop.rpgtest.mech.effect.SpellModifier;
import com.thecop.rpgtest.mech.effect.lasting.LastingEffect;
import com.thecop.rpgtest.mech.spell.Spell;

/**
 * Created by Admin on 01.04.2015.
 */
public class Silence extends LastingEffect<Silence> implements SpellModifier {
    public Silence(String name, String description, int length) {
        super(name, description, length);
    }

    private Silence(Silence other) {
        super(other);
    }


    @Override
    public Silence getCopy() {
        return new Silence(this);
    }

    @Override
    public Spell modifySpell(Spell spell) {
        return null;
    }
}
