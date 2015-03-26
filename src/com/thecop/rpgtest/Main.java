package com.thecop.rpgtest;

import com.thecop.rpgtest.mech.effect.instant.impl.InstantDamageEffect;
import com.thecop.rpgtest.mech.effect.instant.impl.InstantHeal;
import com.thecop.rpgtest.mech.effect.lasting.impl.LastingDamageEffect;
import com.thecop.rpgtest.mech.fight.AttackRange;
import com.thecop.rpgtest.mech.fight.DamageType;
import com.thecop.rpgtest.mech.fight.FightProcessor;
import com.thecop.rpgtest.mech.fight.Resistance;
import com.thecop.rpgtest.mech.spell.Spell;
import com.thecop.rpgtest.mech.spell.SpellTargetType;
import com.thecop.rpgtest.object.GameChar;
import com.thecop.rpgtest.object.Monster;
import com.thecop.rpgtest.object.PlayerChar;

public class Main {

    public static void main(String[] args) {
        playerFightTest();

    }

    private static PlayerChar getPlayer(){
        PlayerChar p = new PlayerChar("HERO",1000,250,AttackRange.MELEE,12);
        p.addSpell(new Spell("Lightning","l",3, SpellTargetType.ENEMY,new InstantDamageEffect("Electro damage effect",7,DamageType.ELECTRIC)));
        p.addSpell(new Spell("Pure force","p",7, SpellTargetType.ENEMY,new InstantDamageEffect("Pure force effect",7,DamageType.PURE)));
        p.addSpell(new Spell("Burn","b",4, SpellTargetType.ENEMY,new LastingDamageEffect("Burning",3,20,DamageType.FIRE,"Slowly burns your body")));
        p.addSpell(new Spell("Instant heal","i",10, SpellTargetType.FRIENDLY,new InstantHeal("Instant healing",10)));

        p.addResistance(new Resistance(DamageType.PHYSICAL,3));
        p.addResistance(new Resistance(DamageType.FIRE,2));
        p.addResistance(new Resistance(DamageType.ICE,1));
        p.addResistance(new Resistance(DamageType.MAGIC,0));

        return p;

    }

    private static Monster getMonster(){
        Monster p = new Monster("BIG RAT",1100,40,AttackRange.MELEE,10);
        p.addSpell(new Spell("Lightning","l",3, SpellTargetType.ENEMY,new InstantDamageEffect("Electro damage effect",7,DamageType.ELECTRIC)));
        p.addSpell(new Spell("Pure force","p",7, SpellTargetType.ENEMY,new InstantDamageEffect("Pure force effect",7,DamageType.PURE)));
        p.addSpell(new Spell("Burn","b",4, SpellTargetType.ENEMY,new LastingDamageEffect("Burning",3,2,DamageType.FIRE,"Slowly burns your body")));
        p.addSpell(new Spell("Instant heal","i",10, SpellTargetType.FRIENDLY,new InstantHeal("Instant healing",10)));

        p.addResistance(new Resistance(DamageType.PHYSICAL,0));
        p.addResistance(new Resistance(DamageType.FIRE,3));
        p.addResistance(new Resistance(DamageType.ICE,0));
        p.addResistance(new Resistance(DamageType.MAGIC,2));

        return p;
    }

    private static void playerFightTest() {
        PlayerChar p = getPlayer();
        Monster m = getMonster();
        FightProcessor fp = new FightProcessor(p,m);
        GameChar winner = fp.fight();

    }




}
