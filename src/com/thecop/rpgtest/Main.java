package com.thecop.rpgtest;

import com.thecop.rpgtest.mech.fight.AttackType;
import com.thecop.rpgtest.mech.fight.Resistance;
import com.thecop.rpgtest.mech.fight.ResistanceType;
import com.thecop.rpgtest.object.Monster;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) {
        fightTest();

    }

    private static void fightTest() {
        Monster m1 = new Monster("Good", 100, 10);
        Monster m2 = new Monster("Bad", 85, 13);
        m1.setResistance(new Resistance(ResistanceType.FIRE,5));
        m1.setResistance(new Resistance(ResistanceType.MAGIC,6));
        m1.setResistance(new Resistance(ResistanceType.PHYSICAL,5));

        m2.setResistance(new Resistance(ResistanceType.FIRE,7));
        m2.setResistance(new Resistance(ResistanceType.MAGIC,8));
        m2.setResistance(new Resistance(ResistanceType.PHYSICAL,8));
        Logger.log("Fight:");
        Logger.log(m1);
        Logger.log(m2);
        boolean m1Turn = true;
        while (m1.isAlive() && m2.isAlive()) {
            if (m1Turn) {
                m1.attack(m2, AttackType.MELEE);
                m1Turn = false;
            } else {
                m2.attack(m1, AttackType.MELEE);
                m1Turn = true;
            }
            Logger.log("=======END OF TURN=======");
        }
        Logger.log("Fight finished");
    }

    private static void defenceCurveTest() {
        double defConst = 0.04;
        for (int i = -10; i <= 1000; ) {
            double def = (i * defConst) / (1 + i * defConst);
            System.out.println("Def=" + i + "\t " + def);
            if (i < 20) {
                i = i + 5;
            } else if (i < 100) {
                i = i + 10;
            } else {
                i = i + 100;
            }
        }
    }

    private void waitInput() {
        System.out.print("Enter something:");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            int c;
            while ((c = br.read()) != -1) {
                char character = (char) c;
                System.out.println("Entered: " + character);
                if (character == 'q') {
                    break;
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
