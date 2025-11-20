
import java.util.Objects;


/** to represent a list of authors */
interface ILoA {
	
	ILoA select(IPredicate <Author> pred);

	/** produce the author that is the minimum according to the
	 * given comparator
	 */
	public Author min(IAuthorComparator comp);
	public Author minSoFar(IAuthorComparator comp, Author minSoFar);

}


/** an empty list of authors */
class MTLoA implements ILoA {
	MTLoA() {}
	
	public ILoA select(IPredicate <Author> pred) {
		return this;
	}
	
	/** produce the author that is the minimum according to the
	 * given comparator
	 */
	public Author min(IAuthorComparator comp) {
		throw new RuntimeException("no authors in the list");
	}
	
	public Author minSoFar(IAuthorComparator comp, Author minSoFar) {
		return minSoFar;
	}



	@Override
	public boolean equals(Object other) {
		return other instanceof MTLoA;
	}

	@Override
	public int hashCode() {
		return MTLoA.class.hashCode();
	}

	@Override
	public String toString() {
		return "MTLoA []";
	}
}


/** to represent an author added to a list of authors */
class ConsLoA implements ILoA {
	Author first;
	ILoA rest;

	ConsLoA(Author first, ILoA rest) {
		this.first = first;
		this.rest = rest;
	}
	
	public ILoA select(IPredicate <Author> pred) {
		if (pred.satisfiedBy(this.first)) {
			return new ConsLoA(this.first, this.rest.select(pred));
		} else {
			return this.rest.select(pred);
		}
	}

	
	/** produce the author that is the minimum according to the
	 * given comparator
	 */
	public Author min(IAuthorComparator comp) {
		return this.rest.minSoFar(comp, this.first);
	}
	
	public Author minSoFar(IAuthorComparator comp, Author minSoFar) {
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
		ConsLoA other = (ConsLoA) obj;
		return Objects.equals(first, other.first) && Objects.equals(rest, other.rest);
	}

	@Override
	public String toString() {
		return "ConsLoA [first=" + first + ", rest=" + rest + "]";
	}
}