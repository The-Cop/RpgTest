package com.thecop.rpgtest.mech.screen;

import com.thecop.rpgtest.mech.spell.Spell;
import com.thecop.rpgtest.mech.player.PlayerAction;
import com.thecop.rpgtest.mech.player.PlayerActionType;
import com.thecop.rpgtest.object.PlayerChar;
import com.thecop.rpgtest.utils.Util;

import java.util.List;

import static com.thecop.rpgtest.Logger.print;

/**
 * Created by TheCop on 21.03.2015.
 */
public class FightScreenController {

    private PlayerChar playerChar;

    public FightScreenController(PlayerChar playerChar) {
        this.playerChar = playerChar;
    }

    public PlayerAction choosePlayerAction() {
        while (true) {
            print("What to do?");
            printFightCommands();
            switch (FightCommand.getCommand(Util.input())){
                case ATTACK:
                    return new PlayerAction(PlayerActionType.USUAL_ATTACK,null);
                case SPELL:
                    PlayerAction action = chooseSpell();
                    if(action!=null){
                        return action;
                    }
                     break;
                case RUN:
                    print("Implement runaway");
                    break;
            }
        }
    }

    private PlayerAction chooseSpell(){
        List<Spell> spells = playerChar.getSpells();
        while (true) {
            print("Mana: " + playerChar.getManaString());
            printSpellList(spells);
            print("q - back");
            String input = Util.input();
            if(FightCommand.getCommand(input)== FightCommand.BACK){
                return null;
            }
            for (Spell spell : spells) {
                if(playerChar.canCastSpell(spell)){
                    if(spell.getControlString().equalsIgnoreCase(input)){
                        return new PlayerAction(PlayerActionType.SPELL,spell);
                    }
                }
            }
        }
    }

    private void printSpellList(List<Spell> spells){
        if(spells.isEmpty()){
            print("You have no spells");
            return;
        }
        print("Your spells:");
        for (Spell spell : spells) {
            if(playerChar.canCastSpell(spell)) {
                print(spell.getControlString() + " - " + spell.getName());
            }
            else {
                print(spell.getControlString() + " - " + spell.getName() + " - can not cast spell");
            }
        }
    }

    private void printFightCommands(){
        for (FightCommand fightCommand : FightCommand.values()) {
            print(fightCommand.getInputString()+" - "+fightCommand.getDescription());
        }
    }


}
