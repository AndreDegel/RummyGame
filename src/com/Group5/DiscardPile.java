package com.Group5;

import java.util.LinkedList;
import java.util.Stack;

/**
 * Created by Abdallah on 2/19/2015.
 */
public class DiscardPile extends Pile {

    public DiscardPile(LinkedList<Cards> deck) {
        super(deck);
    }

    public void toStockPile(Pile stockPile) {
        while (Card.size() > 0) {
            Cards c = Card.pop();
            stockPile.Card.push(c);
        }
    }
}
