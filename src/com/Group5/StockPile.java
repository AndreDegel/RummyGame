package com.Group5;

import java.util.LinkedList;

/**
 * Created by Abdallah on 2/19/2015.
 */
public class StockPile {
    private static LinkedList<Cards> stockPile = new LinkedList<Cards>();

    public static Cards Draw() {
        return stockPile.pop();
    }

    public static void AddCard(Cards card) {
        stockPile.push(card);
    }

    public static LinkedList<Cards> getStockPile() {
        return stockPile;
    }

    public static void setStockPile(LinkedList<Cards> cards) {
        stockPile = cards;
    }
}