package com.Group5;

import java.util.*;

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
        //sort the deck.
        Collections.sort(newDeck);

        System.out.println(newDeck.toString());
    }

    /**
     * Start game:
     *
     * deal 10 cards to each player
     * deal next card to start discard pile
     * place remainder of cards onto stock pile
     *
     * while(true) {
     *
     * //player plays
     * if stock pile is empty:
     * top discard pile card stays
     * remainder of discard pile goes to stock
     *
     * player draws from stock or discard.
     * "would you like to meld, lay off, or discard?"
     *
     * meld:
     * ...
     * "choose a card to discard"
     *
     * lay off:
     * ...
     * "choose a card to discard"
     *
     * player discards.
     *
     * if player hand is empty: GAME OVER! Player wins
     *
     * //TODO: pc plays
     *
     * if pc hand is empty: GAME OVER! PC wins
     * }
     **/
}
