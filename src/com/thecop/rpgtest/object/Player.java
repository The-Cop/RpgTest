package com.thecop.rpgtest.object;

import com.thecop.rpgtest.mech.fight.*;
import com.thecop.rpgtest.mech.iteraction.Attackable;
import com.thecop.rpgtest.mech.iteraction.Damageable;
import com.thecop.rpgtest.mech.iteraction.SpellCastable;
import com.thecop.rpgtest.mech.magic.Spell;
import com.thecop.rpgtest.mech.player.PlayerAction;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Admin on 19.03.2015.
 */
public class Player implements Attackable, Damageable, SpellCastable {
    private int health;
    private int maxHealth;
    private int baseAttack;
    private String name;
    private Map<ResistanceType, Resistance> resistances;
    private List<Spell> spells;

    public Player(String name, int health, int baseAttack) {
        this.name = name;
        this.health = health;
        this.maxHealth = health;
        this.baseAttack = baseAttack;
        resistances = new HashMap<>();
        spells = new ArrayList<>();
    }

    public int getHealth() {
        return health;
    }

    public int getBaseAttack() {
        return baseAttack;
    }

    public void setResistance(Resistance resistance) {
        resistances.put(resistance.getType(), resistance);
    }

    @Override
    public void attack(Damageable target) {
        throw new NotImplementedException();
//        Damage damage = new Damage(DamageType.PHYSICAL, baseAttack);
//        DamageProcessor.damage(this, target, damage, type);
    }

    public void performAction(PlayerAction action, Damageable target){
        switch (action.getType()){
            case USUAL_ATTACK:
                Damage damage = new Damage(DamageType.PHYSICAL, baseAttack);
                DamageProcessor.damage(this, target, damage, AttackType.MELEE);
                return;
            case SPELL:
                castSpell(action.getSpell(),target);
                return;
            case RUN:
                //TODO implement runaway
                return;
        }
    }
    @Override
    public void castSpell(Spell spell, Damageable target) {
        Damage damage = new Damage(spell.getDamageType(), spell.getBaseDamage());
        DamageProcessor.damage(this, target, damage, AttackType.SPELL);
    }

    @Override
    public void takeDamage(int damageAmount) {
        health = health - damageAmount;
        if (health < 0) health = 0;
    }

    @Override
    public Resistance getResistance(ResistanceType type) {
        return resistances.get(type);
    }

    @Override
    public int getHealthLeft() {
        return getHealth();
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
        return spells;
    }



    @Override
    public boolean canCastSpell(Spell spell) {
        return true;
    }

    @Override
    public String toString() {
        return "Player{" +
                "health=" + health +
                ", maxHealth=" + maxHealth +
                ", baseAttack=" + baseAttack +
                ", name='" + name + '\'' +
                ", resistances=" + resistances +
                '}';
    }
}
