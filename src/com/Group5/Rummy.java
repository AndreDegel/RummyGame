package com.Group5;

import java.util.Collections;
import java.util.LinkedList;

//TODO: Add Code to Main(or pseudo)

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
        //sort the deck.
        Collections.sort(newDeck);

        System.out.println(newDeck.toString());
    }
}
