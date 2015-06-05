package com.thecop.rpgtest.mech.effect.types;

import com.thecop.rpgtest.mech.damage.Resistance;

/**
 * Created by TheCop on 24.03.2015.
 */
public interface ResistanceModifier {
    public abstract Resistance modifyResistance(Resistance resistance);
}
