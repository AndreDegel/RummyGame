package com.Group5;

import java.util.ArrayList;
import java.util.Collections;

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

    private void Discard() {
        //TODO: maybe just discard randomly to make it easier?!
        ArrayList<Cards> hand = playerHand.getAllCards();

        ArrayList<Cards> spades = new ArrayList<Cards>();
        ArrayList<Cards> clubs = new ArrayList<Cards>();
        ArrayList<Cards> hearts = new ArrayList<Cards>();
        ArrayList<Cards> diamonds = new ArrayList<Cards>();


        //TODO: figure out which card to discard
        //TODO: sort ArrayList so that least desired cards are at the front
        for (Cards card : hand) {
            //Separate hand cards by suit
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
    }

    private boolean hasCard(Cards c) {
        return playerHand.getAllCards().contains(c);
    }

    private boolean hasRank(Rank r) {
        for (Cards c : playerHand.getAllCards()) {
            if (c.getRank() == r) {
                return true;
            }
        }
        return false;
    }

    private boolean hasSuit(Suit s) {
        for (Cards c : playerHand.getAllCards()) {
            if (c.getSuit() == s) {
                return true;
            }
        }
        return false;
    }
}
