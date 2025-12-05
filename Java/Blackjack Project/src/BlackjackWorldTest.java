import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

public class BlackjackWorldTest {

    @Test
    public void testRemoveCard() {
        Deck testDeck = new Deck(new ArrayList<Card>(Arrays.asList(
            new Card(Color.RED, "Hearts", 'A', false),
            new Card(Color.RED, "Hearts", '2', false),
            new Card(Color.RED, "Diamonds", '4', false),
            new Card(Color.BLACK, "Clubs", '5', false)
        )));

        Deck expected = new Deck(new ArrayList<Card>(Arrays.asList(
            new Card(Color.RED, "Hearts", '2', false),
            new Card(Color.RED, "Diamonds", '4', false),
            new Card(Color.BLACK, "Clubs", '5', false)
        )));

        assertEquals(expected, testDeck.removeCard());
    }

    @Test
    public void testAddCard() {
        Hand tester = new Hand(new ArrayList<Card>(Arrays.asList(
            new Card(Color.RED, "Hearts", '2', false)
        )), new Posn(100, 100));

        Deck testDeck = new Deck(new ArrayList<Card>(Arrays.asList(
            new Card(Color.RED, "Hearts", 'A', false),
            new Card(Color.RED, "Hearts", '2', false),
            new Card(Color.RED, "Diamonds", '4', false),
            new Card(Color.BLACK, "Clubs", '5', false)
        )));

        Hand expected = new Hand(new ArrayList<Card>(Arrays.asList(
            new Card(Color.RED, "Hearts", '2', false),
            new Card(Color.RED, "Hearts", '2', true)
        )), new Posn(100, 100));

        assertEquals(expected, tester.addCard(testDeck));
    }

    @Test
    public void testFlipCard() {
        Hand tester = new Hand(new ArrayList<Card>(Arrays.asList(
            new Card(Color.RED, "Hearts", '2', false)
        )), new Posn(100, 100));

        Card flipped = tester.getCards().get(0).flipCard();
        assertEquals(new Card(Color.RED, "Hearts", '2', true), flipped);
    }

    @Test
    public void testTotalHandValue() {
        Hand hand = new Hand(new ArrayList<Card>(Arrays.asList(
            new Card(Color.RED, "Hearts", 'A', true),
            new Card(Color.RED, "Diamonds", 'K', true)
        )), new Posn(100, 100));

        assertEquals(21, hand.total());
    }

    @Test
    public void testDeckShuffle() {
        Deck deck1 = new Deck(true);
        Deck deck2 = new Deck(true);
        assertNotEquals(deck1, deck2); // Shuffled decks should not be equal
    }

    @Test
    public void testLeaderboardUpdate() {
        Leaderboard lb = new Leaderboard();
        Score newScore = new Score(500, "TestPlayer");
        lb.updateLB(newScore);
        assertTrue(lb.getLb().contains(newScore));
    }

    @Test
    public void testKeyPressed() {
        // This one is interactive — you can leave it as a placeholder
        // or simulate key events if your Hand/World classes support it.
    }

    @Test
    public void testUpdate() {
        // Placeholder for update logic once implemented
    }
}
