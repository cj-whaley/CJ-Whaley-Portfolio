import java.util.Scanner;
import java.io.*;

public class RecordProcessor {

	public static void main(String[] args) {
		
		// prints to the Console --- "standard output"
		System.out.println("Processing Temperature Data");
		
		System.out.println("Enter a file name:");  // prompt
		
		// get user's input from "standard input" --- usually the keyboard
		Scanner kb = new Scanner(System.in);
		String filename = kb.nextLine();
		kb.close();
		
		System.out.println("Reading from: " + filename + "...");
		
		try {
			
			
		// open the data file
		Scanner sc = new Scanner(new File(filename));
		
		
		// open an output file
		PrintWriter pw = new PrintWriter(new File("output.txt"));
		
		// read in lines and record in a bdr
		BasicDailyRecord bdr = new BasicDailyRecord();
		
		
		// while loop that runs while the file has more ints
		while(sc.hasNextInt()) {
			int hour = sc.nextInt();
			int temp = sc.nextInt();
			bdr.recordTemp(hour, temp);
		}
		
		
		pw.println("High : " + bdr.getHigh());
		pw.println("Low : " + bdr.getLow());
		pw.println("Average : " + bdr.getAverage());
		
		
		// it is polite to close your file when you are done with it
		// closing the file also saves it
		
		pw.close();   // make sure to save the output file
		
		sc.close();
		} catch (IOException e) {
			System.out.println("Something went wrong: " + e);
			e.printStackTrace();
			
		}
		

	}

}
