package com.Group5;

public class Cards {
    //Data for a card object
    private int rank;
    private String suit;

    //Constructor
    public Cards(int rank, String suit){
        this.rank = rank;
        this.suit = suit;
    }

    //Method that returns the string representation of a card.
    public void showCard(){
        String rankName;
        if (this.rank == 1) {
            rankName = "Ace";
        } else if (this.rank == 11) {
            rankName = "Jack";
        } else if (this.rank == 12) {
            rankName = "Queen";
        } else if (this.rank == 13) {
            rankName = "King";
        } else {
            rankName = Integer.toString(this.rank);
        }
        System.out.println(rankName + " of " + suit);
    }
}
