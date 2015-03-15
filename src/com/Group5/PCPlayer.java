package com.Group5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * Player class that represents the Computer player (AI)
 *
 * Created by DayDay on 3/12/2015.
 */
public class PCPlayer extends Player {

    public PCPlayer() {
        this.playerHand = new Hand();
    }

    /**
     * PC AI: to be used every time it's the PC player's turn
     */
    public void Play() {
        //TODO: Add AI playing commands

        //Draw
        Draw();

        ArrayList<Cards> hand = playerHand.getAllCards();

        ArrayList<Cards> spades = new ArrayList<Cards>();
        ArrayList<Cards> clubs = new ArrayList<Cards>();
        ArrayList<Cards> hearts = new ArrayList<Cards>();
        ArrayList<Cards> diamonds = new ArrayList<Cards>();

        //Separate hand cards by suit
        for (Cards card : hand) {
            Suit cardSuit = card.getSuit();

            switch (cardSuit) {
                case Spades:
                    spades.add(card);
                    break;
                case Clubs:
                    clubs.add(card);
                    break;
                case Hearts:
                    hearts.add(card);
                    break;
                case Diamonds:
                    diamonds.add(card);
                    break;
            }
        }

        //Sort each group of cards
        if (!spades.isEmpty()) {
            Collections.sort(spades);
        }
        if (!clubs.isEmpty()) {
            Collections.sort(clubs);
        }
        if (!hearts.isEmpty()) {
            Collections.sort(hearts);
        }
        if (!diamonds.isEmpty()) {
            Collections.sort(diamonds);
        }
        HashMap<Suit, ArrayList<Cards>> suitGroups = new HashMap<Suit, ArrayList<Cards>>();
        suitGroups.put(Suit.Spades, spades);
        suitGroups.put(Suit.Clubs, clubs);
        suitGroups.put(Suit.Hearts, hearts);
        suitGroups.put(Suit.Diamonds, diamonds);


        //put the suit arrays in a list of lists for better checking.
        ArrayList<ArrayList<Cards>> suitsList = new ArrayList<ArrayList<Cards>>();
        suitsList.add(clubs);
        suitsList.add(spades);
        suitsList.add(hearts);
        suitsList.add(diamonds);

        //check what it does so far TODO remove later
        System.out.println(playerHand.getAllCards().toString());
        System.out.println(spades.toString());
        System.out.println(clubs.toString());
        System.out.println(hearts.toString());
        System.out.println(diamonds.toString());


        //TODO: in the end check for same ranks
        //Meld if necessary

        //check if run worked otherwise look for set
        boolean run = false;
        //make arraylist to put meld in
        ArrayList<Cards> meld = new ArrayList<Cards>();
        //go through the suit Arrays
        for (ArrayList<Cards> s : suitsList){
            //if they have at least 3 cards we can check if we can meld them
            if (s.size() >=3){
                for (Cards c : s){
                                //start with the first card in the array to compare the rest
                                if (meld.isEmpty()) {
                                    meld.add(c);
                                    playerHand.getAllCards().remove(c);
                                }
                                //if the current cards value is one greater then the last one in the array
                                else if (meld.get(meld.size() - 1).getRank().getValue() + 1 == c.getRank().getValue()) {
                                    meld.add(c);    //put to meld list
                                    playerHand.getAllCards().remove(c);  //remove from hand
                                    Collections.sort(meld); //sort meld list
                                }
                                //if the current cards value is one less then the first one in the array
                                else if (meld.get(0).getRank().getValue() - 1 == c.getRank().getValue()) {
                                    meld.add(c);    //put to meld list
                                    playerHand.getAllCards().remove(c);  //remove from hand
                                    Collections.sort(meld); //sort meld list
                                }
                                //else retry
                                else {
                                    //if we found at least 3 cards to meld stop searching this array
                                    if (meld.size()>=3){
                                        break;
                                    }
                                    //otherwise put the cards back to the hand if we took them out
                                    else {
                                        for (Cards a : meld) {
                                            if (!playerHand.getAllCards().contains(a)) {
                                                playerHand.AddCard(a);
                                            }
                                        }
                                        //clear the currend meld list and restart from the current card
                                        meld.clear();
                                        meld.add(c);                            //add current card
                                        playerHand.getAllCards().remove(c);     //remove from hand
                                    }
                                }

                }
                //if we previously or at the end of the last array found
                //something to meld then meld it and go on
                if (meld.size() >= 3){
                    Table.newMeld(meld);
                    run = true;
                    break;
                }
                //otherwise we are at the end of the current array
                //put last checked cards back in the hand and clear the meld
                else {
                    for (Cards a : meld) {
                        if (!playerHand.getAllCards().contains(a)) {
                            playerHand.AddCard(a);
                        }
                    }
                    meld.clear();
                }
            }
        }

        //check for a set if no run
        if (!run){
            //sort the hand to better check for sets
            Collections.sort(playerHand.getAllCards());

            //TODO: check later (after work) may can be used meld
            ArrayList<Cards> set = new ArrayList<Cards>();
            for (Cards s : playerHand.getAllCards()){
                //start with the first card in the array to compare the rest
                if (set.isEmpty()) {
                    set.add(s);
                }
                //if the rank is the same add
                else if (set.get(0).getRank() == s.getRank()) {
                    set.add(s);
                }
                //otherwise put the cards back to the hand if we took them out
                else {
                    //clear the current set list and restart from the current card
                    set.clear();
                    set.add(s);
                }
                //if we have a set of 3 (first occurrence)
                if (set.size() >= 3){
                    //meld it
                    Table.newMeld(set);
                    //and remove the cards from the set out of the hand
                    for (Cards a : set) {
                        playerHand.getAllCards().remove(a);
                    }
                    //stop loop
                    break;
                }
            }

        }

        //TODO remove later
        System.out.println(Table.getTableCards().toString());
        System.out.println(playerHand.getAllCards().toString());




        //Lay off if necessary


        //Discard

    }

