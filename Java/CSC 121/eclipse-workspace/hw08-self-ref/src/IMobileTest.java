/**
 * @author cjwhaley
 */
import static org.junit.Assert.*;
import org.junit.Test;

public class IMobileTest {

	IMobile simpleMobile = new MobileEnd(2, 10, "blue");
	IMobile complexMobile = new MobileStrut(4, 13, 
			new MobileStrut(2, 7, 
					new MobileEnd(1, 10, "red"), 7, simpleMobile), 6, 
			new MobileEnd(3, 40, "green"));
	IMobile sMobile1 = new MobileEnd(5, 25, "orange");
	IMobile sMobile2 = new MobileEnd(2, 40, "purple");
	IMobile veryComplexMobile = new MobileStrut(6, 9, sMobile1, 11, 
			new MobileStrut(4, 2, sMobile2, 5, 
					new MobileStrut(5, 3, simpleMobile, 8, simpleMobile)));

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
