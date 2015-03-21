package com.thecop.rpgtest.mech.fight;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 20.03.2015.
 */
public class Damage {
    private DamageType type;
    private int amount;

    public Damage(DamageType type, int amount) {
        this.type = type;
        this.amount = amount;
    }

    public DamageType getType() {
        return type;
    }

    public int getAmount() {
        return amount;
    }

    /**
     *
     * @return List of resistances that affect damage with this type
     */
    public List<ResistanceType> getResistanceTypes() {
        List<ResistanceType> result = new ArrayList<>();
        if (type.isMagic()) {
            result.add(ResistanceType.MAGIC);
        }
        switch (type) {
            case FIRE:
                result.add(ResistanceType.FIRE);
                return result;
            case ICE:
                result.add(ResistanceType.ICE);
                return result;
            case PHYSICAL:
                result.add(ResistanceType.PHYSICAL);
                return result;
            case PURE:
                return result;
            default:
                return result;
        }
    }
}
