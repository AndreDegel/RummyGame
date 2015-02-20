package com.Group5;

import java.util.Stack;

/**
 * Created by Abdallah on 2/19/2015.
 */
public class DiscardPile extends Pile {

    public void toStockPile(Pile stockPile) {
        while (super.Cards.size() > 0) {
            Card c = super.Cards.pop();
            stockPile.Cards.push(c);
        }
    }
}
