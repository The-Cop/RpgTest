package com.thecop.rpgtest.mech.spell;

import com.thecop.rpgtest.mech.effect.Effect;

/**
 * Created by TheCop on 21.03.2015.
 */
public class Spell {
    private String name;
    private String controlString;
    private int manaCost;
    private SpellTargetType targetType;
    private Effect effect;
    private boolean isModified=false;

    public Spell(String name, String controlString, int manaCost, SpellTargetType targetType, Effect effect) {
        this.name = name;
        this.controlString = controlString;
        this.manaCost = manaCost;
        this.targetType = targetType;
        this.effect = effect;
    }

    public Spell(Spell other) {
        this.name = other.name;
        this.controlString = other.controlString;
        this.manaCost = other.manaCost;
        this.targetType = other.targetType;
        this.effect = other.getEffect();
    }


    public String getName() {
        return name;
    }

    public String getControlString() {
        return controlString;
    }

    public int getManaCost() {
        return manaCost;
    }

    public SpellTargetType getTargetType() {
        return targetType;
    }

    /**
     *
     * @return effect copy
     */
    public Effect getEffect() {
        return effect;
    }

    public boolean isModified() {
        return isModified;
    }

    public void setModified(boolean isModified) {
        this.isModified = isModified;
    }
}
