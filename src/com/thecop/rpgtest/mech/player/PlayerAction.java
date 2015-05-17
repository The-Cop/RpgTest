package com.thecop.rpgtest.mech.player;

import com.thecop.rpgtest.mech.spell.Spell;
import com.thecop.rpgtest.object.GameChar;
import com.thecop.rpgtest.object.Party;

/**
 * Created by TheCop on 21.03.2015.
 */
public class PlayerAction {
    private PlayerActionType type;
    private GameChar target;
    private Party partyTarget;
    private Spell spell;

    public PlayerAction(PlayerActionType type, GameChar target, Party partyTarget, Spell spell) {
        this.type = type;
        this.target = target;
        this.partyTarget = partyTarget;
        this.spell = spell;
    }

    public PlayerActionType getType() {
        return type;
    }

    public Spell getSpell() {
        return spell;
    }

    public GameChar getTarget() {
        return target;
    }

    public Party getPartyTarget() {
        return partyTarget;
    }

    @Override
    public String toString() {
        return "PlayerAction{" +
                "type=" + type +
                ", spell=" + spell +
                ", target=" + (target == null ? "" : target.getName()) +
                ", partyTarget=" + (partyTarget == null ? "" : partyTarget.getNames()) +
                '}';
    }
}

