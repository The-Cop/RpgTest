package com.thecop.rpgtest.object;

import com.thecop.rpgtest.mech.fight.AttackRange;
import com.thecop.rpgtest.mech.fight.Damage;
import com.thecop.rpgtest.mech.fight.DamageProcessor;
import com.thecop.rpgtest.mech.fight.DamageType;
import com.thecop.rpgtest.mech.iteraction.Fightable;
import com.thecop.rpgtest.mech.spell.Spell;
import com.thecop.rpgtest.mech.player.PlayerAction;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Admin on 19.03.2015.
 */
public class Player extends GameChar {

    private int baseAttackDamage;
    private List<Spell> spells;

    public Player(String name, int health, int baseAttack) {
        super();
        this.name = name;
        this.health = health;
        this.maxHealth = health;
        this.baseAttackDamage = baseAttack;
        resistances = new HashMap<>();
        spells = new ArrayList<>();
    }



    public void performAction(PlayerAction action, GameChar target){
        switch (action.getType()){
            case USUAL_ATTACK:
                DamageProcessor.attackDamage(this, target, getAttack(), AttackRange.MELEE);
                return;
            case SPELL:
                castSpell(action.getSpell(),target);
                return;
            case RUN:
                //TODO implement runaway
                throw new NotImplementedException();
        }
    }
    @Override
    public void castSpell(Spell spell, Fightable target) {
        Damage damage = new Damage(spell.getDamageType(), spell.getBaseDamage());
        DamageProcessor.damage(this, target, damage, AttackRange.SPELL);
    }

    @Override
    public void attack(Fightable target) {

    }

    @Override
    protected Damage getBaseUnmodifiedAttack() {
        return new Damage(baseAttackDamage, DamageType.PHYSICAL);
    }
}
