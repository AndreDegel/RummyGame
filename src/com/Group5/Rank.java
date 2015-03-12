package com.Group5;

import java.util.Random;

/**
 * Created by DayDay on 3/3/2015.
 */
public enum Rank {
    Ace,
    Two,
    Three,
    Four,
    Five,
    Six,
    Seven,
    Eight,
    Nine,
    Ten,
    Jack,
    Queen,
    King;

    private final int VALUE;

    private Rank() {
        this.VALUE = this.ordinal() + 1;
    }

    public static Rank fromValue(int value) {
        if (value < 1 || value > 13) {
            return null;
        } else {
            return Rank.values()[value - 1];
        }
    }

    public int getValue() {
        return this.VALUE;
    }
}
