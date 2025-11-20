
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class BookTest {

	Author eh = new Author("Hemingway", 1900);
	Author ebw = new Author("White", 1920);
	Author mf = new Author("MF", 1970);

	Book oms = new Book("Old Man and the Sea", this.eh, 10, 'F');
	Book eos = new Book("Elements of Style", this.ebw, 20, 'N');
	Book htdp = new Book("HtDP", this.mf, 60, 'T');
	Book ll = new Book("Little Lisper", this.mf, 30, 'N');

	ILoB mtlob = new MTLoB();
	ILoB blist2 = new ConsLoB(this.eos, new ConsLoB(this.htdp, this.mtlob));
	ILoB blist3 = new ConsLoB(this.oms, this.blist2);
	ILoB blist2sorted = new ConsLoB(this.eos, new ConsLoB(this.htdp, this.mtlob));
	ILoB blist4 = new ConsLoB(this.ll, this.blist3);

	@Test
	void testSalePrice() {
		assertEquals(this.oms.salePrice(), 7);
		assertEquals(this.eos.salePrice(), 16);
		assertEquals(this.htdp.salePrice(), 60);
		assertEquals(this.ll.salePrice(), 24);
	}

	@Test
	void testWrittenBy() {
		assertEquals(this.htdp.writtenBy(new Author("MF", 1970)), true);
		assertEquals(this.htdp.writtenBy(new Author("SK", 1975)), false);
		assertEquals(this.eos.writtenBy(this.ebw), true);
	}

	@Test
	void testSelectsPred() {
		assertEquals(this.mtlob, this.mtlob.select(new NonFictionPredicate()));
		assertEquals(this.mtlob, this.mtlob.select(new Under20Predicate()));

		assertEquals(new ConsLoB(this.ll, new ConsLoB(this.eos, this.mtlob)),
				this.blist4.select(new NonFictionPredicate()));
		assertEquals(new ConsLoB(this.oms, new ConsLoB(this.eos, this.mtlob)),
				this.blist4.select(new Under20Predicate()));

		assertEquals(new ConsLoB(this.htdp, this.mtlob),
				this.blist3.select(new ByAuthorPredicate(this.mf)));
		assertEquals(this.mtlob, this.blist2.select(new ByAuthorPredicate(this.eh)));
		assertEquals(new ConsLoB(this.ll, new ConsLoB(this.htdp, this.mtlob)),
				this.blist4.select(new ByAuthorPredicate(this.mf)));

	}
	
	@Test
	void testMin() {
		assertEquals( oms, blist4.min(new BookPriceComparator() ));
	}

}

