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
    private String description;

    public Item(String name, String description, List<Effect> effects) {
        this.name = name;
        this.effects.addAll(effects);
        this.description = description;
    }

    public Item(String name, String description, Effect effect) {
        this.name = name;
        this.effects.add(effect);
        this.description = description;
    }


    public List<Effect> getEffects() {
        return effects;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
