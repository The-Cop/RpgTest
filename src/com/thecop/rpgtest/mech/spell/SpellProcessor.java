package com.thecop.rpgtest.mech.spell;

import com.thecop.rpgtest.mech.effect.Effect;
import com.thecop.rpgtest.mech.effect.instant.InstantEffect;
import com.thecop.rpgtest.mech.effect.lasting.LastingEffect;
import com.thecop.rpgtest.object.GameChar;

import static com.thecop.rpgtest.Logger.dlog;
import static com.thecop.rpgtest.Logger.print;

/**
 * Created by TheCop on 23.03.2015.
 */
public class SpellProcessor {
    public static void applySpell(Spell spell, GameChar caster, GameChar target){
        //TODO implement AOE spelling
        GameChar spellTarget;
        if(spell.getTargetType()== SpellTargetType.FRIENDLY){
            spellTarget=caster;
        }
        else{
            spellTarget=target;
        }
        dlog("Processing spell " + spell.toString());
        for (Effect effect : spell.getEffects()) {
            if(effect instanceof LastingEffect){
                spellTarget.addEffect((LastingEffect)effect);
                print(spellTarget.getName() + " is now affected by " + effect.getName());
            }
            else if(effect instanceof InstantEffect){
                InstantEffect ie = (InstantEffect)effect;
                dlog("Applying instant effect "+ie.getName()+" to "+spellTarget.getName());
                ie.applyInstantEffect(spellTarget);
            }
        }
    }
}
