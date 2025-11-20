import java.util.Objects;


/** to represent a list of books */
interface ILoB {
	
	/** produce a list of only the books on this list that meet the given predicate */
	ILoB select(IBookPredicate pred);
	
	/** determine if at least one book on this list is a fiction book */
	boolean anyFiction();
	
	/** determine if at least one book on this list has a sale price over $50 */
	boolean anyOver50();
	
	boolean any(IBookPredicate pred);
	
}


/** an empty list of books */
class MTLoB implements ILoB {
	MTLoB() {}

	/** produce a list of only the books on this list that meet the given predicate */
	public ILoB select(IBookPredicate pred) {
		return this;
	}
	
	/** determine if at least one book on this list is a fiction book */
	public boolean anyFiction() {
		return false;
	}
	
	/** determine if at least one book on this list has a sale price over $50 */
	public boolean anyOver50() {
		return false;
	}
	
	public boolean any(IBookPredicate pred) {
		return false;
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

	/** produce a list of only the books on this list that meet the given predicate */
	public ILoB select(IBookPredicate pred) {
		if (pred.satisfiedBy(this.first)) {
			return new ConsLoB(this.first, this.rest.select(pred));
		} else {
			return this.rest.select(pred);
		}
	}
	
	/** determine if at least one book on this list is a fiction book */
	public boolean anyFiction() {
		return this.first.isFiction() || this.rest.anyFiction();
	}
	
	/** determine if at least one book on this list has a sale price over $50 */
	public boolean anyOver50() {
		return this.first.salePrice() > 50 || this.rest.anyOver50();
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
	
	/** determine if at least one book on this list meets
	 *    a given criteria
	 */
	public boolean any(IBookPredicate pred) {
		return pred.satisfiedBy(first) || this.rest.any(pred);
	}

}