    private void Draw() {
        //look at top card on discard pile
        Cards topCard = DiscardPile.ShowTopCard();
        Rank topCardRank = topCard.getRank();
        Suit topCardSuit = topCard.getSuit();



        if (
                hasRank(topCardRank) ||                                 //hand contains same rank as top card
                hasCard(new Cards(
                        Rank.fromValue(topCardRank.getValue() + 1),     //or card with next rank and same suit
                        topCardSuit)) ||
                hasCard(new Cards(
                        Rank.fromValue(topCardRank.getValue() - 1),     //or card with previous rank and same suit
                        topCardSuit))
                ) {
            playerHand.AddCard(DiscardPile.Draw());
        } else {
            playerHand.AddCard(StockPile.Draw());
        }
    }

    private void Discard(HashMap<Suit, ArrayList<Cards>> suitGroups) {
        ArrayList<Cards> discardable = new ArrayList<Cards>();
        ArrayList<Cards> reserve = new ArrayList<Cards>();

        //TODO: figure out which card to discard
        //TODO: sort ArrayList so that least desired cards are at the front

        //Measure difference between same-suit cards and act accordingly
        for (Suit suit : suitGroups.keySet()) {
            ArrayList<Cards> cards = suitGroups.get(suit);

            HashMap<String, ArrayList<Cards>> subgroups = new HashMap<String, ArrayList<Cards>>();

            int prevCardIndex = 0;                                      //start first card in group

            for (int currCardIndex = 1;                                 //loop over the rest of the cards
                 currCardIndex < cards.size();                          //in the group starting with
                 currCardIndex++) {                                     //the second card

                Cards prevCard = cards.get(prevCardIndex);
                Cards currCard = cards.get(currCardIndex);

                int prevCardRankValue = prevCard.getRank().getValue();
                int currCardRankValue = currCard.getRank().getValue();

                int rankDiff = currCardRankValue - prevCardRankValue;

                if (rankDiff <= 2) {
                    //ArrayList<Cards>
                    String subgroupName = null;
                    for (String name : subgroups.keySet()) {
                        ArrayList<Cards> subgroup = subgroups.get(name);
                        if (subgroup.contains(prevCard)) {
                            subgroupName = name;
                        }
                    }

                    if (subgroupName == null) {
                        //TODO
                    }
                }

                prevCardIndex = currCardIndex;
            }
        }
    }

    private boolean hasCard(Cards c) {
        return playerHand.getAllCards().contains(c);
    }

    private boolean hasRank(Rank r) {
        boolean doesHaveRank = false;

        int i = 0;
        while (i < playerHand.getAllCards().size()) {
            Cards c = playerHand.getCard(i);
            if (c.getRank() == r) {
                doesHaveRank = true;
                break;
            }
            i++;
        }

        return doesHaveRank;
    }

    private boolean hasSuit(Suit s) {
        boolean doesHaveSuit = false;

        int i = 0;
        while (i < playerHand.getAllCards().size()) {
            Cards c = playerHand.getCard(i);
            if (c.getSuit() == s) {
                doesHaveSuit = true;
                break;
            }
            i++;
        }

        return doesHaveSuit;
    }
}
