import java.util.ArrayList;
import java.util.Objects;

import processing.core.PApplet;

/**
 * represents a hand in blackjack, which consists of at least two cards
 *   but at most 11 cards
 */

public class Hand implements IWorld {
    private ArrayList<Card> cards;
    private Posn loc;
    private int total; // represents total value of the cards in the hand

    Hand(ArrayList<Card> cards, Posn loc) {
        this.cards = cards;
        this.loc = loc;
        total = totalHandVal(cards);
    }

    /** draw a picture of the cards at this hand's (x,y) */
    public PApplet draw(PApplet w) { 
        for(int i = 0; i < cards.size(); i++) {
            if(i == 0) {
                cards.get(i).draw(w, loc);
            }
            cards.get(i).draw(w, loc.translate(new Posn(i*20, i*20)));
        }
        return w;
    }

    /** adds the first card from the given deck to the hand 
     *   and turns the card face up 
     */
    public Hand addCard(Deck d) {
        Card add = d.getCard();
        cards.add(add.flipCard());
        total = totalHandVal(cards); // Update the total value of the hand
        return this;
    }

    public int total() {
        return totalHandVal(this.cards);
    }

    /**
     * computes the total value of all given cards in the hand
     * @param cards
     * @return
     */
    public int totalHandVal (ArrayList<Card> cards) {
        int result = 0;
        int numAces = 0;
        for(int i = 0; i < cards.size(); i++) {
            if (cards.get(i).value() == 11) { numAces += 1; }
            result = result + cards.get(i).value();
        }

        while (result > 21 && numAces > 0) {
            result -= 10;  // treat one ace as a 1 instead of 11 value
            numAces -= 1;
        }

        return result;
    }

    @Override
    public String toString() {
        return "Hand [cards=" + cards + ", loc=" + loc + "]";
    }

    @Override
    public int hashCode() {
        return Objects.hash(cards, loc);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Hand other = (Hand) obj;
        return Objects.equals(cards, other.cards) && Objects.equals(loc, other.loc);
    }

    public ArrayList<Card> getCards() {
        return this.cards;
    }

    public Posn getLoc() {
        return loc;
    }

    public void setLoc(Posn loc) {
        this.loc = loc;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }
}





	/*Card card2;
	Card card3;
	Card card4;
	Card card5;
	Card card6;
	Card card7;
	Card card8;
	Card card9;
	Card card10;
	Card card11;*/



// restructure to make interface with empty hand or list of cards
// 1) hit method- adds new card- 
// 2) stand method- stops hand and then dealer reveals hand
