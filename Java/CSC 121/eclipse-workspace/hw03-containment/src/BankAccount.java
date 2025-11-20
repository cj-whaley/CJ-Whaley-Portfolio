import java.util.Objects;

/** to represent a bank account */
class BankAccount {
     BankCustomer customer;
     int balance;
     
	BankAccount(BankCustomer customer, int balance) {
		this.customer = customer;
		this.balance = balance;
	}

	@Override
	public int hashCode() {
		return Objects.hash(balance, customer);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BankAccount other = (BankAccount) obj;
		return balance == other.balance && Objects.equals(customer, other.customer);
	}

	/*
	 *  produces a String describing the balance of a BankAccount
	 *   based on whether it is positive, negative, or zero
	 */
     public String statusMessage() {
    	 if (this.balance > 0) {
    		 return "Positive Balance";
    	 }
    	 else if (this.balance == 0) {
    		 return "No Money";
    	 }
    	 else {
    		 return "Overdrawn";
    	 }
     }
 
     
}
