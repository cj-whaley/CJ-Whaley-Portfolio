import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BookTest {

	Author knuth = new Author("Donald E. Knuth", 1938);
	Book taocp = new Book("The Art of Computer Programming (volume 1)", 100, 2, knuth);
	
	Author knuth1 = new Author("Donald E. Knuth", 1938);
	Book taocp1 = new Book("The Art of Computer Programming (volume 1)", 100, 2, knuth1);
	
	Author tardos = new Author("Eva Tardos", 1957);
	Book ad = new Book("Algorithm Design", 150, 3, tardos);
	@Test
	void test() {
		
		this.knuth.book = taocp;
		
		this.knuth1.book = taocp1;
		
		assertEquals(this.knuth, this.knuth.book.author);
		
		assertEquals(true, this.knuth.equals(this.knuth1));
	}
	
	@Test
	void testAddBook() {
		// testing void methods
		// since they do not return anything
		
		// check to see if book is correct before changing the book
		assertEquals(ad, tardos.book);
		
		// changing the book
		tardos.addBook(taocp);
		
		// check to see if the book was changed correctly
		assertEquals(taocp, tardos.book);
	}

}
