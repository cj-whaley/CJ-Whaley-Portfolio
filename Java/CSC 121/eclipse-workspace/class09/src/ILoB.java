
// to represent a list of books

import java.util.Objects;

interface ILoB {
	// public ??? ilobMethod(...);

	// computes the total sale price of all books in *this* list
	public int totalPrice();

	// sort *this* list of books by sale price
	public ILoB sortByPrice();
}


// an empty list of books
class MTLoB implements ILoB {

	MTLoB() {}

	/* TEMPLATE:
  public ??? ilobMethod(...) {
  }
	 */

	// computes the total sale price of all books in *this* list
	public int totalPrice() {
		return 0;
	}

	// sort *this* list of books by sale price
	public ILoB sortByPrice() {
		return this;
	}

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

	/* TEMPLATE:
  public ??? ilobMethod(...) {
    ... this.first ...     -- Book
    ... this.rest ...     -- ILoB

    ... this.first.bookMethod(...) -- ???
    ... this.rest.ilobMethod(...) -- ???
  }
	 */

	// computes the total sale price of all books in *this* list
	public int totalPrice() {
		return (this.first.salePrice() + this.rest.totalPrice());
	}

	// sort *this* list of books by sale price
	public ILoB sortByPrice() {
		return this;
	}
}




/* EXERCISES:

 - Compute the total sale price of all books in this list
 - Sort book list by sale price
 - Produce list of books written by a given author
 - Find cheapest book in the list (two ways; from scratch/sorting it first)
   (signaling error in empty list: Util.error(String) -- section 12.2 (page 131)

 */