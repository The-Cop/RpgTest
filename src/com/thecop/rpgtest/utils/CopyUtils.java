package com.thecop.rpgtest.utils;

import com.thecop.rpgtest.mech.effect.Effect;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by TheCop on 11.05.2015.
 */
public class CopyUtils {
    public static List<Effect> getCopy(List<Effect> list) {

        if (list == null) {
            return Collections.emptyList();
        }
        List<Effect> result = new ArrayList<>();
        for (Effect t : list) {
            result.add((Effect) t.getCopy());
        }
        return result;
    }
}
