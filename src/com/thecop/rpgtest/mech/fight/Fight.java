package com.thecop.rpgtest.mech.fight;

import com.thecop.rpgtest.display.Display;
import com.thecop.rpgtest.display.FightScreenController;
import com.thecop.rpgtest.mech.effect.types.GameCharEffect;
import com.thecop.rpgtest.mech.effect.lasting.LastingEffect;
import com.thecop.rpgtest.mech.player.PlayerAction;
import com.thecop.rpgtest.object.impl.Monster;
import com.thecop.rpgtest.object.impl.MonsterParty;
import com.thecop.rpgtest.object.impl.PlayerChar;
import com.thecop.rpgtest.object.impl.PlayerParty;

import java.util.Iterator;

import static com.thecop.rpgtest.Logger.dlog;
import static com.thecop.rpgtest.Logger.print;

/**
 * Created by TheCop on 26.03.2015.
 */
public class Fight {
    private PlayerParty playerParty;
    private MonsterParty monsterParty;
    private boolean isPlayersTurn;
    private FightScreenController fsc;

    public Fight(PlayerParty playerParty, MonsterParty monsterParty) {
        this.playerParty = playerParty;
        this.monsterParty = monsterParty;
        setFirstTurn();
        fsc = new FightScreenController(this);
    }


    /**
     * Start fighting
     *
     * @return PlayerParty if the fight is won, null if lost
     */
    public PlayerParty fight() {
        print("Fight: " + playerParty.getNames() + " vs " + monsterParty.getNames());

        while (playerParty.isAlive() && monsterParty.isAlive()) {
            turn();
            tickAndDeleteEndedLastingEffects();
            applyLastingEffects();

            print("=======END OF TURN====================================================================================");
        }
        if (playerParty.isAlive()) {
            return playerParty;
        }
        print("Heroes are slain");
        return null;
    }

    private void turn() {
        if (isPlayersTurn) {
            //TODO implement choosing which player to attack (or implement speed-based turn order)
            for (PlayerChar playerChar : playerParty.getChars()) {
                //TODO implement impossibility of choosing dead monster as a target; or delete dead monsterParty from party
                PlayerAction action = null;
                while (action == null) {
                    displayFightParticipants();
                    action = fsc.choosePlayerAction(playerChar);
                }
                dlog("Action = " + action);
                playerChar.performAction(action,this);
            }
            isPlayersTurn = false;
        } else {
            //TODO implement monster's fight logic
            for (Monster monster : monsterParty.getChars()) {
                monster.attack(playerParty.getRandomPlayer());
            }
            isPlayersTurn = true;
        }
    }

    private void displayFightParticipants() {
        Display.separatorBig();
        print("Hero party:");
        Display.party(playerParty);
        print("Monsters:");
        Display.party(monsterParty);
    }

    private void applyLastingEffects() {
        for (PlayerChar playerChar : playerParty.getChars()) {
            for (LastingEffect lastingEffect : playerChar.getLastingEffects()) {
                if (lastingEffect instanceof GameCharEffect) {
                    GameCharEffect gce = (GameCharEffect) lastingEffect;
                    gce.apply(playerChar);
                }
            }
        }

        for (Monster monster : monsterParty.getChars()) {
            for (LastingEffect lastingEffect : monster.getLastingEffects()) {
                if (lastingEffect instanceof GameCharEffect) {
                    GameCharEffect gce = (GameCharEffect) lastingEffect;
                    gce.apply(monster);
                }
            }
        }
    }

    private void tickAndDeleteEndedLastingEffects() {
        dlog("Processing playerParty effects");
        for (PlayerChar playerChar : playerParty.getChars()) {
            Iterator<LastingEffect> i = playerChar.getLastingEffects().iterator();
            while (i.hasNext()) {
                LastingEffect le = i.next();
                dlog("Processing effect " + le.getName());
                le.tick();
                if (le.ended()) {
                    dlog("Effect " + le.getName() + " has ended");
                    i.remove();
                }
            }
        }
        dlog("Processing monsterParty effects");
        for (Monster monster : monsterParty.getChars()) {
            Iterator<LastingEffect> i = monster.getLastingEffects().iterator();
            while (i.hasNext()) {
                LastingEffect le = i.next();
                dlog("Processing effect " + le.getName());
                le.tick();
                if (le.ended()) {
                    print("Effect " + le.getName() + " has ended");
                    i.remove();
                }
            }
        }
    }

    private void setFirstTurn() {
        //TODO set first turn to gameChar with more speed
        isPlayersTurn = true;
    }

    public PlayerParty getPlayerParty() {
        return playerParty;
    }

    public MonsterParty getMonsterParty() {
        return monsterParty;
    }

    public boolean isPlayersTurn() {
        return isPlayersTurn;
    }
}
