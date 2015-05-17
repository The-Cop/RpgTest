package com.thecop.rpgtest.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Iterator;
import java.util.List;

/**
 * Created by TheCop on 21.03.2015.
 */
public class Util {
    public static String formatDouble(double x, int digitsAfterDot) {
        if (digitsAfterDot < 1) {
            throw new IllegalArgumentException("digitsAfterDot must be greater than 0");
        }
        DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols();
        otherSymbols.setDecimalSeparator('.');
        StringBuilder formatSb = new StringBuilder("#.");
        for (int i = 1; i <= digitsAfterDot; i++) {
            formatSb.append("#");
        }
        DecimalFormat df = new DecimalFormat(formatSb.toString(), otherSymbols);
        return df.format(x);
    }

    public static String input() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            return br.readLine().toLowerCase();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }
        return null;
    }

    public static String listToString(List list) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        if (list == null) {
            return sb.toString();
        }
        Iterator i = list.iterator();
        while (i.hasNext()) {
            sb.append(i.next().toString());
            if (i.hasNext()) {
                sb.append(",");
            }
        }
        sb.append("]");
        return sb.toString();
    }

}
