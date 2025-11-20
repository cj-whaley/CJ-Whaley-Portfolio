import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;


public class PlanetTest {

	Planet Earth = new Planet("Earth", 93.845, 1.0);
	Planet Mars = new Planet("Mars", 136.77, 0.38);
	
	@Test
    public void testconvertWeight() {
		assertEquals(11.0, this.Earth.convertWeight(11.0));
		assertEquals(4.18, this.Mars.convertWeight(11.0));
		assertEquals(1.596, this.Mars.convertWeight(4.2));
		assertEquals(22.04, this.Mars.convertWeight(58.0));
		assertEquals(150.0, this.Earth.convertWeight(150.0));
		assertEquals(0.0, this.Earth.convertWeight(0.0));
		assertEquals(0.0, this.Mars.convertWeight(0.0));
	}

}
