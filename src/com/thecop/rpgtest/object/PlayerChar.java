package com.thecop.rpgtest.object;

import com.thecop.rpgtest.mech.damage.AttackRange;
import com.thecop.rpgtest.mech.damage.Damage;
import com.thecop.rpgtest.mech.damage.DamageType;
import com.thecop.rpgtest.mech.player.PlayerAction;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Created by Admin on 19.03.2015.
 */
public class PlayerChar extends GameChar {

    //TODO replace player's baseAttackDamageAmount with somenthing dependent in it's stats/attributes
    private int baseAttackDamageAmount;

    public PlayerChar(String name, int health, int mana, AttackRange baseAttackRange, int baseAttackDamageAmount) {
        super(name, health, mana, baseAttackRange);
        this.baseAttackDamageAmount = baseAttackDamageAmount;
    }

    public void performAction(PlayerAction action) {
        //TODO handle target==null
        switch (action.getType()) {
            case USUAL_ATTACK:
                attack(action.getTarget());
                return;
            case SPELL:
                castSpell(action.getSpell(), action.getTarget(), action.getPartyTarget());
                return;
            case RUN:
                //TODO implement runaway
                throw new NotImplementedException();
        }
    }


    @Override
    protected Damage getBaseUnmodifiedAttack() {
        return new Damage(baseAttackDamageAmount, DamageType.PHYSICAL);
    }
}
