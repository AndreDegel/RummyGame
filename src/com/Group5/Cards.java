package com.Group5;

public class Cards{ //implements Comparable<Cards> {
    //Data for a card object
    private Rank rank;
    private Suit suit;

    //Constructor
    public Cards(Rank rank, Suit suit){
        this.rank = rank;
        this.suit = suit;
    }

    //Method that returns the string representation of a card.
    @Override
    public String toString(){
        int rank = this.rank.getValue();
        char suit = this.suit.getSymbol();
        String color = this.suit.getColor();

        return color + Integer.toString(rank) + suit + "\u001B[0m";
    }
/*
    public int compareTo(Cards otherCard) {
        if (this.rank)
    }*/
}
