package com.thecop.rpgtest;

import com.thecop.rpgtest.mech.damage.AttackRange;
import com.thecop.rpgtest.mech.damage.Damage;
import com.thecop.rpgtest.mech.damage.DamageType;
import com.thecop.rpgtest.mech.damage.Resistance;
import com.thecop.rpgtest.mech.effect.EffectTargetType;
import com.thecop.rpgtest.mech.effect.TargetableEffect;
import com.thecop.rpgtest.mech.effect.instant.impl.InstantDamageEffect;
import com.thecop.rpgtest.mech.effect.lasting.impl.LowerResistance;
import com.thecop.rpgtest.mech.fight.Fight;
import com.thecop.rpgtest.mech.spell.Spell;
import com.thecop.rpgtest.object.impl.Monster;
import com.thecop.rpgtest.object.impl.MonsterParty;
import com.thecop.rpgtest.object.impl.PlayerChar;
import com.thecop.rpgtest.object.impl.PlayerParty;

import static com.thecop.rpgtest.Logger.print;

public class Main {

    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        sb.append("I am some spell, and this are my effects:");
        sb.append("\n\t").append("effect 1");
        sb.append("\n\t").append("effect 2");
        sb.append("\n\t").append("effect 3");

        print("spellkey - " + sb.toString());

