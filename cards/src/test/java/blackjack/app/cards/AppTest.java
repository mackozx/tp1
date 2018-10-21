package blackjack.app.cards;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import java.util.List;
import java.util.ArrayList;

public class AppTest extends TestCase {
 
    public void testApp() {
        assertTrue( true );
    }
    
    public void testGetHandSize() {
    	Blackjack b = new Blackjack();
    	int n = 10;
    	for(int i = 0; i < n; i++) {
    		b.hit();
    	}
    	assertEquals(n + 2, b.getHandSize());
    }
    
    public void testDeckValue() {
    	List<Card> deck = Card.createDeck();
    	int fulldeckvalue = 340;
    	assertEquals(fulldeckvalue, Card.deckValue(deck));
    }
}
