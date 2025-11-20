/**
 * represents a part of a Mobile that only has one object hanging from it
 * @author cjwhaley
 */
import java.util.Objects;

public class MobileEnd implements IMobile {
	int length;
	int weight;
	String color;
	
	MobileEnd(int length, int weight, String color) {
		super();
		this.length = length;
		this.weight = weight;
		this.color = color;
	}

	@Override
	public int hashCode() {
		return Objects.hash(color, length, weight);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MobileEnd other = (MobileEnd) obj;
		return Objects.equals(color, other.color) && length == other.length && weight == other.weight;
	}

	@Override
	public String toString() {
		return "MobileEnd [length=" + length + ", weight=" + weight + ", color=" + color + "]";
	}

}
