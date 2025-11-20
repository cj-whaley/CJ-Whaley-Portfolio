import java.util.Objects;

/**
 * represents a sandwich with some bread, filling, and extras in it
 * @author cjwhaley
 */
public class Sandwich {

	String bread;
	String filling;
	String extras;
	
	Sandwich(String bread, String filling, String extras) {
		super();
		this.bread = bread;
		this.filling = filling;
		this.extras = extras;
	}
	@Override
	public int hashCode() {
		return Objects.hash(bread, extras, filling);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sandwich other = (Sandwich) obj;
		return Objects.equals(bread, other.bread) && Objects.equals(extras, other.extras)
				&& Objects.equals(filling, other.filling);
	}
	
	
}
