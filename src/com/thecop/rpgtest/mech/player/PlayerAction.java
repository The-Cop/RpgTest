package com.thecop.rpgtest.mech.player;

import com.thecop.rpgtest.mech.spell.Spell;
import com.thecop.rpgtest.object.GameChar;

/**
 * Created by TheCop on 21.03.2015.
 */
public class PlayerAction {
    private PlayerActionType type;
    private GameChar friendlyTarget;
    private GameChar enemyTarget;
    private Spell spell;

    public PlayerAction(PlayerActionType type, GameChar friendlyTarget, GameChar enemyTarget, Spell spell) {
        this.type = type;
        this.friendlyTarget = friendlyTarget;
        this.enemyTarget = enemyTarget;
        this.spell = spell;
    }

    public PlayerActionType getType() {
        return type;
    }

    public Spell getSpell() {
        return spell.getCopy();
    }

    public GameChar getFriendlyTarget() {
        return friendlyTarget;
    }

    public GameChar getEnemyTarget() {
        return enemyTarget;
    }

    @Override
    public String toString() {
        return "PlayerAction{" +
                "type=" + type +
                ", friendlyTarget=" + friendlyTarget +
                ", enemyTarget=" + enemyTarget +
                ", spell=" + spell +
                '}';
    }
}

