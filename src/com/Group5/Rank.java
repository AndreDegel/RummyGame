package com.Group5;

/**
 * Created by DayDay on 3/3/2015.
 */
public enum Rank {
    Ace(1),
    Two(2),
    Three(3),
    Four(4),
    Five(5),
    Six(6),
    Seven(7),
    Eight(8),
    Nine(9),
    Ten(10),
    Jack(11),
    Queen(12),
    King(13);

    private final int VALUE;

    private Rank(int val) {
        this.VALUE = val;
    }

    public int getValue() {
        return this.VALUE;
    }
}
