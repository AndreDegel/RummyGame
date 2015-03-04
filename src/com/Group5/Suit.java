package com.Group5;

/**
 * Created by DayDay on 3/3/2015.
 */
public enum Suit {
    Spades((char)9824),
    Clubs((char)9827),
    Hearts((char)9829),
    Diamonds((char)9830);

    private char Symbol;

    private Suit(char symbol) {
        this.Symbol = symbol;
    }

    public char getSymbol() {
        return this.Symbol;
    }
}
