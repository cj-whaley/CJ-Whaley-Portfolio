/**
 * @author cjwhaley
 */

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MediaTest {
    
    IMedia i1 = new Image("flower.gif", 57234, 100, 50, "medium");
    IMedia t1 = new Text("welcome.txt", 5312, 830);
    IMedia s1 = new Sound("theme.mp3", 40960, 210);
    IMedia i2 = new Image("dog.jpg", 4000, 40, 60, "low");
    IMedia t2 = new Text("hello.txt", 2500, 1);
    IMedia s2 = new Sound("pow.mp3", 68240, 2);

    @Test
	void testTimeToDownload() {
		assertEquals(5723, this.i1.timeToDownload(10));
		assertEquals(5312, this.t1.timeToDownload(1));
		assertEquals(2048, this.s1.timeToDownload(20));
		assertEquals(20, this.i2.timeToDownload(200));
		assertEquals(8, this.i2.timeToDownload(500));
		assertEquals(500, this.t2.timeToDownload(5));
		assertEquals(853, this.s2.timeToDownload(80));
	}
    
    @Test
	void testSmallerThan() {
		assertTrue(this.i1.smallerThan(60000));
		assertTrue(this.i1.smallerThan(57235));
		assertFalse(this.i1.smallerThan(57234));
		assertFalse(this.i1.smallerThan(250));
		assertTrue(this.t1.smallerThan(5500));
		assertFalse(this.t1.smallerThan(5000));
		assertTrue(this.s1.smallerThan(50000));
		assertFalse(this.s1.smallerThan(40960));
		assertFalse(this.s1.smallerThan(5));
	}
    
    @Test
	void testSameName() {
		assertTrue(this.i1.sameName("flower.gif"));
		assertFalse(this.i1.sameName("flower"));
		assertFalse(this.i1.sameName("flower.png"));
		assertFalse(this.i1.sameName("rose.gif"));
		assertTrue(this.t1.sameName("welcome.txt"));
		assertFalse(this.t1.sameName("hello.txt"));
		assertTrue(this.s1.sameName("theme.mp3"));
		assertFalse(this.s1.sameName("theme.mp4"));
		assertTrue(this.i2.sameName("dog.jpg"));
		assertFalse(this.i2.sameName("flower.gif"));
	}
}