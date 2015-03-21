package com.thecop.rpgtest.utils;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

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
}
