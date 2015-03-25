package com.thecop.rpgtest.mech.fight;

/**
 * Created by Admin on 20.03.2015.
 */
public class Damage {

    private int amount;
    DamageType damageType=DamageType.PHYSICAL;

    public Damage(int amount) {
        this.amount = amount;
    }

    public Damage(int amount, DamageType damageType) {
        this.amount = amount;
        this.damageType = damageType;
    }

    public int getAmount() {
        return amount;
    }

    public DamageType getDamageType() {
        return damageType;
    }

    public void setDamageType(DamageType damageType) {
        this.damageType = damageType;
    }
}
