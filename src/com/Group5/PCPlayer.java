package com.Group5;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

/**
 * Player class that represents the Computer player (AI)
 *
 * Created by DayDay on 3/12/2015.
 */
public class PCPlayer extends Player {

    public PCPlayer() {
        this.playerHand = new Hand();
    }

    /**
     * PC AI: to be used every time it's the PC player's turn
     */
    public void Play() {
        System.out.println(DiscardPile.ShowTopCard());
        System.out.println(playerHand.getAllCards());

        //Draw
        Draw();


        ArrayList<Cards> hand = playerHand.getAllCards();

        ArrayList<Cards> spades = new ArrayList<Cards>();
        ArrayList<Cards> clubs = new ArrayList<Cards>();
        ArrayList<Cards> hearts = new ArrayList<Cards>();
        ArrayList<Cards> diamonds = new ArrayList<Cards>();

        //Separate hand cards by suit
        for (Cards card : hand) {
            Suit cardSuit = card.getSuit();

            switch (cardSuit) {
                case Spades:
                    spades.add(card);
                    break;
                case Clubs:
                    clubs.add(card);
                    break;
                case Hearts:
                    hearts.add(card);
                    break;
                case Diamonds:
                    diamonds.add(card);
                    break;
            }
        }

        //Sort each group of cards
        if (!spades.isEmpty()) {
            Collections.sort(spades);
        }
        if (!clubs.isEmpty()) {
            Collections.sort(clubs);
        }
        if (!hearts.isEmpty()) {
            Collections.sort(hearts);
        }
        if (!diamonds.isEmpty()) {
            Collections.sort(diamonds);
        }
        HashMap<Suit, ArrayList<Cards>> suitGroups = new HashMap<Suit, ArrayList<Cards>>();
        suitGroups.put(Suit.Spades, spades);
        suitGroups.put(Suit.Clubs, clubs);
        suitGroups.put(Suit.Hearts, hearts);
        suitGroups.put(Suit.Diamonds, diamonds);


        //put the suit arrays in a list of lists for better checking.
        ArrayList<ArrayList<Cards>> suitsList = new ArrayList<ArrayList<Cards>>();
        suitsList.add(clubs);
        suitsList.add(spades);
        suitsList.add(hearts);
        suitsList.add(diamonds);

        //check what it does so far TODO remove later
        System.out.println("AI after drawing");
        System.out.println(DiscardPile.ShowTopCard());
        System.out.println(playerHand.getAllCards().toString());
        System.out.println(spades.toString());
        System.out.println(clubs.toString());
        System.out.println(hearts.toString());
        System.out.println(diamonds.toString());



        //Meld if necessary
        meld(suitsList);

        //TODO: remove later
        System.out.println("AI after melding");
        System.out.println(Table.getTableCards().toString());
        System.out.println(playerHand.getAllCards().toString());


        //Lay off if necessary
        Layoff();
        System.out.println("AI after layoff");
        System.out.println(Table.getTableCards().toString());
        System.out.println(playerHand.getAllCards().toString());
        //Discard
        Discard(suitGroups);
        System.out.println(DiscardPile.ShowTopCard());
        System.out.println(playerHand.getAllCards().toString());
    }

    private void Draw() {
        //look at top card on discard pile
        Cards topCard = DiscardPile.ShowTopCard();
        //check if already drawn from discard
        boolean draw = false;
        //go through all cards in the hand
        for (Cards c : playerHand.getAllCards()){
            //if discard top and hand have the same suit
            if (c.getSuit() == topCard.getSuit()) {
                //see if we can add discard to hand to get a run
                if (c.getRank().getValue() + 1 == topCard.getRank().getValue() ||
                        c.getRank().getValue() - 1 == topCard.getRank().getValue()) {
                    playerHand.AddCard(DiscardPile.Draw());     //draw
                    draw = true;        //we drew
                    break;              //and break
                }
            }
            //if the suit is not the same see if we can add it to make a set
            else if (c.getRank() == topCard.getRank()){
                playerHand.AddCard(DiscardPile.Draw());     //draw
                draw = true;        //we drew
                break;              //and break
            }
        }
        //if we didn't draw from the discard pile we take from the stock
        if (!draw){
            playerHand.AddCard(StockPile.Draw());
        }
    }

