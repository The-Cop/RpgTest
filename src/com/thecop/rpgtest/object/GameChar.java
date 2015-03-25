package com.thecop.rpgtest.object;

import com.thecop.rpgtest.mech.effect.AttackModifier;
import com.thecop.rpgtest.mech.effect.Effect;
import com.thecop.rpgtest.mech.effect.SpellModifier;
import com.thecop.rpgtest.mech.effect.lasting.LastingEffect;
import com.thecop.rpgtest.mech.fight.Damage;
import com.thecop.rpgtest.mech.fight.DamageType;
import com.thecop.rpgtest.mech.fight.Resistance;
import com.thecop.rpgtest.mech.iteraction.Fightable;
import com.thecop.rpgtest.mech.iteraction.SpellCastable;
import com.thecop.rpgtest.mech.spell.Spell;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by TheCop on 24.03.2015.
 */
public abstract class GameChar implements Fightable, SpellCastable {
    protected String name;

    protected int health;
    protected int maxHealth;

    protected int mana;
    protected int maxMana;

    protected Map<DamageType, Resistance> resistances;
    protected List<LastingEffect> lastingEffects;
    protected List<Spell> spells;

    protected GameChar() {
        init();
    }

    public GameChar(String name, int health, int maxHealth, int mana, int maxMana) {
        this.name = name;
        this.health = health;
        this.maxHealth = maxHealth;
        this.mana = mana;
        this.maxMana = maxMana;
        init();
    }

    private void init() {
        resistances = new HashMap<>();
        lastingEffects = new ArrayList<>();
        spells = new ArrayList<>();
    }

    public void addEffect(LastingEffect e) {
        lastingEffects.add(e);
    }

    public void setResistance(Resistance resistance) {
        resistances.put(resistance.getType(), resistance);
    }

    @Override
    public void takeDamage(int damageAmount) {
        health = health - damageAmount;
        if (health < 0) {
            health = 0;
        }
    }

    @Override
    public Resistance getResistance(DamageType type) {
        return resistances.get(type);
    }

    @Override
    public int getHealthLeft() {
        return health;
    }

    @Override
    public String getHealthString() {
        return health + "/" + maxHealth;
    }

    @Override
    public boolean isAlive() {
        return health > 0;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<Spell> getSpells() {
        List<Spell> modSpells = new ArrayList<>();
        for (Spell spell : spells) {
            Spell ms = getModifiedSpell(spell);
            if(ms!=null) {
                modSpells.add(ms);
            }
        }
        return spells;
    }

    @Override
    public List<Spell> getUnmodifiedSpells() {
        return spells;
    }

    @Override
    public boolean canCastSpell(Spell spell) {
        Spell modSpell = getModifiedSpell(spell);
        if(modSpell==null){
            return false;
        }
        return mana >= modSpell.getManaCost();
    }

    @Override
    public boolean canAttack() {
        return getAttack()!=null;
    }

    public Spell getModifiedSpell(Spell spell) {
        Spell spellCopy = new Spell(spell);
        for (Effect effect : lastingEffects) {
            if (effect instanceof SpellModifier) {
                SpellModifier sm = (SpellModifier) effect;
                spellCopy = sm.modifySpell(spellCopy);
                if (spellCopy == null) {
                    return null;
                }
            }
        }
        return spellCopy;
    }

    public Damage getAttack(){
        Damage damage = getBaseUnmodifiedAttack();
        if (damage == null) {
            return null;
        }
        for (Effect effect : lastingEffects) {
            if (effect instanceof AttackModifier) {
                AttackModifier am = (AttackModifier) effect;
                damage = am.modifyAttack(damage);
                if (damage == null) {
                    return null;
                }
            }
        }
        return damage;
    }

    protected abstract Damage getBaseUnmodifiedAttack();

    public String getManaString() {
        return mana + "/" + maxMana;
    }

    public int getHealth() {
        return health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getMana() {
        return mana;
    }

    public int getMaxMana() {
        return maxMana;
    }

    public void setHealth(int health) {
        this.health = health;
        if (this.health > maxHealth) {
            this.health = maxHealth;
        }
    }
}
