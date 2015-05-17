package com.thecop.rpgtest.object;

import com.thecop.rpgtest.mech.item.Backpack;

import java.util.Random;

/**
 * Created by TheCop on 16.05.2015.
 */
public class PlayerParty extends Party<PlayerChar>{
    private Backpack backpack = new Backpack();
    private Random random = new Random();
    //TODO add playerchar a link to its party;
    //TODO add playerchar ability to exchange items between backpack and inventory(? worn items)


    public Backpack getBackpack() {
        return backpack;
    }

    public PlayerChar getRandomPlayer(){
        return chars.get(random.nextInt(chars.size()));
    }
}
