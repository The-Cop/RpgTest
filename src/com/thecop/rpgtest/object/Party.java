package com.thecop.rpgtest.object;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by TheCop on 16.05.2015.
 */
public class Party<T extends GameChar> {
    protected List<T> chars = new ArrayList<>();

    public boolean isAlive() {
        for (GameChar gameChar : chars) {
            if (gameChar.isAlive()) {
                return true;
            }
        }
        return false;
    }

    public String getNames() {
        StringBuilder sb = new StringBuilder();
        Iterator i = chars.iterator();
        while (i.hasNext()) {
            GameChar next = (GameChar) i.next();
            sb.append(next.getName());
            if (i.hasNext()) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }

    public List<T> getChars() {
        return chars;
    }
}
