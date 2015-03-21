package com.thecop.rpgtest.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

    public static char input() {
        char result='0';
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            int c;
            while ((c = br.read()) != -1) {
                result = (char) c;
                break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
//            try {
//                br.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        }
        return result;
    }
}
