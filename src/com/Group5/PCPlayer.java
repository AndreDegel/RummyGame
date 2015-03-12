package com.Group5;

import javax.smartcardio.Card;
import java.util.ArrayList;

/**
 * Player class that represents the Computer player (AI)
 *
 * Created by DayDay on 3/12/2015.
 */
public class PCPlayer extends Player {

    ArrayList<Cards> handCards;

    public PCPlayer() {
        this.playerHand = new Hand();
        handCards = this.playerHand.getAllCards()
    }

    /**
     * PC AI: to be used every time it's the PC player's turn
     */
    public void Play() {
        //TODO: Add AI playing commands

        //Draw


        //Meld if necessary


        //Lay off if necessary


        //Discard

    }

    public Cards DrawCard() {
        //look at top card on discard pile
        Cards topCard = DiscardPile.discardPile.peek();
        Rank topCardRank = topCard.getRank();
        Suit topCardSuit = topCard.getSuit();

        //TODO: Decide where to draw from and draw
        if (hasRank(topCardRank) ||
                hasRank(topCardRank.getValue() + 1)) {
            return DiscardPile.Draw();
        } else if (
        }
    }

    private boolean hasCard(Cards c) {
        return handCards.contains(c);
    }

    private boolean hasRank(Rank r) {
        for (Cards c : handCards) {
            if (c.getRank() == r) {
                return true;
            }
        }
        return false;
    }

    private boolean hasSuit(Suit s) {
        for (Cards c : handCards) {
            if (c.getSuit() == s) {
                return true;
            }
        }
        return false;
    }
}
