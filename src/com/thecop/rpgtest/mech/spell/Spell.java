package com.thecop.rpgtest.mech.spell;

import com.thecop.rpgtest.mech.fight.DamageType;

/**
 * Created by TheCop on 21.03.2015.
 */
public class Spell {
    private String name;
    private char controlChar;
    private int manaCost;
    private int baseDamage;
    private DamageType damageType;
    private SpellTargetType targetType;


    public Spell(String name, char controlChar, int manaCost, int baseDamage, DamageType damageType, SpellTargetType targetType) {
        this.name = name;
        this.controlChar = controlChar;
        this.manaCost = manaCost;
        this.baseDamage = baseDamage;
        this.damageType = damageType;
        this.targetType = targetType;
    }

    public Spell(Spell other) {
        this.name = other.name;
        this.controlChar = other.controlChar;
        this.manaCost = other.manaCost;
        this.baseDamage = other.baseDamage;
        this.damageType = other.damageType;
        this.targetType = other.targetType;
    }


    public int getManaCost() {
        return manaCost;
    }

    public char getControlChar() {
        return controlChar;
    }

    public String getName() {
        return name;
    }

    public int getBaseDamage() {
        return baseDamage;
    }

    @Override
    public String toString() {
        return "Spell{" +
                "name='" + name + '\'' +
                ", controlChar=" + controlChar +
                ", manaCost=" + manaCost +
                ", baseDamage=" + baseDamage +
                ", damageType=" + damageType +
                '}';
    }

    public DamageType getDamageType() {
        return damageType;
    }

    public SpellTargetType getTargetType() {
        return targetType;
    }
}
