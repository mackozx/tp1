package blackjack.app.cards;

import java.util.ArrayList;

import java.util.List;

public class Card {

    private int figure;
    private int color;

    public Card(int f, int c) {
        figure = f;
        color = c;
    }

    public static List<Card> createDeck() {
        List<Card> deck = new ArrayList<Card>();
        for(int i = 0; i < 4; i++) {
            for(int j = 1; j < 14; j++) {
                deck.add(new Card(j, i));
            }
        }
        return deck;
    }

    public int getColor() {
        return color;
    }

    public int getFigure() {
        return figure;
    }

    public int getValue() {
        if (figure >= 11) {
            return 10;
        } else {
            return figure;
        }
    }

    public String getInfo() {
        String c = "";
        String f = "";
        if (color == 0) {
            c = "hearts";
        } else if (color == 1) {
            c = "diamonds";
        } else if (color == 2) {
            c = "clubs";
        } else if (color == 3) {
            c = "spades";
        }

        if (figure == 1) {
            f = "A";
        } else if (figure == 11) {
            f = "J";
        } else if (figure == 12) {
            f = "Q";
        } else if (figure == 13) {
            f = "K";
        } else {
            f = String.valueOf(figure);
        }
        
        return f + " " + c;
    }
    
    public static int deckValue(List<Card> list) {
    	int n = 0;
    	for(Card c : list) {
    		if(c.getFigure() != 1) {
    			n += c.getValue();
    		}
    	}
    	for(Card c : list) {
    		if(c.getFigure() == 1) {
    			if(n <= 10) {
    				n += 11;
    			} else {
    				n += 1;
    			}
    		}
    	}
    	return n;
    }
}