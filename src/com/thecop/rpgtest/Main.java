package com.thecop.rpgtest;

import com.thecop.rpgtest.mech.fight.AttackType;
import com.thecop.rpgtest.mech.fight.DamageType;
import com.thecop.rpgtest.mech.fight.Resistance;
import com.thecop.rpgtest.mech.fight.ResistanceType;
import com.thecop.rpgtest.mech.magic.Spell;
import com.thecop.rpgtest.mech.player.PlayerAction;
import com.thecop.rpgtest.mech.screen.FightScreenController;
import com.thecop.rpgtest.object.Monster;
import com.thecop.rpgtest.object.Player;

import static com.thecop.rpgtest.Logger.print;

public class Main {

    public static void main(String[] args) {
        playerFightTest();

    }
    private static void playerFightTest() {
        Monster m1 = new Monster( 100, 10,"Enemy",AttackType.MELEE);
        Player p = new Player("Player",90,13);
        p.getSpells().add(new Spell("I am spell :)",'s',10,10, DamageType.FIRE));


        print("Fight:");
        print(m1);
        print(p);

        boolean playerTurn = true;
        FightScreenController fsc = new FightScreenController(p);
        while (p.isAlive() && m1.isAlive()) {
            if (playerTurn) {
                PlayerAction action = fsc.choosePlayerAction();
                print("Action = " + action);
                p.performAction(action,m1);
                playerTurn = false;
            } else {
                m1.attack(p);
                playerTurn = true;
            }
            print("=======END OF TURN=======");
        }
        print("Fight finished");
    }

    private static void fightTest() {
        Monster m1 = new Monster( 100, 10,"Good",AttackType.MELEE);
        Monster m2 = new Monster( 85, 13,"Bad",AttackType.MELEE);
        m1.setResistance(new Resistance(ResistanceType.FIRE, 5));
        m1.setResistance(new Resistance(ResistanceType.MAGIC, 6));
        m1.setResistance(new Resistance(ResistanceType.PHYSICAL, 5));

        m2.setResistance(new Resistance(ResistanceType.FIRE, 7));
        m2.setResistance(new Resistance(ResistanceType.MAGIC, 8));
        m2.setResistance(new Resistance(ResistanceType.PHYSICAL, 8));
        print("Fight:");
        print(m1);
        print(m2);
        boolean m1Turn = true;
        while (m1.isAlive() && m2.isAlive()) {
            if (m1Turn) {
                m1.attack(m2);
                m1Turn = false;
            } else {
                m2.attack(m1);
                m1Turn = true;
            }
            print("=======END OF TURN=======");
        }
        print("Fight finished");
    }


}