    private void meld(ArrayList<ArrayList<Cards>> suitsList){
        //check if run worked otherwise look for set
        boolean run = false;
        //make arraylist to put meld in
        ArrayList<Cards> meld = new ArrayList<Cards>();
        //go through the suit Arrays
        for (ArrayList<Cards> s : suitsList){
            //if they have at least 3 cards we can check if we can meld them
            if (s.size() >=3){
                for (Cards c : s){
                    //start with the first card in the array to compare the rest
                    if (meld.isEmpty()) {
                        meld.add(c);
                        playerHand.getAllCards().remove(c);
                    }
                    //if the current cards value is one greater then the last one in the array
                    else if (meld.get(meld.size() - 1).getRank().getValue() + 1 == c.getRank().getValue()) {
                        meld.add(c);    //put to meld list
                        playerHand.getAllCards().remove(c);  //remove from hand
                        Collections.sort(meld); //sort meld list
                    }
                    //if the current cards value is one less then the first one in the array
                    else if (meld.get(0).getRank().getValue() - 1 == c.getRank().getValue()) {
                        meld.add(c);    //put to meld list
                        playerHand.getAllCards().remove(c);  //remove from hand
                        Collections.sort(meld); //sort meld list
                    }
                    //else retry
                    else {
                        //if we found at least 3 cards to meld stop searching this array
                        if (meld.size()>=3){
                            break;
                        }
                        //otherwise put the cards back to the hand if we took them out
                        else {
                            for (Cards a : meld) {
                                if (!playerHand.getAllCards().contains(a)) {
                                    playerHand.AddCard(a);
                                }
                            }
                            //clear the current meld list and restart from the current card
                            meld.clear();
                            meld.add(c);                            //add current card
                            playerHand.getAllCards().remove(c);     //remove from hand
                        }
                    }

                }
                //if we previously or at the end of the last array found
                //something to meld then meld it and go on
                if (meld.size() >= 3){
                    Table.newMeld(meld);
                    run = true;
                    break;
                }
                //otherwise we are at the end of the current array
                //put last checked cards back in the hand and clear the meld
                else {
                    for (Cards a : meld) {
                        if (!playerHand.getAllCards().contains(a)) {
                            playerHand.AddCard(a);
                        }
                    }
                    meld.clear();
                }
            }
        }

        //check for a set if no run
        if (!run){
            //sort the hand to better check for sets
            Collections.sort(playerHand.getAllCards());
            //Iterate over all cards to see if it has a set to meld (reuse meld variable)
            for (Cards s : playerHand.getAllCards()){
                //start with the first card in the array to compare the rest
                if (meld.isEmpty()) {
                    meld.add(s);
                }
                //if the rank is the same add
                else if (meld.get(0).getRank() == s.getRank()) {
                    meld.add(s);
                }
                //otherwise put the cards back to the hand if we took them out
                else {
                    //clear the current set list and restart from the current card
                    meld.clear();
                    meld.add(s);
                }
                //if we have a set of 3 (first occurrence)
                if (meld.size() >= 3){
                    //meld it
                    Table.newMeld(meld);
                    //and remove the cards from the set out of the hand
                    for (Cards a : meld) {
                        playerHand.getAllCards().remove(a);
                    }
                    //stop loop
                    break;
                }
            }

        }

    }

    private void Layoff() {
        ArrayList<ArrayList<Cards>> table = Table.getTableCards();

        for (int x = 0; x < table.size(); x++) {
            //loop through melds on the table
            ArrayList<Cards> meld = table.get(x);

            Collections.sort(meld);

            ArrayList<Cards> toLayoff = new ArrayList<Cards>();

            Cards firstCard = meld.get(0);
            Cards lastCard = meld.get(meld.size() - 1);

            //get first and last rank values
            int firstCardValue = firstCard.getRank().getValue();
            int lastCardValue = lastCard.getRank().getValue();

            if (firstCardValue == lastCardValue) {
                //same value means different suits (this is a set)
                for (Cards c : playerHand.getAllCards()) {
                    if (c.getRank().getValue() == firstCardValue) {
                        //card is a valid layoff card
                        toLayoff.add(c);
                    }
                }
            } else {
                //different values means same suit (this is a run)

                //calculate values for higher and lower layoff cards
                int higherLayoff = lastCardValue + 1;
                int lowerLayoff = firstCardValue - 1;

                for (Cards c : playerHand.getAllCards()) {
                    int currCardValue = c.getRank().getValue();

                    if (currCardValue == lowerLayoff) {
                        //current card is one rank lower than first card in meld
                        toLayoff.add(c);
                        lowerLayoff--;  //higher layoff value increases by one
                    } else if (currCardValue == higherLayoff) {
                        //current card is one rank higher than last card in meld
                        toLayoff.add(c);
                        higherLayoff++; //lower layoff value decreases by one
                    }
                }
            }

            for (Cards c : toLayoff) {
                //remove cards to layoff from hand
                playerHand.Remove(c);
            }

            //layOff cards to table
            layOff(toLayoff, x);
        }
    }

