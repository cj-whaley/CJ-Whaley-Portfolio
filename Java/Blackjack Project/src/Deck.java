import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

import processing.core.PApplet;

/**
 * represents a deck of cards
 */
public class Deck{
	private ArrayList<Card> deck;

	public void draw(PApplet w) {
		w.imageMode(PApplet.CENTER);
		w.image(w.loadImage("card back updated.png"), 125, 125);
	}

	Deck() {
		this(true);
	}
	

	/* generates a full 52 card deck and shuffles it */ 
	Deck(boolean randomize) {
		super();
		this.deck = new ArrayList<Card>();

		String[] suits = { "Clubs", "Hearts", "Diamonds", "Spades" };
		String ranks = "A23456789TJQK";

		for (int i =0; i < ranks.length(); i = i + 1) {
			char crank = ranks.charAt(i);

			for (int s = 0; s < suits.length; s++) {
				String curSuit = suits[s];

				Card card = new Card(Color.RED, curSuit, crank, false);
				this.deck.add(card);
			}
		}

		if (randomize) {
			Collections.shuffle(this.deck);
		}
	}
	
	
	/**
	 * Test Constructor, will not be called in-game
	 * @param deck
	 */
	Deck(ArrayList<Card> deck) {
		this.deck = deck;
	}



	/** returns the first card in the deck */
	public Card getFirstCard() {
		return this.deck.get(0);
		//return this.deck.getFirst();
	}

	/** returns the deck with the first card removed */
	public Deck removeCard() {
		this.deck.remove(0);
		return this;
		//return new Deck(this.deck.removeTop());
	}
	
	public Card getCard() {
		Card res = this.deck.get(0);
		this.deck.remove(0);
		return res;
	}







	@Override
	public String toString() {
		return "Deck [deck=" + deck + "]";
	}







	@Override
	public int hashCode() {
		return Objects.hash(deck);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Deck other = (Deck) obj;
		return Objects.equals(deck, other.deck);
	}



	public ArrayList<Card> getDeck() {
		return deck;
	}



	public void setDeck(ArrayList<Card> deck) {
		this.deck = deck;
	}



}
