package com.Group5;

import java.util.ArrayList;

/**
 * Created by Abdallah on 2/19/2015.
 */
/*
TODO: Player has to draw(from pile to hand), make different plays(lay out, add on), and discard(from hand to discard pile)
TODO: Add Validator class for rules, like meld at least 3 or meld has run or group.
      add Dealer that inherits from Player and has Automated plays(and doesn't show cards)
 */
public class Player {
    //player has cards in his hand
    private Hand inHand;

    //add cards to the hand
    public void addToHand(Cards card){
        this.inHand.AddCard(card);
    }

    //discard card to discard pile
    public void discardFromHand(Cards card, DiscardPile discard){
        this.inHand.Discard(card, discard);
    }

    //Lay out 3 Cards(or take to be added to the table)
    public void melding(ArrayList<Cards> meld){
        Table.newMeld(meld);
    }

    //add one or more card(s) to a lay off on the table
    public void layOff(ArrayList<Cards> layOff, int where){
        Table.addToMeld(layOff, where);
    }
}