    private void Discard(HashMap<Suit, ArrayList<Cards>> suitGroups) {
        Random rand = new Random();

        Cards toDiscard;
        ArrayList<Cards> discardable = new ArrayList<Cards>();
        ArrayList<Cards> reserve = new ArrayList<Cards>();

        //TODO: figure out which card to discard
        //TODO: sort ArrayList so that least desired cards are at the front

        //Measure difference between same-suit cards and act accordingly
        for (Suit suit : suitGroups.keySet()) {
            ArrayList<Cards> cards = suitGroups.get(suit);

            if (!cards.isEmpty()) {
                Collections.sort(cards);

                ArrayList<ArrayList<Cards>> subgroups = new ArrayList<ArrayList<Cards>>();

                int prevCardIndex = 0;  //start first card in group
                int startSubgroup = 0;  //initiate subgroup first card index

                for (int currCardIndex = 1;             //loop over the rest of the cards
                     currCardIndex < cards.size();      //in the group starting with
                     currCardIndex++) {                 //the second card

                    Cards prevCard = cards.get(prevCardIndex);
                    Cards currCard = cards.get(currCardIndex);

                    int prevCardRankValue = prevCard.getRank().getValue();
                    int currCardRankValue = currCard.getRank().getValue();

                    int rankDiff = currCardRankValue - prevCardRankValue;

                    //end subgroup and add to subgroups arrayList
                    if (rankDiff > 2) {
                        ArrayList<Cards> subgroup = new ArrayList<Cards>();

                        //loop over cards from the start of current subgroup to current card index
                        for (int subgroupCardIX = startSubgroup;
                             subgroupCardIX < currCardIndex;
                             subgroupCardIX++) {
                            //add each card to new subgroup arrayList
                            subgroup.add(cards.get(subgroupCardIX));
                        }

                        subgroups.add(subgroup);        //add new subgroup to arrayList of subgroups
                        startSubgroup = currCardIndex;  //start new subgroup with current card
                    }

                    prevCardIndex = currCardIndex;
                }

                ArrayList<Cards> subgroup = new ArrayList<Cards>();

                //loop over cards from the start of current subgroup to end of suit group
                for (int subgroupCardIX = startSubgroup;
                     subgroupCardIX < cards.size();
                     subgroupCardIX++) {
                    //add each card to new subgroup arrayList
                    subgroup.add(cards.get(subgroupCardIX));
                }

                subgroups.add(subgroup);        //add final subgroup to arrayList of subgroups

                //Subgroups with only one card are not a part of a possible run
                //thus are added to reserve cards to be matched for a set
                for (int i = 0; i < subgroups.size(); i++) {
                    ArrayList<Cards> sg = subgroups.get(i);
                    if (sg.size() == 1) {
                        reserve.add(sg.get(0));
                        subgroups.remove(i);
                    }
                }
            }
        }

        //Sort reserve cards
        Collections.sort(reserve);

        //check reserve cards for set matches (cards with the same rank)

        //Split up reserve cards by rank
        HashMap<Rank, ArrayList<Cards>> cardsByRank =
                new HashMap<Rank, ArrayList<Cards>>();

        if (!reserve.isEmpty()) {
            for (int i = 0; i < reserve.size(); i++) {
                Cards card = reserve.get(i);
                Rank cardRank = card.getRank();

                ArrayList<Cards> cards = new ArrayList<Cards>();

                if (cardsByRank.keySet().contains(cardRank)) {
                    //get cards with same rank
                    cards = cardsByRank.get(cardRank);
                }

                cards.add(card);                        //add card to cards with same rank
                cardsByRank.put(cardRank, cards);       //put all cards with this rank into cardsByRank hashMap
            }

            //Get cards unmatched for a set and put them in "discardable" arrayList
            for (Rank rank : cardsByRank.keySet()) {
                ArrayList<Cards> rankSet = cardsByRank.get(rank);

                if (rankSet.size() == 1) {
                    discardable.add(rankSet.get(0));
                }
            }

            if (discardable.isEmpty()) {
                //if all reserve cards fall into a set then discard random reserve card
                int randomCard = rand.nextInt(reserve.size());
                toDiscard = reserve.get(randomCard);
            } else {
                //discard random "discardable" card
                int randomCard = rand.nextInt(discardable.size());
                toDiscard = discardable.get(randomCard);
            }
        } else {
            //if there are no reserve (all hand cards fall into a potential run)
            //then discard random card from hand
            int randomCard = rand.nextInt(playerHand.getAllCards().size());
            toDiscard = playerHand.getCard(randomCard);
        }

        playerHand.Discard(toDiscard);  //Discard chosen card
    }
}
