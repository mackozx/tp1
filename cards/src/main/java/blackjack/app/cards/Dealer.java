package blackjack.app.cards;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Dealer {
	
	public List<Card> deck;
	public List<Card> hand;
	public int score;
	
	public Dealer() {
		deck = Card.createDeck();
		hand = new ArrayList<Card>();
		score = 0;
		shuffleDeck();
	}
	
	public void shuffleDeck() {
        Collections.shuffle(deck);
    }
	
	public void hit() {
        if(getHandSize() < 2 || score < 17) {
			hand.add(getTopCard());
			deck.remove(getTopCard());
			updateScore();
        }
	}
	
	private Card getTopCard() {
        return deck.get(0);
    }
	
    public int getHandSize() {
    	return hand.size();
    }
    
    private void updateScore() {
    	score = Card.deckValue(hand);
    }

	public Card[] handToArray() {
		Card[] c = new Card[getHandSize()];
		int i = 0;
		for(Card card : hand) {
			c[i] = card;
			i++;
		}
		return c;
	}
}
