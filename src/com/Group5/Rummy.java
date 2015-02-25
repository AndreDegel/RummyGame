package com.Group5;

import java.util.LinkedList;

public class Rummy {

    public static void main(String[] args) {
        //create a new deck object
        Deck deck = new Deck();
        //Fill it
        LinkedList<Cards> newDeck = deck.makeDeck();
        //Shuffle it
        deck.shuffle(newDeck);
        //Deal/Show first card of Stack
        deck.deal(newDeck);
    }
}
