package com.thecop.rpgtest.mech.spell;

import com.thecop.rpgtest.mech.Copyable;
import com.thecop.rpgtest.mech.effect.Effect;
import com.thecop.rpgtest.utils.CopyUtils;
import com.thecop.rpgtest.utils.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by TheCop on 21.03.2015.
 */
public class Spell implements Copyable<Spell>{
    private String name;
    private String controlString;
    private int manaCost;
    private SpellTargetType targetType;
    private List<Effect> effects = new ArrayList<>();

    public Spell(String name, String controlString, int manaCost, SpellTargetType targetType, List<Effect> effects) {
        this.name = name;
        this.controlString = controlString;
        this.manaCost = manaCost;
        this.targetType = targetType;
        if(effects!=null){
            this.effects.addAll(effects);
        }
    }
    public Spell(String name, String controlString, int manaCost, SpellTargetType targetType, Effect effect) {
        this(name,controlString,manaCost,targetType, Collections.EMPTY_LIST);
        this.effects.add(effect);
    }
    public Spell(String name, String controlString, int manaCost, SpellTargetType targetType, Effect[] effects) {
        this(name,controlString,manaCost,targetType, Collections.EMPTY_LIST);
        this.effects.addAll(Arrays.asList(effects));
    }

    public Spell(Spell other) {
        this.name = other.name;
        this.controlString = other.controlString;
        this.manaCost = other.manaCost;
        this.targetType = other.targetType;
        this.effects = CopyUtils.getCopy(other.effects);
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
    public List<Effect> getEffects() {
        return effects;
    }


    @Override
    public String toString() {
        return "Spell{" +
                "name='" + name + '\'' +
                ", controlString='" + controlString + '\'' +
                ", manaCost=" + manaCost +
                ", targetType=" + targetType +
                ", effects=" + Util.listToString(effects) +
                '}';
    }

    @Override
    public Spell getCopy() {
        return new Spell(this);
    }
}
