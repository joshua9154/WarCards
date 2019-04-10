package com.company;

import java.util.Comparator;

public class Card {
    public Card(suit suit, rank rank) {
        this.ranks = rank;
        this.suits = suit;
    }

    private suit suits;
    private rank ranks;

    public String getRank() {
        return ranks.toString();
    }

    public String getSuit() {
        return suits.toString();
    }

    public static final Comparator<Card> warComparator = new Comparator<Card>() {
        public int compare(Card c, Card t) {
            if (c.suits.ordinal() < t.suits.ordinal())
                return -1;
            else if (c.suits.ordinal() > t.suits.ordinal()) {
                return 1;
            }
            return 0;
        }
    };

    public static final Comparator<Card> naturalOrder = new Comparator<Card>() {
        @Override
        public int compare(Card c, Card t) {
            if (c.suits.ordinal() < t.suits.ordinal())
                return -1;
            else if (c.suits.ordinal() == t.suits.ordinal()) {
                if (c.ranks.ordinal() < t.ranks.ordinal()) {
                    return -1;
                }
            }
            if (c.ranks.ordinal() == t.ranks.ordinal() && c.suits.ordinal() == t.suits.ordinal())
                return 0;

            return 1;
        }
    };

    public enum rank {Clubs, Diamonds, Hearts, Spades}

    public enum suit {
        Two, Three, Four, Five, Six,
        Seven, Eight, Nine, Ten, Jack, Queen, King, Ace
    }

}


