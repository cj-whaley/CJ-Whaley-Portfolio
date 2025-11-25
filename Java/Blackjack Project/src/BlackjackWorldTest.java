import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

class BlackjackWorldTest {

	@Test
	void test() {
		// hand with the 2 of Hearts, face down
		Hand tester = new Hand(new ArrayList<Card>(Arrays.asList(new Card[]{new Card(Color.RED, "Hearts", '2', false)})), new Posn(100, 100));

		// deck with the Ace of Hearts, 2 of Hearts, 4 of Diamonds, and 5 of Clubs
		Deck testDeck = new Deck(new ArrayList<Card>(Arrays.asList(new Card[]{new Card(Color.RED, "Hearts", 'A', false),
				new Card(Color.RED, "Hearts", '2', false),
				new Card(Color.RED, "Diamonds", '4', false),
				new Card(Color.BLACK, "Clubs", '5', false)})));

		testRemoveCard(testDeck);
		testAddCard(tester, testDeck);
		testFlipCard(tester);
		testTotalHandValue();
		testDeckShuffle();
		testLeaderboardUpdate();
	}

	@Test
	void testUpdate() {
		// Implement any necessary update tests here
	}

	void testRemoveCard(Deck testDeck) {
		assertEquals(new Deck(new ArrayList<Card>(Arrays.asList(new Card[] {
				new Card(Color.RED, "Hearts", '2', false),
				new Card(Color.RED, "Diamonds", '4', false),
				new Card(Color.BLACK, "Clubs", '5', false)}))), testDeck.removeCard());
	}

	void testAddCard(Hand tester, Deck testDeck) {
		assertTrue(new Hand(new ArrayList<Card>(Arrays.asList(new Card[] {new Card(Color.RED, "Hearts", '2', false), 
				new Card(Color.RED, "Hearts", '2', true)})), 
				new Posn(100, 100)).equals(tester.addCard(testDeck)));
	}
	@Test
	void testKeyPressed() {
		// tested interactively, the 'h' key adds another card to the player's hand
		// and the rest of the keys do not do anything
	}

	void testFlipCard(Hand tester) {
		assertEquals(new Card(Color.RED, "Hearts", '2', true), tester.getCards().get(0).flipCard());
	}

	@Test
	void testTotalHandValue() {
		Hand hand = new Hand(new ArrayList<Card>(Arrays.asList(
				new Card(Color.RED, "Hearts", 'A', true),
				new Card(Color.RED, "Diamonds", 'K', true)
				)), new Posn(100, 100));
		assertEquals(21, hand.total());
	}

	@Test
	void testDeckShuffle() {
		Deck deck1 = new Deck(true);
		Deck deck2 = new Deck(true);
		assertNotEquals(deck1, deck2); // Shuffled decks should not be equal
	}


	@Test
	void testLeaderboardUpdate() {
		Leaderboard lb = new Leaderboard();
		Score newScore = new Score(500, "TestPlayer");
		lb.updateLB(newScore);
		assertTrue(lb.getLb().contains(newScore));
	}
}




/*


	void testFlipCard(Hand tester) {
		assertEquals(new Card(Color.RED, "Hearts", '2', true), tester.getCards().get(0).flipCard());
	}

}

	void testLoadGame() {

	}

	void testSaveGame() {

	}

	void testGetName() {

	}

	void testMouseClicked (){

	}

}


 */

