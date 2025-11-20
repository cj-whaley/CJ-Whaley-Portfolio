import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ListTest {

	Author eh = new Author("Hemingway", 1900);
	Author ebw = new Author("White", 1920);
	Author mf = new Author("MF", 1970);

	Book oms = new Book("Old Man and the Sea", this.eh, 10, 'F');
	Book eos = new Book("Elements of Style", this.ebw, 20, 'N');
	Book htdp = new Book("HtDP", this.mf, 60, 'T');
	Book ll = new Book("Little Lisper", this.mf, 30, 'N');

	// GENERIC  lists
	
	ILoX<Book> blist3 = new Cons<Book>(oms, 
					new Cons<Book>(eos, 
					   new Cons<Book>(htdp, new MT<Book>())));
	
	ILoX<Author> alist3 = new Cons<Author>(eh,
					new Cons<Author>(ebw,
					   new Cons<Author>(mf, new MT<Author>())));
	
	
	@Test
	void test() {
		
		
		assertEquals( new Cons<Author>(mf, new MT<Author>())  ,
				alist3.select(new BornAfter1940()));
		
		assertEquals(new Cons<Book>(eos, new MT<Book>()), 
				blist3.select(new NonFictionPredicate()));
		
		
	}

}