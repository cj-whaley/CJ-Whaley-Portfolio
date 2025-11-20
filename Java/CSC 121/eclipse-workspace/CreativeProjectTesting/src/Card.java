import java.awt.Color;

/**
 * represents a playing card in a deck
 */
public class Card {
	Color color;
	String suit;
	char rank;
	int value;
	
	Card(Color color, String suit, char rank, int value) {
		this.color = color;
		this.suit = suit;
		this.rank = rank;
		this.value = value;
	}
	Color red = Color.red;
    Card king = new Card(red, "Hearts", 'K', 10);

}
