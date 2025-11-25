import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Leaderboard {

	private ArrayList<Score> lb;


	
	Leaderboard() {
		loadLB();
	}
	
	public void updateLB(Score score1) {
		int pos = -1;
		
		for (int i = 0; i < lb.size(); i++) {
			Score sc = lb.get(i);
			if (sc.getName().equals(score1.getName())) {
				pos = i;
			}
		}
		
		if (pos < 0) {			
			this.lb.add(score1);
		} else {
			this.lb.set(pos, score1);
		}
		
		
	}
	
	/*
	 * loads the users name and bank balance from a text file
	 */
	public void loadLB() {
		this.lb = new ArrayList<Score>();
		
		try {
			Scanner sc = new Scanner(new File("results.txt"));

			while (sc.hasNextInt()) {
				int bank =  sc.nextInt();
				String name = sc.nextLine().trim();
				updateLB(new Score(bank, name));
			}	

			lb.sort(new BankComparator().reversed());
			sc.close();
		} catch (IOException exp) {
			System.out.println("Problem loading game: " + exp.getMessage());
		}
	}
	
	public void saveLB() {

		try {
			PrintWriter pw = new PrintWriter("results.txt");
			
			for (Score score : lb) {			 // for-each
				pw.println(score.getBank() + " " + score.getName());

			}
			pw.close();

		}  catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	


	@Override
	public String toString() {
		return "Leaderboard [lb=" + lb + "]";
	}

	public ArrayList<Score> getLb() {
		return lb;
	}

	public void setLb(ArrayList<Score> lb) {
		this.lb = lb;
	}
	
}
