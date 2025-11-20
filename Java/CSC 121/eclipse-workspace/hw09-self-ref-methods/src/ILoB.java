/**
 * @author cjwhaley
 */
// to represent a list of books

import java.util.Objects;

interface ILoB {
	// public ??? ilobMethod(...);

	// computes the total sale price of all books in *this* list
	public int totalPrice();

	// sort *this* list of books by sale price
	public ILoB sortByPrice();

	// insert a book into this list, sorted by sale price
	public ILoB insertByPrice(Book b);

	// select from this list those books written by the given author
	public ILoB selectByAuthor(Author a);

	// produce the book with cheapest sale price in this list
	public Book cheapestBook();
	/* in the MTLoB case, raise an error using this statement 
	     instead of a return:
	         throw new RuntimeException("The list is empty!");
	 */

	// produce the book with cheapest sale price in this list; given the
	// cheapest book seen so far in the list (accumulator-style method)
	public Book cheapestBookAcc(Book cheapest);
}


// an empty list of books
class MTLoB implements ILoB {

	MTLoB() {}

	// computes the total sale price of all books in *this* list
	public int totalPrice() {
		return 0;
	}

	// sort *this* list of books by sale price
	public ILoB sortByPrice() {
		return new MTLoB();
	}

	// insert a book into this list, sorted by sale price
	public ILoB insertByPrice(Book b) {
		return new ConsLoB(b, new MTLoB());
	}

	// select from this list those books written by the given author
	public ILoB selectByAuthor(Author a) {
		return new MTLoB();
	}

	// produce the book with cheapest sale price in this list
	public Book cheapestBook() {
		throw new RuntimeException("The list is empty!");
	}
	/* in the MTLoB case, raise an error using this statement 
	     instead of a return:
	         throw new RuntimeException("The list is empty!");
	 */

	// produce the book with cheapest sale price in this list; given the
	// cheapest book seen so far in the list (accumulator-style method)
	public Book cheapestBookAcc(Book cheapest) {
		return cheapest;
	}


	/* TEMPLATE:
  public ??? ilobMethod(...) {
  }
	 */

	@Override
	public boolean equals(Object other) {
		return other instanceof MTLoB;
	}

	@Override
	public int hashCode() {
		return MTLoB.class.hashCode();
	}

	@Override
	public String toString() {
		return "MTLoB []";
	}
}


// to represent a book added to a list of books
class ConsLoB implements ILoB {
	Book first;
	ILoB rest;

	ConsLoB(Book first, ILoB rest) {
		this.first = first;
		this.rest = rest;
	}

	// computes the total sale price of all books in *this* list
	public int totalPrice() {
		return this.first.salePrice() + this.rest.totalPrice();
	}

	// sort *this* list of books by sale price
	public ILoB sortByPrice() {
		return this.rest.insertByPrice(this.first);
	}

	// insert a book into this list, sorted by sale price
	public ILoB insertByPrice(Book b) {
		if(b.salePrice() < this.first.salePrice()) {
			return new ConsLoB(b, new ConsLoB(this.first, this.rest));
		}
		else {
			return new ConsLoB(this.first, new ConsLoB(b, this.rest));
		}
	}

	// select from this list those books written by the given author
	public ILoB selectByAuthor(Author a) {
		if(this.first.author.equals(a)) {
			return new ConsLoB(this.first, this.rest.selectByAuthor(a));
		}
		else {
			return this.rest.selectByAuthor(a);
		}
	}

	// produce the book with cheapest sale price in this list
	public Book cheapestBook() {
		return this.cheapestBookAcc(this.first);
	}
	/* in the MTLoB case, raise an error using this statement 
	     instead of a return:
	         throw new RuntimeException("The list is empty!");
	 */

	// produce the book with cheapest sale price in this list; given the
	// cheapest book seen so far in the list (accumulator-style method)
	public Book cheapestBookAcc(Book cheapest) {
		if (cheapest.salePrice() < this.first.salePrice()) {
			return this.rest.cheapestBookAcc(cheapest);
		}
		else {
			return this.rest.cheapestBookAcc(this.first);
		}
	}

	/* TEMPLATE:
  public ??? ilobMethod(...) {
    ... this.first ...     -- Book
    ... this.rest ...     -- ILoB

    ... this.first.bookMethod(...) -- ???
    ... this.rest.ilobMethod(...) -- ???
  }
	 */

	@Override
	public int hashCode() {
		return Objects.hash(first, rest);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ConsLoB other = (ConsLoB) obj;
		return Objects.equals(first, other.first) && Objects.equals(rest, other.rest);
	}

	@Override
	public String toString() {
		return "ConsLoB [first=" + first + ", rest=" + rest + "]";
	}

}




/* EXERCISES:

 - Compute the total sale price of all books in this list
 - Sort book list by sale price
 - Produce list of books written by a given author
 - Find cheapest book in the list (two ways; from scratch/sorting it first)
   (signaling error in empty list: Util.error(String) -- section 12.2 (page 131)

 */