import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BlackjackWorldTest {

	// BlackjackWorld w1 = new BlackjackWorld(new Posn(50, 50), new Posn(100, 100));
	// BlackjackWorld w2 = new BlackjackWorld(new Posn(50, 50), new Posn(98, 98));
	// BlackjackWorld w3 = new BlackjackWorld(new Posn(50, 50), new Posn(50, 50));
	// BlackjackWorld w4 = new BlackjackWorld(new Posn(100, 50), new Posn(0, 70));

	@Test
	void testUpdate() {
		assertEquals(w2, w1.update());
		assertEquals(w3, w3.update());
	}

}
