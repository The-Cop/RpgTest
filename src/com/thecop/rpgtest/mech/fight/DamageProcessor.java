package com.thecop.rpgtest.mech.fight;

import com.thecop.rpgtest.Logger;
import com.thecop.rpgtest.mech.iteraction.Attackable;
import com.thecop.rpgtest.mech.iteraction.Damageable;
import com.thecop.rpgtest.utils.Util;

import java.util.List;

/**
 * Created by Admin on 20.03.2015.
 */
public class DamageProcessor {
    public static void damage(Attackable attacker, Damageable target, Damage damage, AttackType attackType) {
        Logger.log(attacker.getName() + " attacks " + target.getName() + " with damage " + damage.getAmount());
        int resultDamageAmount = damage.getAmount();
        //get resistance types for this damage type
        List<ResistanceType> resistanceTypes = damage.getResistanceTypes();

        //process damage through resistances
        for (ResistanceType resistanceType : resistanceTypes) {
            Resistance resistance = target.getResistance(resistanceType);
            if (resistance != null) {

                resultDamageAmount = calculateResistantDamage(resultDamageAmount, resistance.getStrength());
            }
        }
        //deal damage
        target.takeDamage(resultDamageAmount);
        Logger.log(target.getName() + " takes damage " + resultDamageAmount + " and has " + target.getHealthLeft() + " hp");
        //TODO implement attacktype - if spell or ranged then no counterattack can be done
    }

    private static int calculateResistantDamage(int resultDamageAmount, int resistanceStrength) {
        double resMult = getResistanceMultiplier(resistanceStrength);
        Logger.dlog("Resistance multiplier = " + Util.formatDouble(resMult,2));
        double damage = resultDamageAmount - resultDamageAmount * getResistanceMultiplier(resistanceStrength);
        Logger.dlog("Damage reduced to = " + Util.formatDouble(damage,2));
        return (int) Math.round(resultDamageAmount - resultDamageAmount * getResistanceMultiplier(resistanceStrength));
    }

    private static double getResistanceMultiplier(int resistanceStrength) {
        double defConst = 0.04;
        double def = (resistanceStrength * defConst) / (1 + resistanceStrength * defConst);
        return def;
    }
}
