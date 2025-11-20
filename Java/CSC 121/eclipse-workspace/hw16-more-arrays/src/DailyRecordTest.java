

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


class DailyRecordTest {


	@Test
	void testArbitraryDailyRecords() {
		testSetArbitrary(new BasicDailyRecord());
		// testSetSequential(new BasicDailyRecord());

		//testSetArbitrary(new ParallelDailyRecord());
	}

	@Test
	void testSeqDailyRecords() {
		testSetSequential(new SeqDailyRecord());
	}

	@Test
	void testBasicDailyRecords() {
		testBasic(new BasicDailyRecord());
	}

	@Test
	void testSeq() {
		testSequential(new SeqDailyRecord());
	}


	/* 
	 * Tests implementations of IDailyRecord that require
	 * recording hour daily in sequential order, i.e.
	 * you cannot record a temperature for hour h until readings for
	 * hours 0...(h-1) have been recorded.

	 * If hours 0...(h-1) have been recorded, you can *rerecord* a temperature
	 * for any hour h' < h.
	 * 
	 * This method should be called with an initially 
	 * empty daily record
	 */
	void testSetSequential(IDailyRecord dr) {
		assertThrows(IllegalArgumentException.class, () -> dr.getTemp(10));
		assertThrows(IllegalArgumentException.class, () -> dr.getTemp(21));
		assertThrows(IllegalStateException.class, () -> dr.getHigh());
		assertThrows(IllegalStateException.class, () -> dr.getLow());
		assertThrows(IllegalStateException.class, () -> dr.getAverage());

		// not sequential...
		assertThrows(IllegalArgumentException.class, () -> dr.recordTemp(10, 68));

		dr.recordTemp(0, 68);
		assertEquals(68, dr.getTemp(0));
		assertThrows(IllegalArgumentException.class, () -> dr.getTemp(21)); // not affected

		assertEquals(68, dr.getHigh());
		assertEquals(68, dr.getLow());
		assertEquals(68, dr.getAverage());

		dr.recordTemp(1, 73);
		dr.recordTemp(2, 59);
		dr.recordTemp(3, 62);

		assertEquals(73, dr.getHigh());
		assertEquals(59, dr.getLow());
		assertEquals(65, dr.getAverage());  

		dr.recordTemp(1, 62);   // change
		assertEquals(68, dr.getHigh());
		assertEquals(59, dr.getLow());
		assertEquals(62, dr.getAverage());  
	}


	/* 
	 * Tests implementations of IDailyRecord that allow 
	 * setting hourly data in arbitrary order 
	 * 
	 * This method should be called with an initially 
	 * empty daily record
	 */
	void testSetArbitrary(IDailyRecord dr) {
		assertThrows(IllegalArgumentException.class, () -> dr.getTemp(10));
		assertThrows(IllegalArgumentException.class, () -> dr.getTemp(21));
		assertThrows(IllegalStateException.class, () -> dr.getHigh());
		assertThrows(IllegalStateException.class, () -> dr.getLow());
		assertThrows(IllegalStateException.class, () -> dr.getAverage());

		assertThrows(IllegalArgumentException.class, () -> dr.recordTemp(-3, 83)); // invalid hour
		assertThrows(IllegalArgumentException.class, () -> dr.recordTemp(24, 75)); // invalid hour
		assertThrows(IllegalArgumentException.class, () -> dr.recordTemp(27, 71)); // invalid hour

		dr.recordTemp(10, 68);
		assertEquals(68, dr.getTemp(10));
		assertThrows(IllegalArgumentException.class, () -> dr.getTemp(21)); // not affected

		assertEquals(68, dr.getHigh());
		assertEquals(68, dr.getLow());
		assertEquals(68, dr.getAverage());

		dr.recordTemp(14, 73);
		dr.recordTemp(6, 59);
		dr.recordTemp(22, 62);

		assertEquals(73, dr.getHigh());
		assertEquals(59, dr.getLow());
		assertEquals(65, dr.getAverage());

		dr.recordTemp(14, 62);   // change
		assertEquals(68, dr.getHigh());
		assertEquals(59, dr.getLow());
		assertEquals(62, dr.getAverage());  

	}

	void testBasic(IDailyRecord dr) {
		assertThrows(IllegalArgumentException.class, () -> dr.recordTemp(-12, 83)); // invalid hour
		assertThrows(IllegalArgumentException.class, () -> dr.recordTemp(24, 75));  // invalid hour
		assertThrows(IllegalArgumentException.class, () -> dr.recordTemp(-1, 71));  // invalid hour
		assertThrows(IllegalArgumentException.class, () -> dr.recordTemp(32, 3));   // invalid hour

		assertThrows(IllegalArgumentException.class, () -> dr.recordTemp(0, -461));   // invalid temp
		assertThrows(IllegalArgumentException.class, () -> dr.recordTemp(3, -1498));  // invalid temp
		assertThrows(IllegalArgumentException.class, () -> dr.recordTemp(7, 1001));   // invalid temp
		assertThrows(IllegalArgumentException.class, () -> dr.recordTemp(1, 2704));   // invalid temp


		dr.recordTemp(10, 55);
		assertEquals(55, dr.getTemp(10));
		assertThrows(IllegalArgumentException.class, () -> dr.getTemp(21));

		assertEquals(55, dr.getHigh());
		assertEquals(55, dr.getLow());
		assertEquals(55, dr.getAverage());

		dr.recordTemp(0, -460);
		dr.recordTemp(23, 1000);
		
		assertEquals(1000, dr.getHigh());
		assertEquals(-460, dr.getLow());
		assertEquals(198, dr.getAverage());


	}

	void testSequential(IDailyRecord dr) {
		assertThrows(IllegalArgumentException.class, () -> dr.recordTemp(-12, 83)); // invalid hour, hour is outside the 0-23 bounds
		assertThrows(IllegalArgumentException.class, () -> dr.recordTemp(24, 75));  // invalid hour, hour is outside the 0-23 bounds
		assertThrows(IllegalArgumentException.class, () -> dr.recordTemp(-1, 71));  // invalid hour, hour is outside the 0-23 bounds
		assertThrows(IllegalArgumentException.class, () -> dr.recordTemp(32, 3));   // invalid hour, hour is outside the 0-23 bounds
		assertThrows(IllegalArgumentException.class, () -> dr.recordTemp(1, 3));    // invalid hour, hour 0 has not been recorded

		assertThrows(IllegalArgumentException.class, () -> dr.recordTemp(0, -461));   // invalid temp
		assertThrows(IllegalArgumentException.class, () -> dr.recordTemp(3, -1498));  // invalid temp
		assertThrows(IllegalArgumentException.class, () -> dr.recordTemp(7, 1001));   // invalid temp
		assertThrows(IllegalArgumentException.class, () -> dr.recordTemp(1, 2704));   // invalid temp
		
		dr.recordTemp(0, 12);
		assertEquals(12, dr.getTemp(0));
		assertThrows(IllegalArgumentException.class, () -> dr.getTemp(2));
		
		assertEquals(12, dr.getHigh());
		assertEquals(12, dr.getLow());
		assertEquals(12, dr.getAverage());
		
		dr.recordTemp(1, 18);
		dr.recordTemp(2, 1000);
		dr.recordTemp(3, -460);
		
		assertEquals(1000, dr.getTemp(2));
		
		assertEquals(1000, dr.getHigh());
		assertEquals(-460, dr.getLow());
		assertEquals(142, dr.getAverage());
	}


}