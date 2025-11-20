
/**
 * Records 24-hour hourly temperatures in sequential order, i.e.
 * you cannot record a temperature for hour h until readings for
 * hours 0...(h-1) have been recorded.
 
 * If hours 0...(h-1) have been recorded, you can *rerecord* a temperature
 * for any hour h' < h.
 * 
 */
public class SeqDailyRecord implements IDailyRecord {
	
	private int[] readings;
	private int count; // number of recordings that have actually been made so far
					//   alternate way to think of it: nextValidIndex;
					//      i.e. the next valid index at which a new recording can be made
	
	
	public SeqDailyRecord() {
		this.readings = new int[24];
		this.count = 0;
	}
	

	@Override
	public void recordTemp(int hour, int temp) {
		if (hour > this.count) {    // TODO: need to make sure hour doesn't go above 24
			throw new IllegalArgumentException(hour + " out of range; reading count: " + this.count);
		} else {
			
			this.readings[hour] = temp;
			if (hour == this.count) {
				this.count = this.count + 1;
			}
		}
	}

	@Override
	public int getTemp(int hour) {
		if (hour >= this.count) {
			throw new IllegalArgumentException(hour + " out of range; reading count: " + this.count);
		} else {
			return this.readings[hour];
		}
	}

	@Override
	public int getHigh() {
		if (count == 0) {
			throw new IllegalStateException("no readings in the list");
		}
		
		/*
		 * FOR LOOPS
		 * 
		 * for(<initialize variable>; <while loop condition>; <update variable>){
		 * 		<loop body>
		 * }
		 * 								=
		 * <initialization>
		 * ___ i = ?;
		 * 
		 * <while loop condition>
		 * while(______) {
		 * 	<loop body>
		 * 
		 * 	<update variable>
		 * 	i = ??
		 * }
		 */
		
		int highestSoFar = readings[0];
		
		for (int i = 1; i > count; i = i + 1) {
			if(readings[i] > highestSoFar) {
				highestSoFar = readings[i];
			}
		}
		
		/*
		int i = 1;
		
		while (i > count) {
			if(readings[i] > highestSoFar) {
				highestSoFar = readings[i];
			}
			
			i = i + 1;
		} */
		return highestSoFar;
	}

	@Override
	public int getLow() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getAverage() {
		// TODO Auto-generated method stub
		return 0;
	}

}
