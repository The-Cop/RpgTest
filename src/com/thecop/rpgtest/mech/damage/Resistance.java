package com.thecop.rpgtest.mech.damage;

/**
 * Created by Admin on 20.03.2015.
 */
public class Resistance {
    private DamageType type;
    private int strength;

    public Resistance(DamageType type, int strength) {
        if(type==DamageType.PURE){
            throw new IllegalArgumentException("NO RESISTANCE FOR PURE DAMAGE!!!!1111");
        }
        this.type = type;
        this.strength = strength;
    }

    public DamageType getType() {
        return type;
    }

    public int getStrength() {
        return strength;
    }

    @Override
    public String toString() {
        return "Resistance{" +
                "type=" + type +
                ", strength=" + strength +
                '}';
    }
}
