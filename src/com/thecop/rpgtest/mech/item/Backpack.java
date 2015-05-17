package com.thecop.rpgtest.mech.item;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by TheCop on 16.05.2015.
 */
public class Backpack {
    public static final int BACKPACK_SIZE = 15;
    private List<Item> items = new ArrayList<>(BACKPACK_SIZE);

    /**
     * @param item item to add
     * @return true if item is added; false if item can not be added
     */
    public boolean put(Item item) {
        if (items.size() >= BACKPACK_SIZE) {
            return false;
        }
        items.add(item);
        return true;
    }

    /**
     * adds an item without any constraints
     * @param item
     */
    public void putQuest(Item item) {
        items.add(item);
    }

    public Item takeOut(int position) {
//        try {
        Item i = items.remove(position);
        return i;
//        }catch (IndexOutOfBoundsException e){
        //TODO catch IndexOutOfBoundsException
//        }
    }

    /**
     * Checks if backpack is available to exchange item:
     * take one out and put one another.
     * Can be impossible if items count is more than maximum due to quest items
     */
    public boolean canExchange(){
        return (items.size()-1) >= BACKPACK_SIZE;
    }


    public List<Item> getItems() {
        return Collections.unmodifiableList(items);
    }


}
