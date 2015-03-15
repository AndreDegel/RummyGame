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

        //Meld if necessary


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
                (hasCard(new Cards(
                        Rank.fromValue(topCardRank.getValue() + 1),     //or card with next rank and same suit
                        topCardSuit))) ||
                (hasCard(new Cards(
                        Rank.fromValue(topCardRank.getValue() - 1),     //or card with previous rank and same suit
                        topCardSuit)))
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
                    String subgroupName = null;
                    for (String name : subgroups.keySet()) {
                        ArrayList<Cards> subgroup = subgroups.get(name);
                        if (subgroup.contains(prevCard)) {
                            subgroupName = name;
                        }
                    }

                    if (subgroupName == null) {

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
        }

        return doesHaveSuit;
    }
}
