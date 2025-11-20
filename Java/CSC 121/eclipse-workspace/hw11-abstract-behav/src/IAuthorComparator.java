import java.util.Objects;


interface IAuthorComparator {
	/* produces a negative # if author a is "less than" author b according to some
	 * 						criteria
	 * produces 0 (zero) if the authors are the same according to some characteristic
	 * produces a positive # if author a is "greater than" b according to some characteristic
	 */
	int compare(Author a, Author b);
}

class YoBComparator implements IAuthorComparator {
	public int compare(Author a, Author b) {
		return a.yob - b.yob;
	}
}
/**
 * used to compare the length of two Authors names  
 */
class NameLengthComparator implements IAuthorComparator {
	public int compare(Author a, Author b) {
		return a.name.length() - b.name.length();
	}
}




/** to represent a list of authors */
interface ILoA {
	
	/** produce the author with the minimum value based on some
	 * comparison characteristic
	 */
	public Author min( IAuthorComparator m );   // <---- earliestYoB() and shortestName()
	public Author minSoFar(IAuthorComparator m, Author minSoFarCandidate );
	
}


/** an empty list of authors */
class MTLoA implements ILoA {
	MTLoA() {}
	
	/** produce the author with the minimum value based on some
	 * comparison characteristic in this list
	 */
	public Author min( IAuthorComparator m ) {
		throw new RuntimeException("no authors in the list");
	}
	
	
	public Author minSoFar( IAuthorComparator m, Author minSoFarCandidate ) {
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
	
	/** produce the author with the minimum value based on some
	 * comparison characteristic in this list
	 */
	public Author min( IAuthorComparator m ) {
		return this.rest.minSoFar(m, this.first);
	}
	
	
	public Author minSoFar( IAuthorComparator m,       Author minSoFarCandidate ) {
		if (m.compare( this.first, minSoFarCandidate ) < 0) {
				// this.first......... < minSoFarCandidate..........) {
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