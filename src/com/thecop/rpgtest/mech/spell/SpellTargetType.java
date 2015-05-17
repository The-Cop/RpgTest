package com.thecop.rpgtest.mech.spell;

/**
 * Created by TheCop on 23.03.2015.
 */
public enum SpellTargetType {
    ENEMY,
    ENEMY_AOE,
    FRIENDLY,
    FRIENDLY_AOE,
    SELF;

    public boolean isAOE() {
        if (this == ENEMY_AOE || this == FRIENDLY_AOE) {
            return true;
        }
        return false;
    }
}
