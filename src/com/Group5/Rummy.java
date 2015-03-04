package com.Group5;

import java.util.LinkedList;

//TODO: Add Code to Main(or pseudo)
//TODO: Add Table class that has a "List of lists" with the things on the table, and an add method
//that takes int for which list(later also Validation).

public class Rummy {

    public static void main(String[] args) {
        //create a new deck object
        Deck deck = new Deck();
        //Fill it
        LinkedList<Cards> newDeck = deck.makeDeck();
        //Shuffle it
        deck.shuffle(newDeck);


        //good till here:
        //-----------------------------------------
        //Deal/Show first card of Stack
        deck.deal(newDeck);

    }
}
