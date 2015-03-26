package com.thecop.rpgtest.mech.fight;

/**
 * Created by Admin on 20.03.2015.
 */
public enum DamageType {
    PHYSICAL, FIRE,ICE,ELECTRIC,MAGIC,PURE;

    public boolean isMagic() {
        if (this == FIRE || this == ICE||this==ELECTRIC) {
            return true;
        }
        return false;
    }
}
