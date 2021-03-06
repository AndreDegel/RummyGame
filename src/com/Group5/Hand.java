package com.Group5;

import java.util.ArrayList;

/**
 * Created by Abdallah on 2/19/2015.
 */
public class Hand {
    private ArrayList<Cards> Card;

    public Hand() {
        this.Card = new ArrayList<Cards>();
    }

    public ArrayList<Cards> getAllCards() {
        return this.Card;
    }

    public Cards getCard(int index) {
        return this.Card.get(index);
    }

    public void AddCard(Cards card) {
        this.Card.add(card);
    }

    public void Discard(Cards card) {
        DiscardPile.addToDiscard(card);
        Card.remove(card);
    }
    public void Remove(Cards card){
        Card.remove(card);
    }

}
