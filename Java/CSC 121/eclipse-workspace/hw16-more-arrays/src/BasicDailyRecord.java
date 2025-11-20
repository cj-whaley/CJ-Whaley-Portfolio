

import java.util.Arrays;

/** Represents 24 hourly temperature readings */
public class BasicDailyRecord implements IDailyRecord {

	private int[] readings;

	public BasicDailyRecord() {
		this.readings = new int[24];
		for(int i = 0; i < readings.length; i = i + 1) {
			this.readings[i] = Integer.MIN_VALUE;
		}
	}

	// package access only
	BasicDailyRecord(int[] readings) {
		this.readings = readings;
	}

	/** 
	 *  Records the given temperature value for the given
	 *  hour (specified in 24-hour, or "military" time, 0 ... 23).
	 */
	public void recordTemp(int hour, int temp) {
		if (hour < 0 || hour > 23) {
			throw new IllegalArgumentException("invalid hour " + hour);
		}
		else if(temp < -460 || temp > 1000) {
			throw new IllegalArgumentException("invalid temperature " + temp);
		}
		this.readings[hour] = temp;
	}

	/**
	 * Returns the recorded temperature value for the given hour
	 */
	public int getTemp(int hour) {
		if (this.readings[hour] == Integer.MIN_VALUE) {
			throw new IllegalArgumentException("No reading for hour " + hour);
		}
		return this.readings[hour];
	}

	/**
	 * Returns the highest recorded temperature value
	 */
	public int getHigh() {

		int maxReading = Integer.MIN_VALUE;
		boolean updated = false;

		for(int i = 0; i < readings.length; i = i + 1) {
			if(readings[i] != Integer.MIN_VALUE) {
				updated = true;
			}
		}

		if(updated) {
			for(int j = 0; j < readings.length; j = j + 1) {
				if (this.readings[j] > maxReading) {
					maxReading = this.readings[j];
				}
			}
			return maxReading;
		}
		else {
			throw new IllegalStateException("No readings have been set.");
		}
	}

	/**
	 * Returns the lowest recorded temperature value 
	 */
	public int getLow() {
		int minReading = Integer.MAX_VALUE;
		boolean updated = false;

		for(int i = 0; i < readings.length; i = i + 1) {
			if(readings[i] != Integer.MIN_VALUE) {
				updated = true;
			}
		}

		if(updated) {
			for(int j = 0; j < readings.length; j = j + 1) {
				if(this.readings[j] != Integer.MIN_VALUE) {
					if (this.readings[j] < minReading) {
						minReading = this.readings[j];
					}
				}
			}
			return minReading;
		}
		else {
			throw new IllegalStateException("No readings have been set.");
		}
	}

	/**
	 * Returns the average temperature
	 */
	public int getAverage() {
		int sum =  0;
		int count = 0;
		boolean updated = false;

		for(int i = 0; i < readings.length; i = i + 1) {
			if(readings[i] != Integer.MIN_VALUE) {
				updated = true;
			}
		}

		if(updated) {
			for(int j = 0; j < readings.length; j = j + 1) {
				if(this.readings[j] != Integer.MIN_VALUE) {
					sum = sum + readings[j];
					count = count + 1;
				}
			}
			return sum / count;
		}
		else {
			throw new IllegalStateException("No readings have been set.");
		}
	}



	// auto-generated methods

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(readings);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BasicDailyRecord other = (BasicDailyRecord) obj;
		return Arrays.equals(readings, other.readings);
	}

}
