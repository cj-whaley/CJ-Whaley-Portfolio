import java.util.Objects;

/** to represent a list of authors */
interface ILoA {
	
	/** produce the author with the minimum value based on some
	 *   comparison characteristic
	 */
	public Author min(IAuthorComparator m);
	public Author minSoFar(IAuthorComparator m, Author minSoFarCandidate);
	
	/** produce the author with the earliest year of birth in this list */
	public Author earliestYoB();
	
	/** produce the author with the shortest name in this list */
	public Author shortestName();

	/** produce the author with the earliest year of birth between this list 
	     and the given earliestSoFar */
	public Author earliestYoBSoFar(Author earliestSoFar);

	/** produce the author with the shortest name between this list 
    	and the given shortestSoFar */
	public Author shortestNameSoFar(Author shortestSoFar);
}


/** an empty list of authors */
class MTLoA implements ILoA {
	MTLoA() {}
		
	/** produce the author with the earliest year of birth in this list */
	public Author earliestYoB() {
		throw new RuntimeException("no authors in the list");
	}
	
	/** produce the author with the shortest name in this list */
	public Author shortestName() {
		throw new RuntimeException("no authors in the list");		
	}
	
	/** produce the author with the earliest year of birth between this list 
	    and the given earliestSoFar */
	public Author earliestYoBSoFar(Author earliestSoFar) { 
		return earliestSoFar;
	}

	/** produce the author with the shortest name between this list 
		and the given shortestSoFar */
	public Author shortestNameSoFar(Author shortestSoFar) {
		return shortestSoFar;
	}

	/** produce the author with the minimum value based on some
	 *   comparison characteristic
	 */
	public Author min(IAuthorComparator m) {
		throw new RuntimeException("no authors in the list");
	}
	
	public Author minSoFar(IAuthorComparator m, Author minSoFarCandidate) {
		return minSoFarCandidate;
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
	
	/** produce the author with the earliest year of birth in this list */
	public Author earliestYoB() {
		return this.rest.earliestYoBSoFar(this.first);
	}
	
	/** produce the author with the shortest name in this list */
	public Author shortestName() {
		return this.rest.shortestNameSoFar(this.first);
	}

	/** produce the author with the earliest year of birth between this list 
	    and the given earliestSoFar */
	public Author earliestYoBSoFar(Author earliestSoFar) { 
		if (this.first.yob < earliestSoFar.yob) {
			return this.rest.earliestYoBSoFar(this.first);
		} else {
			return this.rest.earliestYoBSoFar(earliestSoFar);
		}
	}

	/** produce the author with the shortest name between this list 
		and the given shortestSoFar */
	public Author shortestNameSoFar(Author shortestSoFar) {
		if (this.first.name.length() < shortestSoFar.name.length()) {
			return this.rest.shortestNameSoFar(this.first);
		} else {
			return this.rest.shortestNameSoFar(shortestSoFar);
		}
	}

	/** produce the author with the minimum value based on some
	 *   comparison characteristic
	 */
	public Author min(IAuthorComparator m) {
		return this.rest.minSoFar(m, this.first);
	}
	
	public Author minSoFar(IAuthorComparator m, Author minSoFarCandidate) {
		if (m.compare(this.first, minSoFarCandidate) < 0) {
			return this.rest.minSoFar(m, this.first);
		} else {
			return this.rest.minSoFar(m, minSoFarCandidate);
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