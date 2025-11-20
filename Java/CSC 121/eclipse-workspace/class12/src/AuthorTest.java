import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class AuthorTest {
	Author im = new Author("Mbue", 1981);
	Author sn = new Author("Novic", 1987);
	Author cf = new Author("Fuentes", 1928); 
	Author go = new Author("Orwell", 1903);
	
	ILoA mt = new MTLoA();
	ILoA alist2 = new ConsLoA(im, new ConsLoA(sn, mt));
	ILoA alist4 = new ConsLoA(cf, new ConsLoA(go, alist2));

	@Test
	public void tests() {
		assertEquals(im, alist2.shortestName());
		assertEquals(im, alist2.earliestYoB());
		
		assertEquals(im, alist4.shortestName());
		assertEquals(go, alist4.earliestYoB());
		
		assertEquals(im, alist2.min(new YoBComparator()));
		
		assertEquals(im, alist4.min(new NameComparator()));
		
		assertEquals(go, alist4.min(new YoBComparator()));
		
		assertEquals(im, alist2.min(new NameComparator()));
	}

}