
import java.util.Objects;


/** to represent a list of books */
interface ILoB {
	
	ILoB select(IPredicate <Book> pred);

	/** produce the book that is the minimum according to the
	 * given comparator
	 */
	public Book min(IBookComparator comp);
	public Book minSoFar(IBookComparator comp, Book minSoFar);

}


/** an empty list of books */
class MTLoB implements ILoB {
	MTLoB() {}

	public ILoB select(IPredicate <Book> pred) {
		return this;
	}
	
	/** produce the book that is the minimum according to the
	 * given comparator
	 */
	public Book min(IBookComparator comp) {
		throw new RuntimeException("no books in the list");
	}
	
	public Book minSoFar(IBookComparator comp, Book minSoFar) {
		return minSoFar;
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


/** to represent a book added to a list of books */
class ConsLoB implements ILoB {
	Book first;
	ILoB rest;

	ConsLoB(Book first, ILoB rest) {
		this.first = first;
		this.rest = rest;
	}


	public ILoB select(IPredicate <Book> pred) {
		if (pred.satisfiedBy(this.first)) {
			return new ConsLoB(this.first, this.rest.select(pred));
		} else {
			return this.rest.select(pred);
		}
	}
	
	/** produce the book that is the minimum according to the
	 * given comparator
	 */
	public Book min(IBookComparator comp) {
		return this.rest.minSoFar(comp, this.first);
	}
	
	public Book minSoFar(IBookComparator comp, Book minSoFar) {
		if (comp.compare(this.first, minSoFar) < 0) {
			return this.rest.minSoFar(comp, this.first);
		} else {
			return this.rest.minSoFar(comp, minSoFar);
		}
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

}



