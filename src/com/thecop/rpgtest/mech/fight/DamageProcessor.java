package com.thecop.rpgtest.mech.fight;

import com.thecop.rpgtest.Logger;
import com.thecop.rpgtest.mech.iteraction.Fightable;
import com.thecop.rpgtest.object.GameChar;
import com.thecop.rpgtest.utils.Util;

import static com.thecop.rpgtest.Logger.print;

/**
 * Created by Admin on 20.03.2015.
 */
public class DamageProcessor {
    public static void attackDamage(Fightable attacker, GameChar target, Damage damage, AttackRange attackRange) {
        print(attacker.getName() + " attacks " + target.getName() + " with damage " + damage.getAmount());
        calculateAndDealDamage(target, damage.getAmount(), damage.damageType);
        if(attackRange==AttackRange.MELEE) {
            //TODO implement attacktype - if spell or ranged then no counterattack can be done
        }
    }

    public static void effectDamage(GameChar c, Damage damage, String effectName){
        print(c.getName() + " is affected by " + effectName + " effect.");
        calculateAndDealDamage(c,damage.getAmount(),damage.getDamageType());
    }

    private static void calculateAndDealDamage(GameChar target, int damage,DamageType dType){
        int resultDamageAmount = damage;
        //process damage through resistances
        Resistance resistance = target.getResistance(dType);
        //check if attack type is of spell kind
        if(dType.isMagic()){
            Resistance magicResistance = target.getResistance(DamageType.MAGIC);
            if (resistance != null) {
                resultDamageAmount = calculateResistantDamage(resultDamageAmount, magicResistance.getStrength());
            }
        }
        //
        if (resistance != null) {
            resultDamageAmount = calculateResistantDamage(resultDamageAmount, resistance.getStrength());
        }

        //deal damage
        target.takeDamage(resultDamageAmount);
        print(target.getName() + " takes damage " + resultDamageAmount + " and has " + target.getHealthString() + " hp");
    }



    private static int calculateResistantDamage(int resultDamageAmount, int resistanceStrength) {
        double resMult = getResistanceMultiplier(resistanceStrength);
        Logger.dlog("Resistance multiplier = " + Util.formatDouble(resMult, 2));
        double damage = resultDamageAmount - resultDamageAmount * getResistanceMultiplier(resistanceStrength);
        Logger.dlog("Damage reduced to = " + Util.formatDouble(damage, 2));
        return (int) Math.round(resultDamageAmount - resultDamageAmount * getResistanceMultiplier(resistanceStrength));
    }

    private static double getResistanceMultiplier(int resistanceStrength) {
        double defConst = 0.04;
        double def = (resistanceStrength * defConst) / (1 + resistanceStrength * defConst);
        return def;
    }
}
