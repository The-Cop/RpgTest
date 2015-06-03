package com.thecop.rpgtest.object;

import com.thecop.rpgtest.Logger;
import com.thecop.rpgtest.mech.damage.AttackRange;
import com.thecop.rpgtest.mech.damage.Damage;
import com.thecop.rpgtest.mech.damage.DamageType;
import com.thecop.rpgtest.mech.damage.Resistance;
import com.thecop.rpgtest.mech.effect.Effect;
import com.thecop.rpgtest.mech.effect.TargetableEffect;
import com.thecop.rpgtest.mech.effect.instant.InstantEffect;
import com.thecop.rpgtest.mech.effect.lasting.LastingEffect;
import com.thecop.rpgtest.mech.effect.types.AttackModifier;
import com.thecop.rpgtest.mech.effect.types.IncomingDamageEffect;
import com.thecop.rpgtest.mech.effect.types.SpellModifier;
import com.thecop.rpgtest.mech.fight.Fight;
import com.thecop.rpgtest.mech.spell.Spell;
import com.thecop.rpgtest.mech.spell.SpellSingleTargetRequirements;
import com.thecop.rpgtest.mech.spell.SpellTarget;
import com.thecop.rpgtest.utils.Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.thecop.rpgtest.Logger.dlog;
import static com.thecop.rpgtest.Logger.print;

/**
 * Created by TheCop on 24.03.2015.
 */
public abstract class GameChar implements SpellTarget {
    public static final double RESISTANCE_FORMULA_MULTIPLIER = 0.04d;

    protected String name;

    protected int health;
    protected int maxHealth;

    protected int mana;
    protected int maxMana;

    protected AttackRange baseAttackRange;

    protected Map<DamageType, Resistance> resistances = new HashMap<>();
    protected List<LastingEffect> lastingEffects = new ArrayList<>();
    protected List<Spell> spells = new ArrayList<>();
    //TODO 7 active items and a backpack with same limit (10?)
    //Backpack may be common for playable party


    public GameChar(String name, int health, int maxHealth, int mana, int maxMana, AttackRange baseAttackRange) {
        this.name = name;
        this.health = health;
        this.maxHealth = maxHealth;
        this.mana = mana;
        this.maxMana = maxMana;
        this.baseAttackRange = baseAttackRange;
    }

    public GameChar(String name, int health, int mana, AttackRange baseAttackRange) {
        this(name, health, health, mana, mana, baseAttackRange);
    }


    public void addEffect(LastingEffect e) {
        lastingEffects.add(e);
    }

    public void addSpell(Spell s) {
        for (Spell spell : spells) {
            if (spell.getControlString().equalsIgnoreCase(s.getControlString())) {
                throw new IllegalArgumentException("Control strings must be unique!");
            }
        }
        spells.add(s);
    }

    public void addResistance(Resistance resistance) {
        resistances.put(resistance.getType(), resistance);
    }

