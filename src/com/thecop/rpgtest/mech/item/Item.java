package com.thecop.rpgtest.mech.item;

import com.thecop.rpgtest.mech.effect.Effect;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TheCop on 16.05.2015.
 */
public class Item {
    private List<Effect> effects = new ArrayList<>();
    private String name;

    public Item(String name, List<Effect> effects) {
        this.name = name;
        this.effects.addAll(effects);
    }
    public Item(String name, Effect effect) {
        this.name = name;
        this.effects.add(effect);
    }


    public List<Effect> getEffects() {
        return effects;
    }

    public void setEffects(List<Effect> effects) {
        this.effects = effects;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
