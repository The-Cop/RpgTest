package com.thecop.rpgtest.object.impl;

import com.thecop.rpgtest.mech.item.Item;
import com.thecop.rpgtest.object.Party;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TheCop on 16.05.2015.
 */
public class MonsterParty extends Party<Monster> {
    //TODO implement drops
    private List<Item> drop = new ArrayList<>();

    public List<Item> getDrop() {
        return drop;
    }
}
