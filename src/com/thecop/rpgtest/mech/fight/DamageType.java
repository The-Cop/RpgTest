package com.thecop.rpgtest.mech.fight;

/**
 * Created by Admin on 20.03.2015.
 */
public enum DamageType {
    PHYSICAL, FIRE, ICE,MAGIC,PURE;

    public boolean isMagic() {
        if (this == FIRE || this == ICE) {
            return true;
        }
        return false;
    }
}
