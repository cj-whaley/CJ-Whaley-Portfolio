import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class RectangleTest {

	@Test
	void testRectangle() {
		Rectangle r1 = new Rectangle();		// create a 1x1   rectangle
		Rectangle r2 = new Rectangle(10);	// create a 10x10 rectangle
		Rectangle r3 = new Rectangle(20, 30);	// create a 20x30 rectangle

		assertEquals(1

				, r1.area());
		assertEquals(100

				, r2.area());
		assertEquals(600

				, r3.area());
	}

}
