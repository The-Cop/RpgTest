package com.thecop.rpgtest.mech.fight;

import com.thecop.rpgtest.mech.effect.GameCharEffect;
import com.thecop.rpgtest.mech.effect.lasting.LastingEffect;
import com.thecop.rpgtest.mech.player.PlayerAction;
import com.thecop.rpgtest.mech.screen.FightScreenController;
import com.thecop.rpgtest.object.GameChar;
import com.thecop.rpgtest.object.PlayerChar;
import com.thecop.rpgtest.utils.Display;

import java.util.Iterator;

import static com.thecop.rpgtest.Logger.dlog;
import static com.thecop.rpgtest.Logger.print;

/**
 * Created by TheCop on 26.03.2015.
 */
public class FightProcessor {
    private PlayerChar playerChar;
    private GameChar monster;
    private boolean isPlayerTurn;
    private FightScreenController fsc;

    public FightProcessor(PlayerChar playerChar, GameChar monster) {
        this.playerChar = playerChar;
        this.monster = monster;
        setFirstTurn();
        fsc = new FightScreenController(playerChar);
    }

    /**
     * Start fighting
     * @return Winner of the fight
     */
    public GameChar fight(){
        print("Fight: " + playerChar.getName() + " vs " + monster.getName());

        while(playerChar.isAlive() && monster.isAlive()){
            turn();
            tickAndDeleteEndedEffects();
            applyLastingEffects();

            //TODO first tick of newly applied effect goes nowhere because it is not processed but length is reduced
            //TODO maybe set boolean "added this turn" - and if so, no tick for it

            print("=======END OF TURN=======");
        }
        if(playerChar.isAlive()){
            print("Winner is " + playerChar.getName());
            return playerChar;
        }
        print("Winner is " + monster.getName());
        return monster;
    }

    private void turn() {
        Display.separatorBig();
        Display.playerInfo(playerChar);
        Display.separator();
        Display.monsterInfo(monster);
        Display.separator();
        if(isPlayerTurn){
            PlayerAction action = fsc.choosePlayerAction();
            dlog("Action = " + action);
            playerChar.performAction(action, monster);
            isPlayerTurn=false;
        }
        else {
            monster.attack(playerChar);
            isPlayerTurn=true;
        }

    }

    private void applyLastingEffects(){
        for (LastingEffect lastingEffect : playerChar.getLastingEffects()) {
            if(lastingEffect instanceof GameCharEffect){
                GameCharEffect gce = (GameCharEffect)lastingEffect;
                gce.apply(playerChar);
            }

        }
        for (LastingEffect lastingEffect : monster.getLastingEffects()) {
            if(lastingEffect instanceof GameCharEffect){
                GameCharEffect gce = (GameCharEffect)lastingEffect;
                gce.apply(monster);
            }
        }
    }

    private void tickAndDeleteEndedEffects(){
        Iterator<LastingEffect> i = playerChar.getLastingEffects().iterator();
        dlog("Processing effects");

        while (i.hasNext()) {
            LastingEffect le = i.next();
            dlog("Processing effect " + le.getName());
            le.tick();
            if(le.ended()){
                i.remove();
                dlog("Effect " + le.getName() + " has ended");
            }
        }

        i = monster.getLastingEffects().iterator();
        while (i.hasNext()) {
            LastingEffect le = i.next();
            le.tick();
            if(le.ended()){
                print("Effect " + le.getName() + " has ended");
                i.remove();
            }
        }
    }

    private void setFirstTurn(){
        //TODO set first turn to gameChar with more speed
        isPlayerTurn=true;
    }

}
