package com.Group5;

import java.util.LinkedList;
import java.util.Stack;

/**
 * Created by Abdallah on 2/19/2015.
 */
public class DiscardPile {
    public static LinkedList<Cards> discardPile = new LinkedList<Cards>();

    public static void toStockPile() {
        Cards topCard = discardPile.pop();
        while (discardPile.size() > 0) {
            Cards c = discardPile.pop();
            Pile.stockPile.push(c);
        }
        discardPile.push(topCard);
    }
    public static void addToDiscard(Cards discard){
        discardPile.push(discard);
    }
    public static Cards Draw() {
        return discardPile.pop();
    }
}
