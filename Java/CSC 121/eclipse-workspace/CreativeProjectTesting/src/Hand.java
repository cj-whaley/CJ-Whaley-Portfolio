import java.awt.Color;

/**
 * represents a hand in blackjack, which consists of at least two cards
 *   but at most 11 cards
 */

public class Hand {
	
	Hand(Card ...cards){
	
	}

	Color red = Color.red;
	Card king = new Card(red, "Hearts", 'K', 10);
	Hand h1 = new Hand(king, king, king, king, king, king, king, king, king, king, king);
	Hand h2 = new Hand();
	Hand h3 = new Hand(king, new Card(red, "Diamonds", 'A', 11));
}
