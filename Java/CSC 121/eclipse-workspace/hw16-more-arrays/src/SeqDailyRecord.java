
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
		} 
		else if(hour < 0) {
			throw new IllegalArgumentException(hour + " out of range; hour value cannot be negative");
		}
		else if(temp < -460 || temp > 1000) {
			throw new IllegalArgumentException("invalid temperature " + temp);
		}
		else {
			
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
		
		int highestSoFar = this.readings[0];
		

		
		
		/*
			FOR loop:
			
			for ( <initialization> ; <condition> ; <update> ) {
				<body>
			}
			
			======>
			
			<initialization>;
			while ( true <&& condition> ) {
				<body>
				
				<update>;
			}
			
		
		
		
		 * 
		 * 
		 * 
		 * 
		 
		int i = 1;				// initialize i for looping through the array
		
		while ( i < count ) {	// loop condition
			
			if ( this.readings[i] > highestSoFar ) {
				highestSoFar = this.readings[i];
			}
			
			i = i + 1;			// increment i
		}
		*/
		

		for ( int i = 1  ;  i < count  ;  i = i + 1 ) {
			if ( this.readings[i] > highestSoFar ) {
				highestSoFar = this.readings[i];
			}
		}		
		
		return highestSoFar;
	}

	@Override
	public int getLow() {
		if (count == 0) {
			throw new IllegalStateException("no readings in the list");
		} 
		
		int lowestSoFar = this.readings[0];
		
		for(int i = 1; i < count; i = i + 1) {
			if(this.readings[i] < lowestSoFar) {
				lowestSoFar = this.readings[i];
			}
		}
		return lowestSoFar;
	}

	@Override
	public int getAverage() {
		if (count == 0) {
			throw new IllegalStateException("no readings in the list");
		} 
		
		int total = this.readings[0];
		
		for (int i = 1; i < count; i = i + 1) {
			total = total + readings[i];
		}
		return total/count;
	}

}