        playerFightTest();
//        String[] columnNames = {
//                "First Name",
//                "Last Name",
//                "Sport",
//                "# of Years",
//                "Vegetarian"};
//
//
//        Object[][] data = {
//                {"Kathy", null,
//                        "Snowboarding Snowboarding Snowboarding Snowboarding Snowboarding Snowboarding Snowboarding Snowboarding Snowboarding Snowboarding Snowboarding ", new Integer(5), new Boolean(false)},
//                {"John", "Doe",
//                        "Rowing", new Integer(3), new Boolean(true)},
//                {"Sue", "Black",
//                        "Knitting", new Integer(2), new Boolean(false)},
//                {"Jane", "White",
//                        "Speed reading", new Integer(20), new Boolean(true)},
//                {"Joe", "Brown",
//                        "Pool", new Integer(10), new Boolean(false)}
//        };
//        TextTable tt = new TextTable(columnNames, data);
//        tt.printTable();
//        tt.printTable();
    }

    private static PlayerChar getPlayer(int number) {
        PlayerChar p = new PlayerChar("HERO" + number, 1000, 250, AttackRange.MELEE, 15);
        p.addSpell(new Spell(
                        "Lower physical resistances",
                        "lpr",
                        10,
                        new TargetableEffect[]{
                                new LowerResistance("Lowers Physical resistance","Lowers Physical resistance",EffectTargetType.ENEMY,5,20,DamageType.PHYSICAL)
                        })
        );
        p.addSpell(new Spell(
                        "Enemy AOE Flamestrike",
                        "fe",
                        10,
                        new TargetableEffect[]{
                                new InstantDamageEffect("Flame enemy aoe", "Burns your foes", EffectTargetType.ENEMY_AOE, new Damage(100, DamageType.FIRE))
                        })
        );
        p.addSpell(new Spell(
                        "Friendly AOE Flamestrike",
                        "ff",
                        10,
                        new TargetableEffect[]{
                                new InstantDamageEffect("Flame friendly aoe", "Burns your foes", EffectTargetType.FRIENDLY_AOE, new Damage(100, DamageType.FIRE))
                        })
        );
        p.addSpell(new Spell(
                        "All AOE Flamestrike",
                        "fa",
                        10,
                        new TargetableEffect[]{
                                new InstantDamageEffect("Flame friendly aoe", "Burns your foes", EffectTargetType.FRIENDLY_AOE, new Damage(100, DamageType.FIRE)),
                                new InstantDamageEffect("Flame enemy aoe", "Burns your foes", EffectTargetType.ENEMY_AOE, new Damage(100, DamageType.FIRE)),
                                new InstantDamageEffect("Flame single enemy ", "Burns your foes", EffectTargetType.ENEMY, new Damage(33, DamageType.FIRE)),
                                new InstantDamageEffect("Flame single friend ", "Burns your foes", EffectTargetType.FRIENDLY, new Damage(11, DamageType.FIRE))
                        })
        );
//        p.addSpell(new Spell("AOE Lightning", "l", 3, SpellTargetType.ENEMY_AOE, new InstantDamageEffect("Electro damage effect", "Deal 100 electro damage to each enemy", 100, DamageType.ELECTRIC)));
//        p.addSpell(new Spell("Pure force", "p", 7, SpellTargetType.ENEMY, new InstantDamageEffect("Pure force effect", "Deal 7 pure damage", 7, DamageType.PURE)));
//        p.addSpell(new Spell("Burn", "b", 4, SpellTargetType.ENEMY, new LastingDamageEffect("Burning", "Deal 20 fire damage each turn for 3 turns", 3, 20, DamageType.FIRE)));
//        p.addSpell(new Spell("Instant heal", "i", 10, SpellTargetType.FRIENDLY, new InstantHeal("Instant healing", "Heals 10 hp", 10)));
//        p.addSpell(new Spell("AOE heal", "ia", 10, SpellTargetType.FRIENDLY_AOE, new InstantHeal("Instant aoe healing", "Heals 10 hp to all allies", 10)));
//        p.addSpell(new Spell("Shield spell", "s", 10, SpellTargetType.FRIENDLY, new LastingAllDamageReduceEffect("Shield", "Reduces all incoming damage by 5", 10, 5)));
//        p.addSpell(new Spell("Critical strike", "c", 15, SpellTargetType.FRIENDLY, new AttackAmplifier("Critical Strike", "Grants x2.5 attack damage THIS turn", 1, 2.5)));
//        p.addSpell(new Spell("Self silence", "shh", 15, SpellTargetType.SELF, new Silence("Self silence", "SHUT THE FUCK UP!", 20)));


        p.addResistance(new Resistance(DamageType.PHYSICAL, 3));
//        p.addResistance(new Resistance(DamageType.FIRE,2));
//        p.addResistance(new Resistance(DamageType.ICE,1));
//        p.addResistance(new Resistance(DamageType.MAGIC,0));

        return p;

    }

    private static Monster getMonsterRat() {
        Monster p = new Monster("BIG RAT", 1100, 40, AttackRange.MELEE, 0);
//        p.addSpell(new Spell("Lightning","l",3, SpellTargetType.ENEMY,new InstantDamageEffect("Electro damage effect",7,DamageType.ELECTRIC)));
//        p.addSpell(new Spell("Pure force","p",7, SpellTargetType.ENEMY,new InstantDamageEffect("Pure force effect",7,DamageType.PURE)));
//        p.addSpell(new Spell("Burn","b",4, SpellTargetType.ENEMY,new LastingDamageEffect("Burning",3,2,DamageType.FIRE,"Slowly burns your body")));
//        p.addSpell(new Spell("Instant heal","i",10, SpellTargetType.FRIENDLY,new InstantHeal("Instant healing",10)));

        p.addResistance(new Resistance(DamageType.PHYSICAL, 0));
        p.addResistance(new Resistance(DamageType.FIRE, 3));
        p.addResistance(new Resistance(DamageType.ICE, 0));

        return p;
    }

    private static Monster getMonsterCat() {
        Monster p = new Monster("LARGE CAT", 1500, 400, AttackRange.MELEE, 0);

        p.addResistance(new Resistance(DamageType.PHYSICAL, 7));
        p.addResistance(new Resistance(DamageType.FIRE, 0));
        p.addResistance(new Resistance(DamageType.ICE, 10));

        return p;
    }

    private static void playerFightTest() {
        PlayerChar p1 = getPlayer(1);
        PlayerChar p2 = getPlayer(2);
        PlayerParty pp = new PlayerParty();
        pp.getChars().add(p1);
        pp.getChars().add(p2);
        Monster rat = getMonsterRat();
        Monster cat = getMonsterCat();
        MonsterParty mp = new MonsterParty();
        mp.getChars().add(rat);
        mp.getChars().add(cat);
        Fight fp = new Fight(pp, mp);
        if (fp.fight() == null) {
            print("YOU LOST!");
        }
        print("YOU WIN!");

    }


}
