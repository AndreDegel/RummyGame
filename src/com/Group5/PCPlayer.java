package com.Group5;

import java.util.ArrayList;

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
        ArrayList<Cards> hand = playerHand.getAllCards();

        //TODO: figure out which card to discard
        //TODO: sort ArrayList so that least desired cards are at the front
        for (int i = 0; i < hand.size(); i++) {
            Cards card = hand.remove(i);
            Rank cardRank = card.getRank();
            Suit cardSuit = card.getSuit();

            if (
                    !hand.contains(new Cards(                       //does not have another card
                        cardRank, Suit.Spades)) &&                  // with the same rank
                    !hand.contains(new Cards(                       //
                        cardRank, Suit.Clubs)) &&                   //
                    !hand.contains(new Cards(                       //
                        cardRank, Suit.Hearts)) &&                  //
                    !hand.contains(new Cards(                       //
                        cardRank, Suit.Diamonds)) &&                //
                            !hand.contains(new Cards(
                        Rank.fromValue(cardRank.getValue() + 1),    //or one with next rank and same suit
                        cardSuit)) &&
                    !hand.contains(new Cards(
                        Rank.fromValue(cardRank.getValue() - 1),    //or one with previous rank and same suit
                        cardSuit))) {
            }
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
