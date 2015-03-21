package com.thecop.rpgtest.object;

import com.thecop.rpgtest.mech.fight.*;
import com.thecop.rpgtest.mech.iteraction.Attackable;
import com.thecop.rpgtest.mech.iteraction.Damageable;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Admin on 19.03.2015.
 */
public class Monster implements Attackable, Damageable {
    private int health;
    private int maxHealth;
    private int baseAttack;
    private String name;
    private Map<ResistanceType, Resistance> resistances;

    public Monster(String name, int health, int baseAttack) {
        this.name = name;
        this.health = health;
        this.maxHealth = health;
        this.baseAttack = baseAttack;
        resistances = new HashMap<>();
    }

    public int getHealth() {
        return health;
    }

    public int getBaseAttack() {
        return baseAttack;
    }

    public void setResistance(Resistance resistance) {
        resistances.put(resistance.getType(), resistance);
    }

    @Override
    public void attack(Damageable target, AttackType type) {

        Damage damage = new Damage(DamageType.PHYSICAL, baseAttack);
        DamageProcessor.damage(this, target, damage, type);
    }

    @Override
    public void takeDamage(int damageAmount) {
        health = health - damageAmount;
        if (health < 0) health = 0;
    }

    @Override
    public Resistance getResistance(ResistanceType type) {
        return resistances.get(type);
    }

    @Override
    public int getHealthLeft() {
        return getHealth();
    }

    @Override
    public String getHealthString() {
        return health + "/" + maxHealth;
    }

    @Override
    public boolean isAlive() {
        return health > 0;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Monster{" +
                "health=" + health +
                ", baseAttack=" + baseAttack +
                ", name='" + name + '\'' +
                ", resistances=" + resistances +
                '}';
    }
}
