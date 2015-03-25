package com.thecop.rpgtest.mech.effect;

import com.thecop.rpgtest.mech.spell.Spell;

/**
 * Created by TheCop on 24.03.2015.
 */
public interface SpellModifier {
    public abstract Spell modifySpell(Spell spell);
}
