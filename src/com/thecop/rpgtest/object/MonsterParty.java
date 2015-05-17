package com.thecop.rpgtest.object;

import com.thecop.rpgtest.mech.item.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TheCop on 16.05.2015.
 */
public class MonsterParty extends Party<Monster>{
    //TODO implement drops
    private List<Item> drop = new ArrayList<>();
    public List<Item> getDrop() {
        return drop;
    }
}
