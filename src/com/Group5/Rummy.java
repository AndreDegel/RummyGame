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
        StockPile.setStockPile(newDeck);
        DiscardPile.addToDiscard(StockPile.Draw());

        //

        //start the game loop and run as long as player has cards
        while (!player1.getPlayerHand().getAllCards().isEmpty()){
            //check that there are still cards in the stockpile if not turn discard
            if (StockPile.getStockPile().isEmpty()){
                DiscardPile.toStockPile();
            }
            System.out.println("Players turn.");
            //show cards
            System.out.println(player1.getPlayerHand().getAllCards().toString());
            // show top of discard
            System.out.println("The discard pile has a: " + DiscardPile.ShowTopCard());
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
            //safeguard a way back if selection is not correct
            boolean turn = true;
            while (turn) {
                //Show Table
                System.out.println("On the table is: " + Table.getTableCards().toString());
                //show cards
                System.out.println("You have: " + player1.getPlayerHand().getAllCards().toString());
                //give play options
                System.out.println("What would you like to do?");
                System.out.println("1-Meld\n2-Lay off\n3-Discard and end Turn");
                //validate input
                int play = isWithinRange(1, 3);
                //play accordingly
                if (play == 1) {
                    ArrayList<Cards> meld = new ArrayList<Cards>();
                    while (!player1.getPlayerHand().getAllCards().isEmpty()) {
                        //determine number to cancel
                        int endRange = player1.getPlayerHand().getAllCards().size() + 1;
                        System.out.println("Choose at least 3 cards to meld. Card must be +-1 of current to meld!");
                        System.out.println(endRange + "-to cancel and return to menu");
                        //show cards
                        System.out.println(player1.getPlayerHand().getAllCards().toString());
                        //make exit possible and return to menu
                        int i = isWithinRange(1, endRange);
                        if (i == endRange) {
                            break;
                        } else {

                            Cards m = player1.getPlayerHand().getCard(i - 1);
                            //if the meld array is empty add card
                            if (meld.isEmpty()) {
                                meld.add(m);
                                player1.getPlayerHand().Remove(m);
                            }
                            //if the Suit is the same add
                            else if (meld.get(0).getSuit() == m.getSuit()) {
                                //if the current cards value is one greater then the last one in the array
                                if (meld.get(meld.size() - 1).getRank().getValue() + 1 == m.getRank().getValue()) {
                                    meld.add(m);    //put to meld list
                                    player1.getPlayerHand().Remove(m);  //remove from hand
                                    Collections.sort(meld); //sort meld list
                                }
                                //if the current cards value is one less then the first one in the array
                                else if (meld.get(0).getRank().getValue() - 1 == m.getRank().getValue()) {
                                    meld.add(m);    //put to meld list
                                    player1.getPlayerHand().Remove(m);  //remove from hand
                                    Collections.sort(meld); //sort meld list
                                }
                            }
                            //if the rank is the same add
                            else if (meld.get(0).getRank() == m.getRank()) {
                                meld.add(m);
                                player1.getPlayerHand().Remove(m);
                            }
                            //else retry
                            else {
                                System.out.println("The cards must have the same suit or rank!");
                                continue;
                            }
                            //if player has at least 3 cards give option to continue or quit.
                            if (meld.size() == 3 || player1.getPlayerHand().getAllCards().isEmpty()) {
                                System.out.println("1-Meld now\n2-Add more cards");
                                int a = isWithinRange(1, 2);
                                if (a == 1) {
                                    //meld the array
                                    player1.melding(meld);
                                    //Discard a card
                                    System.out.println("You have to discard a Card now");
                                    //show cards to discard
                                    System.out.println(player1.getPlayerHand().getAllCards().toString());
                                    System.out.println("What card you would like to discard? Choose by position!");
                                    //let the player put in a number for the card to discard
                                    int discard = isWithinRange(1, player1.getPlayerHand().getAllCards().size());
                                    //discard that card
                                    player1.discardFromHand(player1.getPlayerHand().getCard(discard - 1));
                                    turn = false;
                                    break;
                                } else {
                                    continue;
                                }
                            }
                        }
                    }
                    //TODO: print to test table....remove before finish
                    System.out.println(Table.getTableCards().toString());
                //Lay off cards to table
                } else if (play == 2) {
                    //TODO: may need clean up

                    if (Table.getTableCards().isEmpty()) {
                        System.out.println("Sorry but there are no cards to add to yet");
                        continue;
                    } else {
                        while (true) {
                            //show player card
                            System.out.println("Your Cards: " + player1.getPlayerHand().getAllCards().toString());
                            System.out.println("Which cards do you want to add to?");
                            for (int x = 1; x <= Table.getTableCards().size(); x++) {
                                System.out.println(x + "-" + Table.getTableCards().get(x - 1).toString());
                            }
                            System.out.println(Table.getTableCards().size() + 1 + "-End");
                            int a = isWithinRange(1, Table.getTableCards().size() + 1);

                            if (a == Table.getTableCards().size() + 1) {
                                break;
                            } else {
                                while (true) {
                                    //show player card and to add
                                    System.out.println("Table: " + Table.getTableCards().get(a - 1));
                                    System.out.println("Your Cards: " + player1.getPlayerHand().getAllCards().toString());
                                    System.out.println("What card do you want to add here?");
                                    ArrayList<Cards> layOff = Table.getTableCards().get(a - 1);
                                    Cards l = player1.getPlayerHand().getCard(isWithinRange(1, player1.getPlayerHand().getAllCards().size()) - 1);
                                    if (layOff.get(0).getSuit() == l.getSuit()) {
                                        //if the current cards value is one greater then the last one in the array
                                        if (layOff.get(layOff.size() - 1).getRank().getValue() + 1 == l.getRank().getValue()) {
                                            layOff.add(l);    //put to lay-off array
                                            player1.getPlayerHand().Remove(l);  //remove from hand
                                        }
                                        //if the current cards value is one less then the first one in the array
                                        else if (layOff.get(0).getRank().getValue() - 1 == l.getRank().getValue()) {
                                            layOff.add(l);    //put to meld list
                                            player1.getPlayerHand().Remove(l);  //remove from hand
                                        }
                                    } else if (layOff.get(0).getRank() == l.getRank()) {
                                        layOff.add(l);    //put to meld list
                                        player1.getPlayerHand().Remove(l);  //remove from hand
                                    }
                                    //else retry
                                    else {
                                        System.out.println("The cards must have the same suit or rank!");
                                        continue;
                                    }
                                    System.out.println("1-Add more to this meld\n2-See other melds or exit");
                                    int b = isWithinRange(1, 2);
                                    if (b == 1) {
                                        continue;
                                    } else {
                                        break;
                                    }
                                }
                            }
                            //show player card and to add

                            System.out.println("1-Do you want to see if you can lay off to other\n2-Finish and discard");
                            int c = isWithinRange(1, 2);
                            if (c == 1) {
                                continue;
                            } else {
                                //Discard a card
                                System.out.println("You have to discard a Card now");
                                //show cards to discard
                                System.out.println(player1.getPlayerHand().getAllCards().toString());
                                System.out.println("What card you would like to discard? Choose by position!");
                                //let the player put in a number for the card to discard
                                int discard = isWithinRange(1, player1.getPlayerHand().getAllCards().size());
                                //discard that card
                                player1.discardFromHand(player1.getPlayerHand().getCard(discard - 1));
                                turn = false;
                                break;
                            }
                        }
                    }

                }

                //Discard and finish the turn
                else {
                    //show cards to discard
                    System.out.println(player1.getPlayerHand().getAllCards().toString());
                    System.out.println("What card you would like to discard? Choose by position!");
                    //let the player put in a number for the card to discard
                    int discard = isWithinRange(1, player1.getPlayerHand().getAllCards().size());
                    //discard that card
                    player1.discardFromHand(player1.getPlayerHand().getCard(discard - 1));
                    turn = false;
                }
            }
            System.out.println("You Win thanks for playing.");

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
