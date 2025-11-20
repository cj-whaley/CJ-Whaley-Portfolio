import java.util.Objects;

/** to represent a customer at a bank */
class BankCustomer {
     String firstName;
     String lastName;
     
	@Override
	public int hashCode() {
		return Objects.hash(firstName, lastName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BankCustomer other = (BankCustomer) obj;
		return Objects.equals(firstName, other.firstName) && Objects.equals(lastName, other.lastName);
	}

	BankCustomer(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}
     /*
      *  produces a new BankAccount owned by the customer with a balance of the
      *   given deposit
      */
	public BankAccount openAccount(int deposit) {
		return new BankAccount(new BankCustomer(this.firstName, this.lastName), deposit);
	}
     
}
