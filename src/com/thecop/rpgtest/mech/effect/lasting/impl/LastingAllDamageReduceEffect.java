package com.thecop.rpgtest.mech.effect.lasting.impl;

import com.thecop.rpgtest.mech.damage.Damage;
import com.thecop.rpgtest.mech.effect.IncomingDamageEffect;
import com.thecop.rpgtest.mech.effect.lasting.LastingEffect;

import static com.thecop.rpgtest.Logger.print;

/**
 * Created by TheCop on 25.03.2015.
 */
public class LastingAllDamageReduceEffect extends LastingEffect<LastingAllDamageReduceEffect> implements IncomingDamageEffect {
    int damageReduce;

    public LastingAllDamageReduceEffect(String name, String description, int length, int damageReduce) {
        super(name, description, length);
        this.damageReduce = damageReduce;
    }

    private LastingAllDamageReduceEffect(LastingAllDamageReduceEffect other) {
        super(other);
        this.damageReduce = other.damageReduce;
    }


    public int getDamageReduce() {
        return damageReduce;
    }

    @Override
    public LastingAllDamageReduceEffect getCopy() {
        return new LastingAllDamageReduceEffect(this);
    }

    @Override
    public Damage modifyIncomingDamage(Damage damage) {
        int newAmount = damage.getAmount() - damageReduce;
        if (newAmount < 0) {
            newAmount = 0;
        }
        print(getName() + " reduces damage by " + damageReduce + " to " + newAmount);
        damage.setAmount(newAmount);
        return damage;
    }
}
