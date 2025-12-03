import java.util.Objects;

/** represents a list of
 * things of the 'same' type (where
 * the type could be anything) */
public interface ILoX<Z> {

	 ILoX<Z> select(IPredicate<Z> pred);
	 
	 Z min(IComparator<Z> comp);
	 
	 Z minSoFar(IComparator<Z> comp, Z minSoFar);

}


/** represents an empty
 * list of anything */
class MT<S> implements ILoX<S> {
	
	public ILoX<S> select(IPredicate<S> pred) {
		return this;
	}
	
	@Override
	public boolean equals(Object other) {
		return other instanceof MT;
	}

	@Override
	public int hashCode() {
		return MT.class.hashCode();
	}

	@Override
	public String toString() {
		return "MT<S> []";
	}
	
	public S min(IComparator<S> comp){
		throw new RuntimeException("no instance found in the list");
	}
	
	public S minSoFar(IComparator<S> comp, S minSoFar) {
		return minSoFar;
	}

}


/** to represent a list
 * with something added to it */
class Cons<T> implements ILoX<T> {
	 T first;
	 ILoX<T> rest;
	 
	Cons(T first, ILoX<T> rest) {
		super();
		this.first = first;
		this.rest = rest;
	}
	
	
	public ILoX<T> select(IPredicate<T> pred) {
		if (pred.satisfiedBy(this.first)) {
			return new Cons<T>(this.first, this.rest.select(pred));
		} else {
			return this.rest.select(pred);
		}
	}
	
	public T min(IComparator<T> comp) {
		return this.rest.minSoFar(comp, this.first);
	}
	
	public T minSoFar(IComparator<T> comp, T minSoFar) {
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
		Cons<?> other = (Cons<?>) obj;
		return Objects.equals(first, other.first) && Objects.equals(rest, other.rest);
	}
	
	
}



