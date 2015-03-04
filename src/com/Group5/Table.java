package com.Group5;

import java.util.ArrayList;

/**
 * Created by bd2319wv on 3/3/2015.
 * Class that represents the cards on the table
 * using an ArrayList of Card ArrayLists.
 * Static class, variables, and methods because it(table) never changes
 * and doesn't need to be instantiated.
 */
public class Table {
    public static ArrayList<ArrayList<Cards>> tableCards = new ArrayList<ArrayList<Cards>>();

    //see (get) whatever is on the table
    public static ArrayList<ArrayList<Cards>> getTableCards() {
        return tableCards;
    }

    //add to the Array if you add a run
    public static void newMeld(ArrayList<Cards> meld){
        //meld.sort();
        tableCards.add(meld);

    }

    public static void addToMeld(ArrayList<Cards> toAdd, int tableCardIndex){
        ArrayList<Cards> addCardsHere = tableCards.remove(tableCardIndex);
        for (Cards c : toAdd) {
            addCardsHere.add(c);
        }
        //TODO: sort
        tableCards.add(addCardsHere);

    }
}


