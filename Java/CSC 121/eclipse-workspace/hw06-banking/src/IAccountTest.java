import static org.junit.Assert.*;
import org.junit.Test;

public class IAccountTest {

	Checking AS = new Checking("Aubrey Smith", 123, 150, 50);
	Savings BJ = new Savings("Bailey Jones", 456, 120, 2.5);
	CD PM = new CD("Pat Malloy", 334, 300, false);
	CD MR = new CD("Matt Ryan", 556, 200, true);
	Checking AR = new Checking("Austin Riley", 027, 25, 25);
	Checking SM = new Checking("Sean Murphy", 223, 300, 300);

	@Test
	public void testAmtAvailable() {
		assertEquals(100, this.AS.amtAvailable());
		assertEquals(120, this.BJ.amtAvailable());
		assertEquals(0, this.PM.amtAvailable());
		assertEquals(200, this.MR.amtAvailable());
		assertEquals(0, this.AR.amtAvailable());
		assertEquals(0, this.SM.amtAvailable());
	}
	
	@Test
	public void testMoreAvailable() {
		assertEquals(false, this.AS.moreAvailable(BJ));
		assertEquals(true, this.AS.moreAvailable(AR));
		assertEquals(true, this.BJ.moreAvailable(AS));
		assertEquals(false, this.BJ.moreAvailable(MR));
		assertEquals(true, this.AS.moreAvailable(PM));
		assertEquals(true, this.MR.moreAvailable(AR));
	}
	
	@Test
	public void testWithdraw() {
		assertEquals(AS, this.AS.withdraw(250));
		assertEquals(new Checking("Aubrey Smith", 123, 100, 50), this.AS.withdraw(50));
		assertEquals(BJ, this.BJ.withdraw(250));
		assertEquals(new Savings("Bailey Jones", 456, 0, 2.5), this.BJ.withdraw(120));
		assertEquals(PM, this.PM.withdraw(250));
		assertEquals(MR, this.MR.withdraw(250));
		assertEquals(new CD("Matt Ryan", 556, 50, true), this.MR.withdraw(150));
	}
	
}
