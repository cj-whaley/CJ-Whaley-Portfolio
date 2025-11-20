/**
 * represents a part of a Mobile that has other Mobile parts hanging from it
 * @author cjwhaley
 */

import java.util.Objects;

public class MobileStrut implements IMobile{
	int length;
	int loffset;
	IMobile left;
	int roffset;
	IMobile right;

	MobileStrut(int length, int loffset, IMobile left, int roffset, IMobile right) {
		super();
		this.length = length;
		this.loffset = loffset;
		this.left = left;
		this.roffset = roffset;
		this.right = right;
	}

	@Override
	public int hashCode() {
		return Objects.hash(left, length, loffset, right, roffset);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MobileStrut other = (MobileStrut) obj;
		return Objects.equals(left, other.left) && length == other.length && loffset == other.loffset
				&& Objects.equals(right, other.right) && roffset == other.roffset;
	}

	@Override
	public String toString() {
		return "MobileStrut [length=" + length + ", offset=" + loffset + ", left=" + left + ", roffset=" + roffset
				+ ", right=" + right + "]";
	}

}
