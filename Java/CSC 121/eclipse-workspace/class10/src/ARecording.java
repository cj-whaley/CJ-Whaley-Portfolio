/**
 * represents a weather recording with a 
 *  high/today/low value
 */
public abstract class ARecording implements IRecording {
	int high;
	int today;
	int low;
	String unit;

	ARecording(int high, int today, int low, String unit) {
		super();
		this.high = high;
		this.today = today;
		this.low = low;
		this.unit = unit;
	}

	// produce today's difference from the high of this recording
	public int diffFromHigh() {
		return this.high - this.today;
	}

	// produce today's difference from the low of this recording
	public int diffFromLow() {
		return this.today - this.low;
	}
	
	// produce a string description of this recording
	  public String asString() {
	    return String.valueOf(this.high)
	            .concat("-")
	            .concat(String.valueOf(this.low))
	            .concat(this.unit);
	  }


}
