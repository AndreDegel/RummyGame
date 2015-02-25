package com.Group5;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by Abdallah on 2/19/2015.
 */
public class Pile {
    public Stack<Cards> Card;

    public Pile() {
        this.Card = new Stack<Cards>();
    }

    public Cards Draw() {
        return this.Card.pop();
    }
}