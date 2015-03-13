package com.Group5;

import java.util.*;

//TODO: Finish Main
//TODO: Create AI
//TODO: Make Validation......Validate discards is empty.


public class Rummy {

    private static Scanner scanner;   //Global scanner used for all input

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        Player player1 = new Player();

        //create a new deck object
        Deck deck = new Deck();
        //Fill it
        LinkedList<Cards> newDeck = deck.makeDeck();
        //Shuffle it
        deck.shuffle(newDeck);
        //deal 10 cards to player
        for (int x = 0; x < 10; x++){
            player1.addToHand(deck.deal(newDeck));
        }
        //start discard pile and stock pile
        StockPile.stockPile = newDeck;
        DiscardPile.addToDiscard(StockPile.Draw());

        //

        //start the game loop
        while (!player1.getPlayerHand().getAllCards().isEmpty()){
            //check that there are still cards in the stockpile if not turn discard
            if (StockPile.stockPile.isEmpty()){
                DiscardPile.toStockPile();
            }
            System.out.println("Players turn.");
            //Decide where to draw from
            System.out.println("Where do you would like to draw from?\n1-Stock Pile\n2-Discard Pile");
            //validate input
            int answer = isWithinRange(1,2);
            //draw accordingly
            if (answer == 1){
                player1.addToHand(StockPile.Draw());
            }
            else if (answer == 2){
                player1.addToHand(DiscardPile.Draw());
            }
            //show cards
            System.out.println(player1.getPlayerHand().getAllCards().toString());



        }

/*
        //Deal/Show first card of Stack
        deck.deal(newDeck);
        //sort the deck.
        Collections.sort(newDeck);

        System.out.println(newDeck.toString());*/
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

    //Validation methods

    private static int isWithinRange(int min, int max) {

        while (true) {
            try {
                String stringInput = scanner.nextLine();
                int intInput = Integer.parseInt(stringInput);
                if (intInput >= min && intInput <= max) {
                    return intInput;
                } else {
                    System.out.println("Please enter a positive number, within the range " + min + " " + max);
                    continue;
                }
            } catch (NumberFormatException ime) {
                System.out.println("Please type a positive number");
            }
        }
    }
}
