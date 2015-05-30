package com.thecop.rpgtest.mech.effect.lasting.impl;

import com.thecop.rpgtest.mech.damage.Damage;
import com.thecop.rpgtest.mech.effect.EffectTargetType;
import com.thecop.rpgtest.mech.effect.lasting.LastingEffect;
import com.thecop.rpgtest.mech.effect.types.IncomingDamageEffect;

import static com.thecop.rpgtest.Logger.print;

/**
 * Created by TheCop on 25.03.2015.
 */
public class LastingAllDamageReduceEffect extends LastingEffect<LastingAllDamageReduceEffect> implements IncomingDamageEffect {
    private int damageReduce;

    public LastingAllDamageReduceEffect(String name, String description, EffectTargetType targetType, int length, int damageReduce) {
        super(name, description, targetType, length);
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
