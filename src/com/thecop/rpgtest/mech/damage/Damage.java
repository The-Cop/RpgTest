package com.thecop.rpgtest.mech.damage;

import com.thecop.rpgtest.mech.Copyable;

/**
 * Created by Admin on 20.03.2015.
 */
public class Damage implements Copyable<Damage>{

    private int amount;
    DamageType damageType = DamageType.PHYSICAL;

    public Damage(int amount) {
        this.amount = amount;
    }

    public Damage(int amount, DamageType damageType) {
        this.amount = amount;
        this.damageType = damageType;
    }

    public Damage(Damage other) {
        this.amount = other.amount;
        this.damageType = other.damageType;
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

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Damage{" +
                "amount=" + amount +
                ", damageType=" + damageType +
                '}';
    }

    @Override
    public Damage getCopy() {
        return new Damage(this);
    }
}
