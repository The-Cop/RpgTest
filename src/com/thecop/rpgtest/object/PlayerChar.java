package com.thecop.rpgtest.object;

import com.thecop.rpgtest.mech.fight.AttackRange;
import com.thecop.rpgtest.mech.fight.Damage;
import com.thecop.rpgtest.mech.fight.DamageType;
import com.thecop.rpgtest.mech.player.PlayerAction;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Created by Admin on 19.03.2015.
 */
public class PlayerChar extends GameChar {

    private int baseAttackDamage;

    public PlayerChar(String name, int health, int mana, AttackRange baseAttackRange, int baseAttackDamage) {
        super(name, health, mana, baseAttackRange);
        this.baseAttackDamage = baseAttackDamage;
    }

    public void performAction(PlayerAction action, GameChar target){
        switch (action.getType()){
            case USUAL_ATTACK:
                attack(target);
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
    protected Damage getBaseUnmodifiedAttack() {
        return new Damage(baseAttackDamage, DamageType.PHYSICAL);
    }
}
