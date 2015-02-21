package com.Group5;

import java.util.LinkedList;
import java.util.Random;

public class Deck {
    //A deck containing 52 cards.
    //Data for a Deck Object
    private LinkedList<Cards> deck;
    private Cards c;
    private int RANKS[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
    private String SUITS[] = {"Spades", "Diamonds", "Hearts", "Clubs"};

    //Make a new Deck of Card Objects(52Cards) and store it in a LinkedList
    //to use as a Stack.
    public LinkedList<Cards> makeDeck(){
        this.deck = new LinkedList<Cards>();
        for (String suit : SUITS){
            for (Integer rank : RANKS){
                this.c = new Cards(rank, suit);
                this.deck.push(this.c);
            }
        }
        return this.deck;
    }

    //Method to shuffle the new generated Deck of Cards.
    public void shuffle(LinkedList<Cards> deckCards){
        Random rnd = new Random();
        //Iterate backwards through the cards
        for (int i = deckCards.size() - 1; i > 0; i--)
        {
            //generate a random number within the stack
            int index = rnd.nextInt(i + 1);
            //Generate 2 cards at two different placeholders (one random
            //the other one through the iteration cycle)
            Cards firstCard = deckCards.get(index);
            Cards secondCard = deckCards.get(i);
            // Remove the card at the current placeholder and
            // replace it with the one from the other placeholder(Swap them out).
            deckCards.remove(firstCard);
            deckCards.add(index, secondCard);
            deckCards.remove(secondCard);
            deckCards.add(i, firstCard);
        }
    }

    //Method to deal the Card, which removes the first of the Stack
    //and show it. Also check if there are still cards in the Stack
    public void deal(LinkedList<Cards> deckCards){
        //Removes and returns the top card or None
        //if the deck is empty.
        if (deckCards.isEmpty()){
            System.out.println("The deck is empty");
        }
        else {
            deckCards.pop().showCard();
        }
    }
}
