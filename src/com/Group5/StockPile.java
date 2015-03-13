package com.Group5;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

/**
 * Created by Abdallah on 2/19/2015.
 */
public class StockPile {
    public static LinkedList<Cards> stockPile = new LinkedList<Cards>();

    public static Cards Draw() {
        return stockPile.pop();
    }
}