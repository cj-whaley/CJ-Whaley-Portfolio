import java.util.Objects;

/**
 * represents a salad of a some kind with some dressing on it
 * @author cjwhaley
 */
public class Salad {
	String kind;
	String dressing;
	Salad(String kind, String dressing) {
		super();
		this.kind = kind;
		this.dressing = dressing;
	}
	@Override
	public int hashCode() {
		return Objects.hash(dressing, kind);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Salad other = (Salad) obj;
		return Objects.equals(dressing, other.dressing) && Objects.equals(kind, other.kind);
	}
	
	

}
