
/**
 * represents a leaderboard entry, with a name and a bank account balance
 */
public class Score {

	private int bank;
	private String name;

	Score(int bank, String name) {
		super();
		this.bank = bank;
		this.name = name;
	}

	@Override
	public String toString() {
		return "Score [bank=" + bank + ", name=" + name + "]";
	}

	public int getBank() {
		return bank;
	}

	public String getName() {
		return name;
	}



}
