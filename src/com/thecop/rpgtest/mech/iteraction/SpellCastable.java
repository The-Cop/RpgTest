package com.thecop.rpgtest.mech.iteraction;

import com.thecop.rpgtest.mech.spell.Spell;

import java.util.List;

/**
 * Created by TheCop on 21.03.2015.
 */
public interface SpellCastable {
    public List<Spell> getSpells();
    public List<Spell> getUnmodifiedSpells();
    public void castSpell(Spell spell, Fightable target);
    public boolean canCastSpell(Spell spell);



}
