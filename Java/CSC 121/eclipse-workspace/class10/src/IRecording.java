/**
 * represents a weather recording
 */
public interface IRecording {
	// produce today's difference from the high of this recording
		public int diffFromHigh();
		
		// produce today's difference from the low of this recording
		public int diffFromLow();
		
		// produce a string description of this recording
		  public String asString();

}
