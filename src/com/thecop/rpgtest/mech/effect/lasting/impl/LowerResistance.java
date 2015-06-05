package com.thecop.rpgtest.mech.effect.lasting.impl;

import com.thecop.rpgtest.mech.damage.DamageType;
import com.thecop.rpgtest.mech.damage.Resistance;
import com.thecop.rpgtest.mech.effect.EffectTargetType;
import com.thecop.rpgtest.mech.effect.lasting.LastingEffect;
import com.thecop.rpgtest.mech.effect.types.ResistanceModifier;

/**
 * Created by Admin on 01.04.2015.
 */
public class LowerResistance extends LastingEffect<LowerResistance> implements ResistanceModifier {
    private int lowerBy;
    private DamageType type;

    public LowerResistance(String name, String description, EffectTargetType targetType, int length, int lowerBy, DamageType type) {
        super(name, description, targetType, length);
        this.lowerBy = lowerBy;
        this.type=type;
    }

    public LowerResistance(LowerResistance other) {
        super(other);
        this.lowerBy = other.lowerBy;
        this.type=other.type;
    }


    @Override
    public Resistance modifyResistance(Resistance resistance) {
        if(resistance.getType()==type){
            return new Resistance(resistance.getType(),resistance.getStrength()-lowerBy);
        }
        return resistance;
    }

    @Override
    public LowerResistance getCopy() {
        return new LowerResistance(this);
    }


}
