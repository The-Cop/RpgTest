package com.thecop.rpgtest.mech.player;

import com.thecop.rpgtest.mech.magic.Spell;

/**
 * Created by TheCop on 21.03.2015.
 */
public class PlayerAction {
    private  PlayerActionType type;
    private  Spell spell;

    public PlayerAction(PlayerActionType type, Spell spell) {
        this.type = type;
        this.spell = spell;
    }

    public PlayerActionType getType() {
        return type;
    }

    public Spell getSpell() {
        return spell;
    }

    @Override
    public String toString() {
        return "PlayerAction{" +
                "type=" + type +
                ", spell=" + spell +
                '}';
    }
}

