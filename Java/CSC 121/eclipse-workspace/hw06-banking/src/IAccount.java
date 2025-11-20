/* A bank offers three kinds of accounts:
   a checking account with a minimum balance
   a savings account
   a certificate of deposit account with maturity date

The customer can withdraw all money from the savings account. 
When withdrawing from the checking account, the minimum balance must remain.
The customer cannot withdraw from a CD account, if is has not matured.
 */

import java.util.Objects;

/** Represents bank accounts */
interface IAccount {
	/** represents the amount of money available to be withdrawn from an account */
	int amtAvailable();

	/**represents whether a given account has more money available to withdraw than another account*/
	boolean moreAvailable(IAccount Acct);

	/** represents a bank account after a withdrawal of a given amount */
	IAccount withdraw(int Withdrawal);

}


/** Represents a checking account */
class Checking implements IAccount {
	String name;      // name of account holder
	int id;           // id number associated with the account
	int balance;      // the current balance in the account
	int minBalance;   // the minimum allowable balance for the account

	Checking(String name, int id, int balance, int minBalance) {
		this.name = name;
		this.id = id;
		this.balance = balance;
		this.minBalance = minBalance;
	}

	/* TEMPLATE:
	  public ??? checkingMethod(...) {
	    ... this.name ...               -- String
	    ... this.id ...                 -- int
	    ... this.balance ...            -- int
	    ... this.minBalance ...         -- int

	    ... this.amtAvailable() ...                   -- int
	    ... this.moreAvailable(IAccount Acct) ...     -- boolean
	    ... this.withdraw(int Withdrawal) ...         -- IAccount
	  }
	 */  

	@Override
	public int hashCode() {
		return Objects.hash(balance, id, minBalance, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Checking other = (Checking) obj;
		return balance == other.balance && id == other.id && minBalance == other.minBalance
				&& Objects.equals(name, other.name);
	}

	/** represents the amount of money available to be withdrawn from an account */
	public int amtAvailable() {
		if (this.balance > this.minBalance) {
			return this.balance - this.minBalance;
		}
		else {
			return 0;
		}
	}

	/**represents whether a given account has more money available to withdraw than another account*/
	public boolean moreAvailable(IAccount Acct) {
		return this.amtAvailable() > Acct.amtAvailable();
	}

	/** represents a bank account after a withdrawal of a given amount */
	public IAccount withdraw(int Withdrawal) {
		if (Withdrawal > this.amtAvailable()) {
			return new Checking(this.name, this.id, this.balance, this.minBalance);
		}
		else {
			return new Checking(this.name, this.id, (this.balance - Withdrawal), this.minBalance);
		}
	}

}


/** Represents a savings account */
class Savings implements IAccount {
	String name;           // name of account holder
	int id;                // id number associated with the account
	int balance;           // the current balance in the account
	double interestRate;   // the interest rate for the account

	Savings(String name, int id, int balance, double interestRate) {
		this.name = name;
		this.id = id;
		this.balance = balance;
		this.interestRate = interestRate;
	}	

	/* TEMPLATE:
	  public ??? savingsMethod(...) {
	    ... this.name ...               -- String
	    ... this.id ...                 -- int
	    ... this.balance ...            -- int
	    ... this.interestRate ...       -- double

	    ... this.amtAvailable() ...                   -- int
	    ... this.moreAvailable(IAccount Acct) ...     -- boolean
	    ... this.withdraw(int Withdrawal) ...         -- IAccount
	  }
	 */  

	@Override
	public int hashCode() {
		return Objects.hash(balance, id, interestRate, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Savings other = (Savings) obj;
		return balance == other.balance && id == other.id
				&& Double.doubleToLongBits(interestRate) == Double.doubleToLongBits(other.interestRate)
				&& Objects.equals(name, other.name);
	}

	/** represents the amount of money available to be withdrawn from an account */
	public int amtAvailable() {
		return this.balance;

	}

	/**represents whether a given account has more money available to withdraw than another account*/
	public boolean moreAvailable(IAccount Acct) {
		return this.amtAvailable() > Acct.amtAvailable();
	}

	/** represents a bank account after a withdrawal of a given amount */
	public IAccount withdraw(int Withdrawal) {
		if (Withdrawal > this.amtAvailable()) {
			return new Savings(this.name, this.id, this.balance, this.interestRate);
		}
		else {
			return new Savings(this.name, this.id, (this.balance - Withdrawal), this.interestRate);
		}
	}
}


/** Represents a certificate of deposit account */
class CD implements IAccount {
	String name;       // name of account holder
	int id;            // id number associated with the account
	int balance;       // the current balance in the account
	boolean matured;   // whether or not the account has fully matured or not

	CD(String name, int id, int balance, boolean matured) {
		this.name = name;
		this.id = id;
		this.balance = balance;
		this.matured = matured;
	}	

	/* TEMPLATE:
	  public ??? cdMethod(...) {
	    ... this.name ...               -- String
	    ... this.id ...                 -- int
	    ... this.balance ...            -- int
	    ... this.matured ...            -- boolean

	    ... this.amtAvailable() ...                   -- int
	    ... this.moreAvailable(IAccount Acct) ...     -- boolean
	    ... this.withdraw(int Withdrawal) ...         -- IAccount
	  }
	 */  

	@Override
	public int hashCode() {
		return Objects.hash(balance, id, matured, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CD other = (CD) obj;
		return balance == other.balance && id == other.id && matured == other.matured
				&& Objects.equals(name, other.name);
	}

	/** represents the amount of money available to be withdrawn from an account */
	public int amtAvailable() {
		if (this.matured) {
			return this.balance;
		}
		else {
			return 0;
		}

	}

	/**represents whether a given account has more money available to withdraw than another account*/
	public boolean moreAvailable(IAccount Acct) {
		return this.amtAvailable() > Acct.amtAvailable();
	}

	/** represents a bank account after a withdrawal of a given amount */
	public IAccount withdraw(int Withdrawal) {
		if (Withdrawal > this.amtAvailable()) {
			return new CD(this.name, this.id, this.balance, this.matured);
		}
		else {
			return new CD(this.name, this.id, (this.balance - Withdrawal), this.matured);
		}
	}
}