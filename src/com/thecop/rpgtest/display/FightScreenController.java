package com.thecop.rpgtest.display;

import com.thecop.rpgtest.mech.effect.Effect;
import com.thecop.rpgtest.mech.player.PlayerAction;
import com.thecop.rpgtest.mech.player.PlayerActionType;
import com.thecop.rpgtest.mech.spell.Spell;
import com.thecop.rpgtest.mech.spell.SpellTargetType;
import com.thecop.rpgtest.object.*;
import com.thecop.rpgtest.utils.Util;

import java.util.List;

import static com.thecop.rpgtest.Logger.dlog;
import static com.thecop.rpgtest.Logger.print;

/**
 * Created by TheCop on 21.03.2015.
 */
public class FightScreenController {

    private MonsterParty monsterParty;
    private PlayerParty playerParty;

    public FightScreenController(MonsterParty monsterParty, PlayerParty playerParty) {
        this.monsterParty = monsterParty;
        this.playerParty = playerParty;
    }

    public PlayerAction choosePlayerAction(PlayerChar player) {
        while (true) {
            print(player.getName() + " - what to do?");
            printFightCommands();
            FightCommand command = FightCommand.getCommand(Util.input());
            if(command==null){
                continue;
            }
            switch (command) {
                case ATTACK:
                    GameChar target = chooseTarget(monsterParty);
                    if (target == null) {
                        break;
                    }
                    return new PlayerAction(PlayerActionType.USUAL_ATTACK, target,null,null);
                case SPELL:
                    PlayerAction spellAction = chooseSpell(player);
                    if (spellAction != null) {
                        return spellAction;
                    }
                    break;
                case RUN:
                    print("Implement runaway");
                    break;
                case BACK:
                    return null;
            }
        }
    }

    private PlayerAction chooseSpell(PlayerChar player) {
        List<Spell> spells = player.getSpells();
        while (true) {
            print("Mana: " + player.getManaString());
            printSpellList(player);
            print("Q - back");
            String input = Util.input();
            if (FightCommand.getCommand(input) == FightCommand.BACK) {
                return null;
            }
            for (Spell spell : spells) {
                if (player.canCastSpell(spell)) {
                    if (spell.getControlString().equalsIgnoreCase(input)) {
                        //self-target spell
                        if (spell.getTargetType().equals(SpellTargetType.SELF)) {
                            return new PlayerAction(PlayerActionType.SPELL, player, null, spell);
                        }
                        //enemy-target spell
                        else if (spell.getTargetType().equals(SpellTargetType.ENEMY)) {
                            GameChar target = chooseTarget(monsterParty);
                            if (target != null) {
                                return new PlayerAction(PlayerActionType.SPELL, target, null, spell);
                            }
                        }
                        //friend-target spell
                        else if (spell.getTargetType().equals(SpellTargetType.FRIENDLY)) {
                            GameChar target = chooseTarget(playerParty);
                            if (target != null) {
                                return new PlayerAction(PlayerActionType.SPELL, target, null, spell);
                            }
                        }
                        //enemy aoe
                        else if (spell.getTargetType().equals(SpellTargetType.ENEMY_AOE)) {
                            return new PlayerAction(PlayerActionType.SPELL, null, monsterParty, spell);
                        }
                        //friendly aoe
                        else if (spell.getTargetType().equals(SpellTargetType.FRIENDLY_AOE)) {
                            return new PlayerAction(PlayerActionType.SPELL, null, playerParty, spell);
                        } else {
                            throw new IllegalArgumentException("Unknown spell target type!");
                        }
                    }
                }
            }
        }
    }

    private GameChar chooseTarget(Party party) {
        while (true) {
            print("Choose target:");
            printPartyCharsList(party);
            print("Q - back");
            String input = Util.input();
            if (FightCommand.getCommand(input) == FightCommand.BACK) {
                return null;
            }
            try {
                int position = Integer.parseInt(input) - 1;
                return (GameChar) party.getChars().get(position);
            } catch (Exception e) {
                dlog("Error: " + e.getMessage());
            }
        }
    }

    private void printPartyCharsList(Party party) {
        for (int i = 0; i < party.getChars().size(); i++) {
            GameChar gc = (GameChar) party.getChars().get(i);
            print((i + 1) + " - " + gc.getName());
        }
    }

    private void printSpellList(PlayerChar player) {
        if (player.getSpells().isEmpty()) {
            print("You have no spells");
            return;
        }
        print("Your spells:");
        for (Spell spell : player.getSpells()) {
            if (player.canCastSpell(spell)) {
                print(spell.getControlString() + " - " + spell.getName());
                for (Effect effect : spell.getEffects()) {
                    print("\t\t"+effect.getDescription());
                }
            } else {
                print(spell.getControlString() + " - " + spell.getName() + " - can not cast spell");
            }
        }
    }

    private void printFightCommands() {
        for (FightCommand fightCommand : FightCommand.values()) {
            print(fightCommand.getInputString() + " - " + fightCommand.getDescription());
        }
    }

}
