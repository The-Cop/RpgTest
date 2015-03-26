package com.thecop.rpgtest.utils;

import com.thecop.rpgtest.mech.effect.lasting.LastingEffect;
import com.thecop.rpgtest.mech.spell.Spell;
import com.thecop.rpgtest.object.GameChar;
import com.thecop.rpgtest.object.PlayerChar;

import static com.thecop.rpgtest.Logger.print;

/**
 * Created by TheCop on 26.03.2015.
 */
public class Display {
    public static void playerInfo(PlayerChar p){
        print("Player: " + p.getName());
        print("Health: " + p.getHealthString());
        print("Mana: " + p.getManaString());
        print("Affected by:");
        for (LastingEffect lastingEffect : p.getLastingEffects()) {
            lastingEffect(lastingEffect);
        }
    }

    public static void monsterInfo(GameChar m){
        print("Monster: " + m.getName());
        print("Health: " + m.getHealthString());
        print("Mana: " + m.getManaString());
        print("Attack: "+m.getAttack().getAmount());
        print("Affected by:");
        for (LastingEffect lastingEffect : m.getLastingEffects()) {
            lastingEffect(lastingEffect);
        }
    }

    public static void spellCast(Spell spell, GameChar caster, GameChar target){
        print(caster.getName()+" casts " + spell.getName() + " on " + target.getName());
    }

    public static void lastingEffect(LastingEffect e){
        print("\t(" + e.getLength()+") "+e.getName()+" - "+e.getDescription());

    }

    public static void separator(){
        print("----------");
    }
    public static void separatorBig(){
        print("------------------------------");
    }
}
