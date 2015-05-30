package com.thecop.rpgtest.mech.effect;

/**
 * Created by TheCop on 31.05.2015.
 */
public abstract class TargetableEffect<T> extends Effect<T> {
    private EffectTargetType targetType;

    public TargetableEffect(String name, String description, EffectTargetType targetType) {
        super(name, description);
        this.targetType = targetType;
    }

    public TargetableEffect(TargetableEffect other) {
        super(other);
        this.targetType = other.targetType;
    }

    public EffectTargetType getTargetType() {
        return targetType;
    }
}
