import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class RationalTest {

	Rational x = new Rational (13,28);
	Rational y = new Rational (3,7);
	Rational z = new Rational (1,14);
	Rational a = new Rational (22,1);
	Rational b = new Rational (16,16);
	Rational c = new Rational (0,10);
	Rational d = new Rational (8, 0);
	Rational f = new Rational (54, 36);
	Rational g = new Rational (-2, -5);
	Rational h = new Rational (-2, 5);
	Rational i = new Rational (2, -5);

	
	@Test
	void testToString() {
		assertEquals("22", this.a.toString());
		assertEquals("1", this.b.toString());
		assertEquals("0", this.c.toString());
		assertEquals("3/7", this.y.toString());
		assertEquals("Irrational Number", this.d.toString());
		assertEquals("3/2", this.f.toString());
		assertEquals("2/5", this.g.toString());
		assertEquals("-2/5", this.h.toString());
		assertEquals("2/-5", this.i.toString());
	}
	
	@Test
	void testPlus() {
		Rational x1 = new Rational(1, 2);
		Rational x2 = new Rational(1, 3);
		Rational x1_plus_2 = x1.plus(x2);  // 1/2 + 1/3 = 5/6
		
		Rational x3 = new Rational(3, 10);
		Rational x4 = new Rational(3, 5);
		Rational x3_plus_4 = x3.plus(x4);  // 3/10 + 3/5 = 9/10
		Rational x1_plus_3 = x1.plus(x3);  // 1/2 + 3/10 = 4/5

		assertEquals("5/6", x1_plus_2.toString()); // compare the expected string representation
		assertEquals("9/10", x3_plus_4.toString());
		assertEquals("4/5", x1_plus_3.toString());
	}
	
	@Test
	void testMinus() {
		Rational x1 = new Rational(1, 2);
		Rational x2 = new Rational(1, 3);
		Rational x1_minus_2 = x1.minus(x2);  // 1/2 - 1/3 = 1/6
		
		Rational x3 = new Rational(3, 10);
		Rational x4 = new Rational(3, 5);
		Rational x3_minus_4 = x3.minus(x4);  // 3/10 - 3/5 = -3/10
		Rational x1_minus_3 = x1.minus(x3);  // 1/2 - 3/10 = 1/5

		assertEquals("1/6", x1_minus_2.toString()); // compare the expected string representation
		assertEquals("-3/10", x3_minus_4.toString());
		assertEquals("1/5", x1_minus_3.toString());
	}
	
	@Test
	void testTimes() {
		Rational x1 = new Rational(1, 2);
		Rational x2 = new Rational(1, 3);
		Rational x1_times_2 = x1.times(x2);  // 1/2 * 1/3 = 1/6
		
		Rational x3 = new Rational(3, 10);
		Rational x4 = new Rational(3, 5);
		Rational x3_times_4 = x3.times(x4);  // 3/10 * 3/5 = 9/50
		Rational x1_times_3 = x1.times(x3);  // 1/2 * 3/10 = 3/20

		assertEquals("1/6", x1_times_2.toString()); // compare the expected string representation
		assertEquals("9/50", x3_times_4.toString());
		assertEquals("3/20", x1_times_3.toString());
	}
	
	@Test
	void testDividedBy() {
		Rational x1 = new Rational(1, 2);
		Rational x2 = new Rational(1, 3);
		Rational x1_dividedBy_2 = x1.dividedBy(x2);  // 1/2 / 1/3 = 3/2
		
		Rational x3 = new Rational(3, 10);
		Rational x4 = new Rational(3, 5);
		Rational x3_dividedBy_4 = x3.dividedBy(x4);  // 3/10 / 3/5 = 1/2
		Rational x1_dividedBy_3 = x1.dividedBy(x3);  // 1/2 / 3/10 = 5/3

		assertEquals("3/2", x1_dividedBy_2.toString()); // compare the expected string representation
		assertEquals("1/2", x3_dividedBy_4.toString());
		assertEquals("5/3", x1_dividedBy_3.toString());
	}
}
