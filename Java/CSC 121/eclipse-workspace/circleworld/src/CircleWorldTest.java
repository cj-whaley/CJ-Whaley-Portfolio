import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CircleWorldTest {

	CircleWorld cw1 = new CircleWorld(200, 0);
	CircleWorld cw2 = new CircleWorld(150, 178);
	CircleWorld cw3 = new CircleWorld(150, 400);
	
	@Test
	void testUpdate() {
		assertEquals(new CircleWorld(200, 1) ,
				     cw1.update());
		assertEquals(new CircleWorld(150, 179) ,
			     cw2.update());
		assertEquals(cw3,
			     cw3.update());
	}

}
