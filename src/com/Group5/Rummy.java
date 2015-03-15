package com.Group5;

import java.util.*;

//TODO: check validation when last 3 cards are melded and layout


public class Rummy {

    private static Scanner scanner;   //Global scanner used for all input

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        Player player1 = new Player();
        PCPlayer pc = new PCPlayer();

        //create a new deck object
        Deck deck = new Deck();
        //Fill it
        LinkedList<Cards> newDeck = deck.makeDeck();
        //Shuffle it
        deck.shuffle(newDeck);
        //deal 10 cards to player
        for (int x = 0; x < 10; x++){
            player1.addToHand(deck.deal(newDeck));
            pc.addToHand(deck.deal(newDeck));
        }
        //start discard pile and stock pile
        StockPile.setStockPile(newDeck);
        DiscardPile.addToDiscard(StockPile.Draw());




        //start the game loop and run as long as player has cards
        while(true){
        //(!player1.getPlayerHand().getAllCards().isEmpty() ||
                //!pc.playerHand.getAllCards().isEmpty()){
            if (player1.getPlayerHand().getAllCards().isEmpty()){break;}


            pc.Play();

            if (pc.playerHand.getAllCards().isEmpty()){break;}

            //check that there are still cards in the stockpile if not turn discard
            if (StockPile.getStockPile().isEmpty()){
                DiscardPile.toStockPile();
            }
            if (player1.getPlayerHand().getAllCards().isEmpty()){break;}
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
            //make one meld per turn
            boolean turnMeld = false;
            //let the player meld, layoff, or just discard in his turn
            while (turn) {
                if (player1.getPlayerHand().getAllCards().isEmpty()){break;}

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
                    if (turnMeld){
                        System.out.println("Sorry, but you can just meld once per turn!");
                    }

                    else {
                        ArrayList<Cards> meld = new ArrayList<Cards>();
                        while (!player1.getPlayerHand().getAllCards().isEmpty()) {
                            //determine number to cancel
                            int endRange = player1.getPlayerHand().getAllCards().size() + 1;
                            System.out.println("Choose at least 3 cards to meld. Card must be +-1 of current to meld!");
                            System.out.println(endRange + "-to cancel all and return to menu (Will also cancel current meld and put at the end of the hand)");
                            //show cards with corresponding input numbers
                            showInput(player1.getPlayerHand().getAllCards());

                            //make exit possible and return to menu
                            int i = isWithinRange(1, endRange);
                            if (i == endRange) {
                                //if player started melding and decided differently
                                //empty meld array and put back into hand
                                if (!meld.isEmpty()) {
                                    for (Cards c : meld) {
                                        player1.getPlayerHand().AddCard(c);
                                    }
                                    meld.clear();
                                }
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
                                    //meld the array
                                    player1.melding(meld);
                                    turnMeld = true;
                                    break;
                                }
                            }
                        }
                    }

                //Lay off cards to table
                }
                else if (play == 2) {
                    //TODO: may need clean up....collect one card per turn may not need array.
                    //collect cards to lay off
                    ArrayList<Cards> collect = new ArrayList<Cards>();
                    if (Table.getTableCards().isEmpty()) {
                        System.out.println("Sorry but there are no cards to add to yet");

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
                                    //System.out.println("Your Cards: " + player1.getPlayerHand().getAllCards().toString());
                                    System.out.println("What card do you want to add here?");
                                    showInput(player1.getPlayerHand().getAllCards());
                                    //way to cancel out of current lay out.
                                    int endLayOut = player1.getPlayerHand().getAllCards().size() + 1;
                                    System.out.println(endLayOut + "-to cancel and return to table view!");
                                    //get the current table selection for the players meld method
                                    ArrayList<Cards> layOff = Table.getTableCards().get(a - 1);
                                    //check users input
                                    int inp = isWithinRange(1, endLayOut);
                                    //way to get out of current layout and back to table menu
                                    if (inp == endLayOut) {
                                        break;
                                    } else {
                                        Cards l = player1.getPlayerHand().getCard(inp - 1);

                                        if (layOff.get(0).getSuit() == l.getSuit()) {
                                            //if the current cards value is one greater then the last one in the array
                                            if (layOff.get(layOff.size() - 1).getRank().getValue() + 1 == l.getRank().getValue()) {
                                                collect.add(l);    //put to lay-off array
                                                player1.getPlayerHand().Remove(l);  //remove from hand
                                            }
                                            //if the current cards value is one less then the first one in the array
                                            else if (layOff.get(0).getRank().getValue() - 1 == l.getRank().getValue()) {
                                                collect.add(l);    //put to meld list
                                                player1.getPlayerHand().Remove(l);  //remove from hand
                                            }
                                        } else if (layOff.get(0).getRank() == l.getRank()) {
                                            collect.add(l);    //put to meld list
                                            player1.getPlayerHand().Remove(l);  //remove from hand
                                        }
                                        //else retry
                                        else {
                                            System.out.println("The cards must have the same suit or rank!");
                                            continue;
                                        }
                                        player1.layOff(collect, a-1);
                                        break;
                                    }
                                }
                            }
                        }

                    }
                }

                //Discard and finish the turn
                else {
                    System.out.println("What card you would like to discard? Choose by position!");
                    //show cards to discard
                    showInput(player1.getPlayerHand().getAllCards());
                    //let the player put in a number for the card to discard
                    int discard = isWithinRange(1, player1.getPlayerHand().getAllCards().size());
                    //discard that card
                    player1.discardFromHand(player1.getPlayerHand().getCard(discard - 1));
                    turn = false;
                }
            }

        }

        if (player1.getPlayerHand().getAllCards().isEmpty()) {
            System.out.println("Congratulations! You Win!");
        }
        else{
            System.out.println("Sorry! The Computer won!");
        }
        System.out.println("Thank you for playing.");
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
     *
     *
     * if pc hand is empty: GAME OVER! PC wins
     * }
     **/

    //Validation methods

    private static void showInput(ArrayList<Cards> toChoose){
        String input = " ";
        for (int x = 1; x <= toChoose.size(); x++) {
            input += x +"-" + toChoose.get(x-1).toString() +", ";
        }
        System.out.println(input);
    }

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
