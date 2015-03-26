package com.thecop.rpgtest.object;

import com.thecop.rpgtest.mech.fight.AttackRange;
import com.thecop.rpgtest.mech.fight.Damage;

/**
 * Created by Admin on 19.03.2015.
 */
public class Monster extends GameChar{
    private int baseAttack;

    public Monster(String name, int health, int maxHealth, int mana, int maxMana, AttackRange baseAttackRange, int baseAttack) {
        super(name, health, maxHealth, mana, maxMana, baseAttackRange);
        this.baseAttack = baseAttack;
    }

    public Monster(String name, int health, int mana, AttackRange baseAttackRange, int baseAttack) {
        super(name, health, mana, baseAttackRange);
        this.baseAttack = baseAttack;
    }

    @Override
    protected Damage getBaseUnmodifiedAttack() {
        return new Damage(baseAttack);
    }
}