    public void attack(GameChar target) {
        //TODO pretty print for attack
        if (canAttack()) {
            print(getName() + " attacks " + target.getName() + " with " + getAttack().toString());
            target.takeDamage(getAttack(), baseAttackRange);
        } else {
            print(getName() + " can not attack!");
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
            if (ms != null) {
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
        if (modSpell == null) {
            return false;
        }
        return mana >= modSpell.getManaCost();
    }

    public void castSpell(Spell spell, Fight fight, GameChar enemyTarget, GameChar friendlyTarget) {
        spell = getModifiedSpell(spell);
        dlog("Casting spell " + spell.toString());
        SpellSingleTargetRequirements req = spell.getSpellSingleTargetRequirements();
        if (req == SpellSingleTargetRequirements.FRIENDLY || req == SpellSingleTargetRequirements.BOTH) {
            //friendlyTarget must be single and non-null
            if (friendlyTarget == null) {
                throw new IllegalArgumentException("friendlyTarget can not be null for spell with SpellSingleTargetRequirements = " + req);
            }
        }
        if (req == SpellSingleTargetRequirements.ENEMY || req == SpellSingleTargetRequirements.BOTH) {
            //friendlyTarget must be single and non-null
            if (enemyTarget == null) {
                throw new IllegalArgumentException("enemyTarget can not be null for spell with SpellSingleTargetRequirements = " + req);
            }
        }

        Party friendlyParty = fight.isPlayersTurn() ? fight.getPlayerParty() : fight.getMonsterParty();
        Party enemyParty = fight.isPlayersTurn() ? fight.getMonsterParty() : fight.getPlayerParty();

        for (TargetableEffect effect : spell.getEffects()) {
            switch (effect.getTargetType()) {
                case SELF:
                    applySingleTargetEffect(effect, this);
                    break;
                case FRIENDLY:
                    applySingleTargetEffect(effect, friendlyTarget);
                    break;
                case ENEMY:
                    applySingleTargetEffect(effect, enemyTarget);
                    break;
                case FRIENDLY_AOE:
                    applyAOEEffect(effect, friendlyParty);
                    break;
                case ENEMY_AOE:
                    applyAOEEffect(effect, enemyParty);
                    break;
            }
        }

        mana = mana - spell.getManaCost();
    }


    private void applyAOEEffect(TargetableEffect effect, Party target) {
        for (Object gameCharObj : target.getChars()) {
            GameChar gc = (GameChar) gameCharObj;
            applySingleTargetEffect(effect, gc);
        }
    }

    private void applySingleTargetEffect(TargetableEffect effect, GameChar target) {
        if (effect instanceof LastingEffect) {
            target.addEffect((LastingEffect) effect);
            print(target.getName() + " is now affected by " + effect.getName());
        } else if (effect instanceof InstantEffect) {
            InstantEffect ie = (InstantEffect) effect;
            dlog("Applying instant effect " + ie.getName() + " to " + target.getName());
            ie.applyInstantEffect(target);
        }
    }

    public boolean canAttack() {
        return getAttack() != null;
    }

    private Spell getModifiedSpell(Spell spell) {
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

    public Damage getAttack() {
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

    public void takeDamage(Damage damage, AttackRange attackRange) {
        calculateAndDealDamage(damage);
        if (attackRange == AttackRange.MELEE) {
            //TODO implement attacktype - if spell or ranged then no counterattack can be done
        }
    }

    public void takeEffectDamage(Damage damage, String effectName) {
        print(getName() + " is damaged by " + effectName + " effect.");
        calculateAndDealDamage(damage);
    }

    private void calculateAndDealDamage(Damage damage) {
        int resultDamageAmount = damage.getAmount();
        //process damage through resistances
        Resistance resistance = getResistance(damage.getDamageType());

        //other resistances
        if (resistance != null) {
            resultDamageAmount = calculateResistantDamage(resultDamageAmount, resistance);
        }

        damage.setAmount(resultDamageAmount);

        //lasting effects
        for (LastingEffect lastingEffect : getLastingEffects()) {
            if (lastingEffect instanceof IncomingDamageEffect) {
                IncomingDamageEffect idf = (IncomingDamageEffect) lastingEffect;
                damage = idf.modifyIncomingDamage(damage);
            }
        }

        //deal damage
        health = health - damage.getAmount();
        if (health < 0) {
            health = 0;
        }
        print(getName() + " takes damage " + damage.getAmount() + " and has " + getHealthString() + " hp");
    }

    private static int calculateResistantDamage(int resultDamageAmount, Resistance resistance) {
        double resMult = getResistanceMultiplier(resistance.getStrength());
        Logger.dlog(resistance.getType() + " resistance multiplier = " + Util.formatDouble(resMult, 2));
        double damage = resultDamageAmount - resultDamageAmount * getResistanceMultiplier(resistance.getStrength());
        Logger.dlog("Damage reduced to = " + Util.formatDouble(damage, 2));
        return (int) Math.round(resultDamageAmount - resultDamageAmount * getResistanceMultiplier(resistance.getStrength()));
    }

    private static double getResistanceMultiplier(int resistanceStrength) {
        double def = (resistanceStrength * RESISTANCE_FORMULA_MULTIPLIER) / (1 + resistanceStrength * RESISTANCE_FORMULA_MULTIPLIER);
        return def;
    }

    protected abstract Damage getBaseUnmodifiedAttack();

    public String getManaString() {
        return mana + "/" + maxMana;
    }

    public int getHealth() {
        return health;
    }

    public int getMaxHealth() {
        //TODO use this method instead of direct field access; move through effects to get modified maxHealth
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
