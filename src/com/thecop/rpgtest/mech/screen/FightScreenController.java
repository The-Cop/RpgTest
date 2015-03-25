package com.thecop.rpgtest.mech.screen;

import com.thecop.rpgtest.mech.spell.Spell;
import com.thecop.rpgtest.mech.player.PlayerAction;
import com.thecop.rpgtest.mech.player.PlayerActionType;
import com.thecop.rpgtest.object.Player;
import com.thecop.rpgtest.utils.Util;

import java.util.List;

import static com.thecop.rpgtest.Logger.print;

/**
 * Created by TheCop on 21.03.2015.
 */
public class FightScreenController {
    private Player player;

    public FightScreenController(Player player) {
        this.player = player;
    }

    public PlayerAction choosePlayerAction() {
        while (true) {
            print("What to do?");
            print("a - Attack");
            print("s - Use a spell");
            print("r - Run like hell");
            switch (Util.input()){
                case 'a':
                    return new PlayerAction(PlayerActionType.USUAL_ATTACK,null);
                case 's':
                    PlayerAction action = chooseSpell();
                    if(action!=null){
                        return action;
                    }
                     break;
                case 'r':
                    print("Implement runaway");
                    break;
            }
        }
    }

    private PlayerAction chooseSpell(){
        List<Spell> spells = player.getSpells();
        while (true) {
            printSpellList(spells);
            print("q - back");
            char c = Util.input();
            if(c=='q'){
                return null;
            }
            for (Spell spell : spells) {
                if(player.canCastSpell(spell)){
                    if(spell.getControlChar()==c){
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
            if(player.canCastSpell(spell)) {
                print(spell.getControlChar() + " - " + spell.getName());
            }
            else {
                print(spell.getControlChar() + " - " + spell.getName() + " - not enough mana");
            }
        }
    }


}
