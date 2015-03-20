package com.thecop.rpgtest.mech.fight;

import com.thecop.rpgtest.mech.iteraction.Attackable;
import com.thecop.rpgtest.mech.iteraction.Damageable;

import java.util.List;

/**
 * Created by Admin on 20.03.2015.
 */
public class DamageProcessor {
    public static void damage(Attackable attacker, Damageable target, Damage damage){
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
    }

    private static int calculateResistantDamage(int resultDamageAmount, int strength) {
        //TODO implement
        return resultDamageAmount;
    }
}
