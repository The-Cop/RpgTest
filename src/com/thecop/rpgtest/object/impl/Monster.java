package com.thecop.rpgtest.object.impl;

import com.thecop.rpgtest.mech.damage.AttackRange;
import com.thecop.rpgtest.mech.damage.Damage;
import com.thecop.rpgtest.mech.damage.DamageType;
import com.thecop.rpgtest.object.GameChar;

/**
 * Created by Admin on 19.03.2015.
 */
public class Monster extends GameChar {
    private int baseAttackDamageAmount;
    private DamageType baseDamageType;

    public Monster(String name, int health, int maxHealth, int mana, int maxMana, AttackRange baseAttackRange, int baseAttackDamageAmount, DamageType baseDamageType) {
        super(name, health, maxHealth, mana, maxMana, baseAttackRange);
        this.baseAttackDamageAmount = baseAttackDamageAmount;
        this.baseDamageType = baseDamageType;
    }

    public Monster(String name, int health, int maxHealth, int mana, int maxMana, AttackRange baseAttackRange, int baseAttackDamageAmount) {
        this(name, health, maxHealth, mana, maxMana, baseAttackRange, baseAttackDamageAmount, DamageType.PHYSICAL);
    }


    public Monster(String name, int health, int mana, AttackRange baseAttackRange, int baseAttackDamageAmount, DamageType baseDamageType) {
        super(name, health, mana, baseAttackRange);
        this.baseAttackDamageAmount = baseAttackDamageAmount;
        this.baseDamageType = baseDamageType;
    }

    public Monster(String name, int health, int mana, AttackRange baseAttackRange, int baseAttackDamageAmount) {
        this(name, health, mana, baseAttackRange, baseAttackDamageAmount, DamageType.PHYSICAL);
    }

    @Override
    protected Damage getBaseUnmodifiedAttack() {
        return new Damage(baseAttackDamageAmount, baseDamageType);
    }
}
