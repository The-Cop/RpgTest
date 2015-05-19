package com.thecop.rpgtest.mech.effect.permanent.impl;

import com.thecop.rpgtest.mech.effect.Effect;
import com.thecop.rpgtest.mech.effect.GameCharEffect;
import com.thecop.rpgtest.mech.effect.permanent.PermanentEffect;
import com.thecop.rpgtest.utils.CopyUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TheCop on 19.05.2015.
 */
public class  GameCharEffectsCaster extends PermanentEffect<GameCharEffectsCaster> {
    private List<Effect<? extends GameCharEffect>> effects = new ArrayList<>();
    public GameCharEffectsCaster(String name, String description) {
        super(name, description);
    }

    //TODO define how to differ effects whish must be applied to self, enemy, aoe and so on.
    //any set of them can be simultaneously be presented in one gameCharEffectsCaster
    public GameCharEffectsCaster(GameCharEffectsCaster other) {
        super(other);
        effects = CopyUtils.getCopy(other.getEffects());
    }

    public List<Effect<? extends GameCharEffect>> getEffects() {
        return effects;
    }

    @Override
    public GameCharEffectsCaster getCopy() {
        return new GameCharEffectsCaster(this);
    }
}
