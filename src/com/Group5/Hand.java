package com.Group5;

import java.util.ArrayList;

/**
 * Created by Abdallah on 2/19/2015.
 */
public class Hand {
    private ArrayList<Card> Cards;

    public Hand() {
        this.Cards = new ArrayList<Card>();
    }

    public ArrayList<Card> getAllCards() {
        return this.Cards;
    }

    public Card getCard(int index) {
        return this.Cards.get(index);
    }

    public void AddCard(Card card) {
        this.Cards.add(card);
    }

    public void Discard(Card card, Pile discardPile) {
    }
}
