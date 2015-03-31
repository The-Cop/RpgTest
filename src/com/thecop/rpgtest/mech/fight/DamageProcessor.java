package com.thecop.rpgtest.mech.fight;

import com.thecop.rpgtest.Logger;
import com.thecop.rpgtest.mech.effect.IncomingDamageEffect;
import com.thecop.rpgtest.mech.effect.lasting.LastingEffect;
import com.thecop.rpgtest.object.GameChar;
import com.thecop.rpgtest.utils.Util;

import static com.thecop.rpgtest.Logger.print;

/**
 * Created by Admin on 20.03.2015.
 */
public class DamageProcessor {
    public static void attackDamage(GameChar attacker, GameChar target, Damage damage, AttackRange attackRange) {
        print(attacker.getName() + " attacks " + target.getName() + " with damage " + damage.getAmount());
        calculateAndDealDamage(target, damage);
        if(attackRange==AttackRange.MELEE) {
            //TODO implement attacktype - if spell or ranged then no counterattack can be done
        }
    }

    public static void effectDamage(GameChar c, Damage damage, String effectName){
        print(c.getName() + " is affected by " + effectName + " effect.");
        calculateAndDealDamage(c,damage);
    }

    private static void calculateAndDealDamage(GameChar target, Damage damage){
        int resultDamageAmount = damage.getAmount();
        //process damage through resistances
        Resistance resistance = target.getResistance(damage.getDamageType());
        //check if attack type is of spell kind
        if(damage.getDamageType().isMagic()){
            Resistance magicResistance = target.getResistance(DamageType.MAGIC);
            if (resistance != null) {
                resultDamageAmount = calculateResistantDamage(resultDamageAmount, magicResistance);
            }
        }
        //other resistances
        if (resistance != null) {
            resultDamageAmount = calculateResistantDamage(resultDamageAmount, resistance);
        }

        damage.setAmount(resultDamageAmount);

        //lasting effects
        for (LastingEffect lastingEffect : target.getLastingEffects()) {
            if(lastingEffect instanceof IncomingDamageEffect){
                IncomingDamageEffect idf = (IncomingDamageEffect)lastingEffect;
                damage = idf.modifyIncomingDamage(damage);
            }
        }

        //deal damage
        target.takeDamage(damage.getAmount());
        print(target.getName() + " takes damage " + damage.getAmount() + " and has " + target.getHealthString() + " hp");
    }



    private static int calculateResistantDamage(int resultDamageAmount, Resistance resistance) {
        double resMult = getResistanceMultiplier(resistance.getStrength());
        Logger.dlog(resistance.getType() + " resistance multiplier = " + Util.formatDouble(resMult, 2));
        double damage = resultDamageAmount - resultDamageAmount * getResistanceMultiplier(resistance.getStrength());
        Logger.dlog("Damage reduced to = " + Util.formatDouble(damage, 2));
        return (int) Math.round(resultDamageAmount - resultDamageAmount * getResistanceMultiplier(resistance.getStrength()));
    }

    private static double getResistanceMultiplier(int resistanceStrength) {
        double defConst = 0.04;
        double def = (resistanceStrength * defConst) / (1 + resistanceStrength * defConst);
        return def;
    }
}
