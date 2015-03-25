package com.thecop.rpgtest.object;

import com.thecop.rpgtest.mech.fight.AttackRange;
import com.thecop.rpgtest.mech.fight.Damage;
import com.thecop.rpgtest.mech.fight.DamageProcessor;
import com.thecop.rpgtest.mech.iteraction.Fightable;
import com.thecop.rpgtest.mech.spell.Spell;

/**
 * Created by Admin on 19.03.2015.
 */
public class Monster extends GameChar{
    private int baseAttack;
    private AttackRange baseAttackRange;

    public Monster(String name, int health, int maxHealth, int mana, int maxMana, int baseAttack, AttackRange baseAttackRange) {
        super(name, health, maxHealth, mana, maxMana);
        this.baseAttack = baseAttack;
        this.baseAttackRange = baseAttackRange;
    }

    @Override
    public void attack(Fightable target) {
        Damage damage = new Damage(baseAttack);
        DamageProcessor.damage(this, target, damage, baseAttackRange);
    }

    @Override
    public void castSpell(Spell spell, Fightable target) {

    }
}
