import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


/*
 * The typecast (Person) is necessary for these test cases to pass
 *  because the IPerson interface does not have the fields mom, dad, and child.
 *  Those fields are in the Person class, so we need to specify that the objects are
 *  of the Person class so that the .child and .mom selectors can be accessed
 */

class PersonTest {

	Person aliya = new Person("Aliya");   // mom
	Person burhan = new Person("Burhan"); // dad
	Person camile = new Person("Camile", aliya, burhan);
	
	@Test
	void test() {
		assertEquals(camile, ((Person)camile.mom).child);
		assertEquals(camile, ((Person)camile.dad).child);
		assertEquals(new Unknown(), ((Person)camile.mom).dad);
		assertEquals(new Unknown(), ((Person)camile.dad).mom);

		assertEquals( aliya, ((Person) ((Person)aliya).child).mom );
		assertEquals( burhan, ((Person) ((Person)burhan).child).dad );
	}
	
	Person aliya1 = new Person("Aliya");   // mom
	Person burhan1 = new Person("Burhan"); // dad
	Person camile1 = new Person("Camile", aliya1, burhan1);

	@Test
	void testEquals() {
		assertEquals(true, aliya.equals(aliya1));
		assertEquals(false, camile.equals(aliya1));
	}
}
