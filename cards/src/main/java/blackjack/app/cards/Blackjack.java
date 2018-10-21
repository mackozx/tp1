package blackjack.app.cards;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Blackjack {

    private int score;
 
    private List<Card> deck;
    private List<Card> hand;
    
    public Dealer dealer;
    
    private BFrame gui;
    
    public Blackjack() {
    	dealer = new Dealer();
        deck = Card.createDeck();
    	hand = new ArrayList<Card>();
    	shuffleDeck();
        score = 0;
        onStart();
        gui = new BFrame(this);
    }
    
    public void reset() {
    	dealer = new Dealer();
    	deck = Card.createDeck();
    	hand = new ArrayList<Card>();
    	shuffleDeck();
    	score = 0;
    	onStart();
    }

    public void hit() {
        hand.add(getTopCard());
        deck.remove(getTopCard());
        updateScore();
      	dealer.hit();
    }

    private Card getTopCard() {
        return deck.get(0);
    }

    private void onStart() {
        hit();
        hit();
    }


    public void shuffleDeck() {
        Collections.shuffle(deck);
    }

    public int getScore() {
        return score;
    }
    
    private void updateScore() {
    	score = Card.deckValue(hand);
    }
    
    public int getHandSize() {
    	return hand.size();
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