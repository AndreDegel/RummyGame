package com.Group5;

/**
 * Created by DayDay on 3/3/2015.
 */
public enum Suit {
    Spades((char)9824, "\u001B[30m"),
    Clubs((char)9827, "\u001B[30m"),
    Hearts((char)9829, "\u001B[31m"),
    Diamonds((char)9830, "\u001B[31m");

    private char Symbol;
    private String Color;

    private Suit(char symbol, String color) {
        this.Symbol = symbol;
        this.Color = color;
    }

    public char getSymbol() {
        return this.Symbol;
    }

    public String getColor() {
        return this.Color;
    }

    public int getSortOrder() {
        return this.ordinal();
    }
}
