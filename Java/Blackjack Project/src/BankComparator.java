import java.util.Comparator;

public class BankComparator implements Comparator<Score> {
	
	public int compare(Score s1, Score s2) {

		return s1.getBank() - s2.getBank();
	}

}
