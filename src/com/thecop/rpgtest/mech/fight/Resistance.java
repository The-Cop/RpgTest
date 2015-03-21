package com.thecop.rpgtest.mech.fight;

/**
 * Created by Admin on 20.03.2015.
 */
public class Resistance {
    private ResistanceType type;
    private int strength;

    public Resistance(ResistanceType type, int strength) {
        this.type = type;
        this.strength = strength;
    }

    public ResistanceType getType() {
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
