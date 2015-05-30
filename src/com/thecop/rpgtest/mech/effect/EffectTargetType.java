package com.thecop.rpgtest.mech.effect;

/**
 * Created by TheCop on 31.05.2015.
 */
public enum EffectTargetType {
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
