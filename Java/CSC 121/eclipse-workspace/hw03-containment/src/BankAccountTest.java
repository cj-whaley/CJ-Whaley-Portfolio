import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BankAccountTest {

	BankCustomer cp = new BankCustomer("Cole", "Porter");
	BankCustomer ak = new BankCustomer("Alex", "Knapp");
	BankCustomer bo = new BankCustomer("Brenden", "O'Sullivan");
	
	
	BankAccount cpChecking = new BankAccount(cp, 13579);
	BankAccount cpSavings = new BankAccount(cp, 2439521);
	BankAccount akChecking = new BankAccount(ak, -1398);
	BankAccount boSavings = new BankAccount(bo, 0);
	
	@Test
	void testStatusMessage() {
		assertEquals("Positive Balance", this.cpChecking.statusMessage());
		assertEquals("Positive Balance", this.cpSavings.statusMessage());
		assertEquals("No Money", this.boSavings.statusMessage());
		assertEquals("Overdrawn", this.akChecking.statusMessage());
	}
}
