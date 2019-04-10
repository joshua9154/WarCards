package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private int n = Card.suit.values().length * Card.rank.values().length;

    private List<Card> extraDeck = new ArrayList<>();
    private List<Card> oneMainDeck = new ArrayList<>();
    private List<Card> oneSpareDeck = new ArrayList<>();
    private List<Card> twoMainDeck = new ArrayList<>();
    private List<Card> twoSpareDeck = new ArrayList<>();

    Deck() {
        for (int i = 0; i < Card.suit.values().length; i++) {
            for (int j = 0; j < Card.rank.values().length; j++) {
                extraDeck.add(new Card(Card.suit.values()[i], Card.rank.values()[j]));
            }
        }

        Collections.shuffle(extraDeck);
       /*
       Splits the deck into two.
        */
        for (int i = 0; i < n / 2; i++) {
            oneMainDeck.add(extraDeck.get(i));
        }
        for (int i = n / 2; i < n; i++) {
            twoMainDeck.add(extraDeck.get(i));
        }

        extraDeck.clear();
    }

    private void switchDecks(List<Card> deckOne, List<Card> deckTwo) {
        deckOne.clear();
        for (Card c : deckTwo) {
            deckOne.add(c);
        }
        deckTwo.clear();
    }

    private void showPlayer(List<Card> cards) {
        for (Card c : cards) {
            System.out.println(c.getSuit() + " of " + c.getRank());
        }
    }


    private void nextRound() {
        Collections.shuffle(oneSpareDeck);
        Collections.shuffle(twoSpareDeck);

        switchDecks(oneMainDeck, oneSpareDeck);
        switchDecks(twoMainDeck, twoSpareDeck);
        /*
        The Sorting is for presentation purposes and the cards are reshuffled after.
         */
        oneMainDeck.sort(Card.naturalOrder);
        twoMainDeck.sort(Card.naturalOrder);

        if (!oneMainDeck.isEmpty()) {
            System.out.println("Player One currently has");
            showPlayer(oneMainDeck);
        } else
            System.out.println("Player One currently has NOTHING!!!!!!!!!");

        if (!twoMainDeck.isEmpty()) {
            System.out.println("Player Two currently has");
            showPlayer(twoMainDeck);
        } else
            System.out.println("Player Two currently has Nothing!!!!!!!!!!");

        Collections.shuffle(oneMainDeck);
        Collections.shuffle(twoMainDeck);

    }

    public void war() {

        while (!oneMainDeck.isEmpty() && !twoMainDeck.isEmpty()) {
            int i = 0;
            while (i < oneMainDeck.size() && i < twoMainDeck.size()) {

                System.out.println("Player One has a " + oneMainDeck.get(i).getSuit() + " and Player Two has a " + twoMainDeck.get(i).getSuit());

                if ((Card.warComparator.compare(oneMainDeck.get(i), twoMainDeck.get(i)) > 0)) {
                    oneSpareDeck.add(oneMainDeck.get(i));
                    oneSpareDeck.add(twoMainDeck.get(i));

                    if (!extraDeck.isEmpty()) {
                        for (Card c : extraDeck) {
                            oneSpareDeck.add(c);
                        }
                        extraDeck.clear();
                        System.out.println("Player One Wins The WAR!!!!!");
                    } else {
                        System.out.println("Player One Wins");
                    }
                    i++;
                } else if ((Card.warComparator.compare(oneMainDeck.get(i), twoMainDeck.get(i)) < 0)) {
                    twoSpareDeck.add(oneMainDeck.get(i));
                    twoSpareDeck.add(twoMainDeck.get(i));
                    if (!extraDeck.isEmpty()) {
                        for (Card c : extraDeck) {
                            twoSpareDeck.add(c);
                        }
                        extraDeck.clear();
                        System.out.println("Player Two Wins The WAR!!!!!");
                    } else {
                        System.out.println("Player Two Wins");
                    }
                    i++;
                } else {
                    System.out.println("Its a War!!!!");

                    extraDeck.add(oneMainDeck.get(i));
                    extraDeck.add(twoMainDeck.get(i));
                    i++;

                    if (i < oneMainDeck.size() && i < twoMainDeck.size()) {
                        extraDeck.add(oneMainDeck.get(i));
                        extraDeck.add(twoMainDeck.get(i));
                    }
                    i++;

                }


            }
            /*
            Takes any extra cards from the game and returns them to their owners
            spare decks.
             */
            for (; i < oneMainDeck.size(); i++) {
                oneSpareDeck.add(oneMainDeck.get(i));
            }
            for (; i < twoMainDeck.size(); i++) {
                twoSpareDeck.add(twoMainDeck.get(i));
            }

            nextRound();
        }
        if (twoMainDeck.isEmpty())
            System.out.println("Player One Wins the Game!!!!!!!!");
        else
            System.out.println("Player Two Wins the Game!!!!!!!!!");
    }
}
