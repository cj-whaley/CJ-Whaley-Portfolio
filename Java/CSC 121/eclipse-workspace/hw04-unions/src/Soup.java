import java.util.Objects;

/**
 *  represents a soup with different flavors
 *  @author cjwhaley
 */
public class Soup {
	String flavor;

	Soup(String flavor) {
		super();
		this.flavor = flavor;
	}

	@Override
	public int hashCode() {
		return Objects.hash(flavor);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Soup other = (Soup) obj;
		return Objects.equals(flavor, other.flavor);
	}
	

}
