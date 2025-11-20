package class08;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class IATTest {

	Person sf = new Person("SF", 1732);
	Person pw = new Person("PW", 1729);
	Person jw = new Person("JW", 1717);
	Person mf = new Person("MF", 1727);
	Person mw = new Person("MW", 1763, jw, mf);
	Person nf = new Person("NF", 1764, sf, pw);
	Person df = new Person("DF", 1794, nf, mw);
	
	@Test
	void test() {
		fail("Not yet implemented");
	}

}
