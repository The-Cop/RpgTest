package com.thecop.rpgtest.mech.fight;

import com.thecop.rpgtest.mech.effect.Effect;
import com.thecop.rpgtest.mech.effect.instant.InstantEffect;
import com.thecop.rpgtest.mech.effect.lasting.LastingEffect;
import com.thecop.rpgtest.mech.spell.Spell;
import com.thecop.rpgtest.object.GameChar;

import static com.thecop.rpgtest.Logger.print;

/**
 * Created by TheCop on 23.03.2015.
 */
public class SpellProcessor {
    public static void applySpell(Spell spell, GameChar caster, GameChar target){

        Effect effect = spell.getEffect();
        if(effect instanceof LastingEffect){
            target.addEffect((LastingEffect)effect);
            print(target.getName() + " is now affected by " + effect.getName());
        }
        else if(effect instanceof InstantEffect){
            InstantEffect ie = (InstantEffect)effect;
            ie.applyInstantEffect(target);
        }
    }
}
