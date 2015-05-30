package com.thecop.rpgtest.display;

import com.thecop.rpgtest.mech.effect.lasting.LastingEffect;
import com.thecop.rpgtest.mech.spell.Spell;
import com.thecop.rpgtest.object.GameChar;
import com.thecop.rpgtest.object.Party;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.thecop.rpgtest.Logger.print;

/**
 * Created by TheCop on 26.03.2015.
 */
public class Display {

    public static void party(Party p) {
        PartyFormat format = defineFormat(p);
        print(format.border);
        print(String.format(format.format, getNames(p).toArray()));
        print(format.border);
        print(String.format(format.format, getHealths(p).toArray()));
        print(String.format(format.format, getManas(p).toArray()));
        print(String.format(format.format, getAttacks(p).toArray()));
        List<List<String>> effectsRows = getEffects(p);
        if (effectsRows.size() > 0) {
            print(format.border);
        }
        for (List<String> row : effectsRows) {
            print(String.format(format.format, row.toArray()));
        }
        print(format.border);
    }

    public static List<String> getNames(Party p) {
        List<String> list = new ArrayList<>();
        for (Object gcObj : p.getChars()) {
            GameChar gc = (GameChar) gcObj;
            list.add(gc.getName());
        }
        return list;
    }

    public static List<String> getHealths(Party p) {
        List<String> list = new ArrayList<>();
        for (Object gcObj : p.getChars()) {
            GameChar gc = (GameChar) gcObj;
            list.add("HP: " + gc.getHealthString());
        }
        return list;
    }

    public static List<String> getManas(Party p) {
        List<String> list = new ArrayList<>();
        for (Object gcObj : p.getChars()) {
            GameChar gc = (GameChar) gcObj;
            list.add("MP: " + gc.getManaString());
        }
        return list;
    }

    public static List<String> getAttacks(Party p) {
        List<String> list = new ArrayList<>();
        for (Object gcObj : p.getChars()) {
            GameChar gc = (GameChar) gcObj;
            list.add("ATK: " + gc.getAttack().getAmount());
        }
        return list;
    }

    public static List<List<String>> getEffects(Party p) {
        List<List<String>> rowList = new ArrayList<>();

        int position = 0;
        while (true) {
            boolean add = false;
            List<String> row = new ArrayList<>();

            for (Object gcObj : p.getChars()) {
                GameChar gc = (GameChar) gcObj;
                try {
                    LastingEffect le = gc.getLastingEffects().get(position);
                    row.add("(" + le.getLength() + ") " + le.getName());
                    add = true;
                } catch (IndexOutOfBoundsException e) {
                    row.add("");
                }
            }

            if (!add) {
                break;
            }
            rowList.add(row);
            position++;
        }
        return rowList;
    }


    private static PartyFormat defineFormat(Party p) {
        int columnWidth = 15;
        for (Object gcObj : p.getChars()) {
            GameChar gc = (GameChar) gcObj;
            if (gc.getName().length() >= columnWidth) {
                columnWidth = gc.getName().length();
            }
            if (("HP: " + gc.getHealthString()).length() >= columnWidth) {
                columnWidth = ("HP: " + gc.getHealthString()).length();
            }
            if (("MP: " + gc.getManaString()).length() >= columnWidth) {
                columnWidth = ("MP: " + gc.getManaString()).length();
            }
            if (("ATK: " + gc.getAttack().getAmount()).length() >= columnWidth) {
                columnWidth = ("ATK: " + gc.getAttack().getAmount()).length();
            }
            for (LastingEffect lastingEffect : gc.getLastingEffects()) {
                if (("(12) " + lastingEffect.getName()).length() >= columnWidth) {
                    columnWidth = ("(12) " + lastingEffect.getName()).length();
                }
            }
        }
        //"| %-15s | %-4d |%n"
        StringBuilder formatSb = new StringBuilder();
        formatSb.append("| ");
        Iterator<GameChar> iterator = p.getChars().iterator();
        while (iterator.hasNext()) {
            GameChar next = iterator.next();
            formatSb.append("%-" + columnWidth + "s");
            if (iterator.hasNext()) {
                formatSb.append(" | ");
            }
        }
        formatSb.append(" |");

        StringBuilder borderSb = new StringBuilder();
        borderSb.append("+-");
        iterator = p.getChars().iterator();
        while (iterator.hasNext()) {
            GameChar next = iterator.next();
            for (int i = 0; i < columnWidth; i++) {
                borderSb.append("-");
            }
            if (iterator.hasNext()) {
                borderSb.append("-+-");
            }
        }
        borderSb.append("-+");

        return new PartyFormat(formatSb.toString(), borderSb.toString());
    }


    public static void spellCast(Spell spell, GameChar caster, GameChar target) {
        print(caster.getName() + " casts " + spell.getName() + " on " + target.getName());
    }

    public static void lastingEffect(LastingEffect e) {
        print("\t(" + e.getLength() + ") " + e.getName() + " - " + e.getDescription());

    }

    public static void separator() {
        print("----------");
    }

    public static void separatorBig() {
        print("------------------------------");
    }

    private static class PartyFormat {
        String format;
        String border;

        public PartyFormat(String format, String border) {
            this.format = format;
            this.border = border;
        }
    }
}
