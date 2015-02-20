package com.Group5;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by Abdallah on 2/19/2015.
 */
public class Pile {
    public Stack<Card> Cards;

    public Pile() {
        this.Cards = new Stack<Card>();
    }

    public Card Draw() {
        return this.Cards.pop();
    }
}