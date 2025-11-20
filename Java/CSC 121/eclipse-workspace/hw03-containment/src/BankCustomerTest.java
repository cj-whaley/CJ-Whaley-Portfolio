import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BankCustomerTest {

	BankCustomer cp = new BankCustomer("Cole", "Porter");
	BankCustomer ak = new BankCustomer("Alex", "Knapp");
	BankCustomer bo = new BankCustomer("Brenden", "O'Sullivan");
	
	@Test
	void testOpenAccount() {
		assertEquals(new BankAccount(cp, 2000), this.cp.openAccount(2000));
		assertEquals(new BankAccount(ak, 1), this.ak.openAccount(1));
		assertEquals(new BankAccount(bo, 6900), this.bo.openAccount(6900));
	}

}
