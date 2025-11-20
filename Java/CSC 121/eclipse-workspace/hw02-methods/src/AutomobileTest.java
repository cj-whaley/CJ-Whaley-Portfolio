import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class AutomobileTest {

	Automobile newCar = new Automobile("Toyota" , 30000 , 25.5 , false, 18);
	Automobile usedCar = new Automobile("Ford" , 12000 , 30.0 , true, 20);
	
	@Test
	public void testRange() {
		assertEquals(229, this.newCar.range(0.5));
		assertEquals(344, this.newCar.range(0.75));
		assertEquals(0, this.newCar.range(0.0));
		assertEquals(270, this.usedCar.range(0.5));
	}
	
}
