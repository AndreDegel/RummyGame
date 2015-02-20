package com.Group5;

/**
 * Created by Andre on 2/18/2015.
 */
public class Card {
    private String Rank;
    private String Suit;

    public Card(String rank, String suit) {
        this.Rank = rank;
        this.Suit = suit;
    }

    public String toString() {
        return this.Rank + " of " + this.Suit;
    }
}
