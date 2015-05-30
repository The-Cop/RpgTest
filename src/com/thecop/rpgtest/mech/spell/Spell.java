package com.thecop.rpgtest.mech.spell;

import com.thecop.rpgtest.mech.Copyable;
import com.thecop.rpgtest.mech.effect.EffectTargetType;
import com.thecop.rpgtest.mech.effect.TargetableEffect;
import com.thecop.rpgtest.utils.CopyUtils;
import com.thecop.rpgtest.utils.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.thecop.rpgtest.Logger.dlog;

/**
 * Created by TheCop on 21.03.2015.
 */
public class Spell implements Copyable<Spell> {
    private String name;
    private String controlString;
    private int manaCost;
    private List<TargetableEffect> effects = new ArrayList<>();

    public Spell(String name, String controlString, int manaCost, List<TargetableEffect> effects) {
        this.name = name;
        this.controlString = controlString;
        this.manaCost = manaCost;
        if (effects != null) {
            this.effects.addAll(effects);
        }
    }

    public Spell(String name, String controlString, int manaCost, TargetableEffect effect) {
        this(name, controlString, manaCost, Collections.EMPTY_LIST);
        this.effects.add(effect);
    }

    public Spell(String name, String controlString, int manaCost, TargetableEffect[] effects) {
        this(name, controlString, manaCost, Collections.EMPTY_LIST);
        this.effects.addAll(Arrays.asList(effects));
    }

    public Spell(Spell other) {
        this.name = other.name;
        this.controlString = other.controlString;
        this.manaCost = other.manaCost;
        this.effects = CopyUtils.getCopy(other.effects);
    }

    public SpellSingleTargetRequirements getSpellSingleTargetRequirements(){
        SpellSingleTargetRequirements req = SpellSingleTargetRequirements.NONE;
        for (TargetableEffect effect : effects) {
            if(effect.getTargetType()== EffectTargetType.ENEMY){
                if(req==SpellSingleTargetRequirements.NONE){
                    req=SpellSingleTargetRequirements.ENEMY;
                }
                else{
                    req=SpellSingleTargetRequirements.BOTH;
                    break;
                }
            }
            else if(effect.getTargetType()==EffectTargetType.FRIENDLY){
                if(req==SpellSingleTargetRequirements.NONE){
                    req=SpellSingleTargetRequirements.FRIENDLY;
                }
                else{
                    req=SpellSingleTargetRequirements.BOTH;
                    break;
                }
            }
        }
        dlog("SpellSingleTargetRequirements = "+req);
        return req;
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


    /**
     * @return effect copy
     */
    public List<TargetableEffect> getEffects() {
        return effects;
    }


    @Override
    public String toString() {
        return "Spell{" +
                "name='" + name + '\'' +
                ", controlString='" + controlString + '\'' +
                ", manaCost=" + manaCost +
                ", effects=" + Util.listToString(effects) +
                '}';
    }

    @Override
    public Spell getCopy() {
        return new Spell(this);
    }
}
