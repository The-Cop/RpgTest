package com.thecop.rpgtest.object;

import com.thecop.rpgtest.mech.effect.AttackModifier;
import com.thecop.rpgtest.mech.effect.Effect;
import com.thecop.rpgtest.mech.effect.SpellModifier;
import com.thecop.rpgtest.mech.effect.lasting.LastingEffect;
import com.thecop.rpgtest.mech.fight.*;
import com.thecop.rpgtest.mech.spell.Spell;
import com.thecop.rpgtest.mech.spell.SpellProcessor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by TheCop on 24.03.2015.
 */
public abstract class GameChar {
    protected String name;

    protected int health;
    protected int maxHealth;

    protected int mana;
    protected int maxMana;

    protected AttackRange baseAttackRange;

    protected Map<DamageType, Resistance> resistances;
    protected List<LastingEffect> lastingEffects;
    protected List<Spell> spells;


    public GameChar(String name, int health, int maxHealth, int mana, int maxMana, AttackRange baseAttackRange) {
        this.name = name;
        this.health = health;
        this.maxHealth = maxHealth;
        this.mana = mana;
        this.maxMana = maxMana;
        this.baseAttackRange = baseAttackRange;
        init();
    }

    public GameChar(String name, int health,  int mana, AttackRange baseAttackRange) {
        this.name = name;
        this.health = health;
        this.maxHealth = health;
        this.mana = mana;
        this.maxMana = mana;
        this.baseAttackRange = baseAttackRange;
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

    public void addSpell(Spell s) {
        for (Spell spell : spells) {
            if(spell.getControlString().equalsIgnoreCase(s.getControlString())){
                throw new IllegalArgumentException("Control strings must be unique!");
            }
        }
        spells.add(s);
    }

    public void addResistance(Resistance resistance) {
        resistances.put(resistance.getType(), resistance);
    }

    public void attack(GameChar target){
        if(canAttack()) {
            DamageProcessor.attackDamage(this, target, getAttack(), baseAttackRange);
        }
    }

    public void takeDamage(int damageAmount) {
        //TODO get here damage processing
        //TODO take Damage argument
        //TODO process it through IncomingDamageEffects
        health = health - damageAmount;
        if (health < 0) {
            health = 0;
        }
    }

    public Resistance getResistance(DamageType type) {
        return resistances.get(type);
    }

    public int getHealthLeft() {
        return health;
    }

    public String getHealthString() {
        return health + "/" + maxHealth;
    }

    public boolean isAlive() {
        return health > 0;
    }

    public String getName() {
        return name;
    }

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

    public List<Spell> getUnmodifiedSpells() {
        return spells;
    }

    public boolean canCastSpell(Spell spell) {
        Spell modSpell = getModifiedSpell(spell);
        if(modSpell==null){
            return false;
        }
        return mana >= modSpell.getManaCost();
    }

    public void castSpell(Spell spell, GameChar target){
        spell=getModifiedSpell(spell);
        mana=mana-spell.getManaCost();
        SpellProcessor.applySpell(spell, this, target);
    }

    public boolean canAttack() {
        return getAttack()!=null;
    }

    public Spell getModifiedSpell(Spell spell) {
        //if already modified - return
        if(spell.isModified()){
            return spell;
        }
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
        spellCopy.setModified(true);
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

    public List<LastingEffect> getLastingEffects() {
        return lastingEffects;
    }
}
