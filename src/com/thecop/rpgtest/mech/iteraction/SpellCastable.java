package com.thecop.rpgtest.mech.iteraction;

import com.thecop.rpgtest.mech.magic.Spell;

import java.util.List;

/**
 * Created by TheCop on 21.03.2015.
 */
public interface SpellCastable {
    public List<Spell> getSpells();
    public void castSpell(Spell spell, Damageable target);
    public boolean canCastSpell(Spell spell);

}
