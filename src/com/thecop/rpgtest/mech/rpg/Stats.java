package com.thecop.rpgtest.mech.rpg;

import com.thecop.rpgtest.mech.Copyable;

/**
 * Created by Admin on 03.06.2015.
 */
public class Stats implements Copyable<Stats> {
    private static final int MIN_SPEED = 1;
    private static final int MIN_EVASION = 0;
    private static final int MIN_MANA = 0;
    private static final int MIN_HEALTH = 1;
    private static final double MAX_INTELLIGENCE_HEALTH_REDUCE_MULTIPLIER = 0.5;

    private int baseHealth;
    private int baseMana;
    private int baseSpeed;
    private int baseEvasion;
    //health
    //mana
    //speed
    //evasion
    //physical resistance
    //magical resistance

    //+++ melee attack
    //+ health
    // - speed
    // - mana
    private int strength;

    //+++ ranged attack
    //++ speed
    //+ evasion
    // - magical resistance
    // - mana pool
    private int agility;

    //+++ spell power
    //+++ mana pool
    //+ magical resistance
    //- physical resistence
    //- health (may be in %)
    private int intelligence;

    //+++ health
    //++ physical resistance
    //+ magic resistance
    //- speed
    //- evasion
    private int endurance;

    public Stats(int baseHealth, int baseMana, int baseSpeed, int baseEvasion, int strength, int agility, int intelligence, int endurance) {
        this.baseHealth = baseHealth;
        this.baseMana = baseMana;
        this.baseSpeed = baseSpeed;
        this.baseEvasion = baseEvasion;
        this.strength = strength;
        this.agility = agility;
        this.intelligence = intelligence;
        this.endurance = endurance;
    }

    public Stats(Stats other) {
        this.baseHealth = other.baseHealth;
        this.baseMana = other.baseMana;
        this.baseSpeed = other.baseSpeed;
        this.baseEvasion = other.baseEvasion;
        this.strength = other.strength;
        this.agility = other.agility;
        this.intelligence = other.intelligence;
        this.endurance = other.endurance;
    }

    public int getHealth() {
        int calculatedHealth = baseHealth;
        calculatedHealth = calculatedHealth + (int) (strength * 2);
        calculatedHealth = calculatedHealth + (int) (endurance * 5);

        double intelligencePenaltyMultiplier = (1d - intelligence * 0.01d); // 1 intelligence = 1% reduce hp
        intelligencePenaltyMultiplier =
                intelligencePenaltyMultiplier < MAX_INTELLIGENCE_HEALTH_REDUCE_MULTIPLIER ?
                        intelligencePenaltyMultiplier : MAX_INTELLIGENCE_HEALTH_REDUCE_MULTIPLIER;

        calculatedHealth = (int) (calculatedHealth * intelligencePenaltyMultiplier);

        return calculatedHealth > MIN_HEALTH ? calculatedHealth : MIN_HEALTH;
    }

    public int getMana() {
        int calculatedMana = baseMana;
        calculatedMana = calculatedMana + (int) (intelligence * 3);
        calculatedMana = calculatedMana - (int) (strength * 1d);
        calculatedMana = calculatedMana - (int) (agility * 1d);
        return calculatedMana > MIN_SPEED ? calculatedMana : MIN_SPEED;
    }

    public int getSpeed() {
        int calculatedSpeed = baseSpeed;
        calculatedSpeed = calculatedSpeed + (int) (agility * 0.2d);
        calculatedSpeed = calculatedSpeed - (int) (strength * 0.1d);
        calculatedSpeed = calculatedSpeed - (int) (endurance * 0.1d);
        return calculatedSpeed > MIN_SPEED ? calculatedSpeed : MIN_SPEED;
    }

    public int getEvasion() {
        int calculatedEvasion = baseEvasion;
        calculatedEvasion = calculatedEvasion + (int) (agility * 0.2d);
        calculatedEvasion = calculatedEvasion - (int) (endurance * 0.1d);
        return calculatedEvasion > MIN_EVASION ? calculatedEvasion : MIN_EVASION;
    }


    public int getStrength() {
        return strength;
    }

    public int getAgility() {
        return agility;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public int getEndurance() {
        return endurance;
    }



    @Override
    public Stats getCopy() {
        return new Stats(this);
    }
}
