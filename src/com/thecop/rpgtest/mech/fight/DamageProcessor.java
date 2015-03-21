package com.thecop.rpgtest.mech.fight;

import com.thecop.rpgtest.Logger;
import com.thecop.rpgtest.mech.iteraction.Attackable;
import com.thecop.rpgtest.mech.iteraction.Damageable;

import java.util.List;

/**
 * Created by Admin on 20.03.2015.
 */
public class DamageProcessor {
    public static void damage(Attackable attacker, Damageable target, Damage damage){
        Logger.log(attacker.getName() + " attacks " + target.getName() + " with damage " + damage.getAmount());
        int resultDamageAmount=damage.getAmount();
        //get resistance types for this damage type
        List<ResistanceType> resistanceTypes = damage.getResistanceTypes();

        //process damage through resistances
        for (ResistanceType resistanceType : resistanceTypes) {
            Resistance resistance = target.getResistance(resistanceType);
            if(resistance!=null){
                resultDamageAmount = calculateResistantDamage(resultDamageAmount,resistance.getStrength());
            }
        }
        //deal damage
        target.takeDamage(resultDamageAmount);
        Logger.log(target.getName() + " takes damage " + resultDamageAmount);
    }

    private static int calculateResistantDamage(int resultDamageAmount, int resistanceStrength) {
        return (int) Math.round(resultDamageAmount * getResistanceMultiplier(resistanceStrength));
    }

    private static double getResistanceMultiplier(int resistanceStrength){
        double defConst=0.04;
        double def = (resistanceStrength*defConst)/(1+resistanceStrength*defConst);
        return def;
    }
}
