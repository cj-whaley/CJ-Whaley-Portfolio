/**
 * represents a more complex IMobile that contains multiple 
 *   Complex or Simple IMobiles within it
 * @author cjwhaley
 */

import java.util.Objects;

/** to represent a part of support structure for a mobile */
class Complex implements IMobile {
	int length;
	int leftSide;
	int rightSide;
	IMobile left;
	IMobile right;

	Complex(int length, int leftSide, int rightSide, 
			IMobile left, IMobile right) {
		this.length = length;
		this.leftSide = leftSide;
		this.rightSide = rightSide;
		this.left = left;
		this.right = right;
	}

	@Override
	public int hashCode() {
		return Objects.hash(left, leftSide, length, right, rightSide);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Complex other = (Complex) obj;
		return Objects.equals(left, other.left) && leftSide == other.leftSide && length == other.length
				&& Objects.equals(right, other.right) && rightSide == other.rightSide;
	}

	@Override
	public String toString() {
		return "Complex [length=" + length + ", leftSide=" + leftSide + ", rightSide=" + rightSide + ", left=" + left
				+ ", right=" + right + "]";
	}

	/* TEMPLATE:
	  public ??? complexMethod(...) {
	     FIELDS:
	    ... this.length ...        - int
	    ... this.leftSide ...      - int
	    ... this.rightSide ...     - int
	    ... this.left ...          - IMobile
	    ... this.right ...         - IMobile

	    METHODS FOR THIS CLASS:
	    ... this.totalWeight()      -- int
	    ... this.height()           -- int
	    ... this.torque()           -- int
	    ... this.isBalanced()       -- boolean
	  }
	 */

	/** compute the total weight of this mobile */
	public int totalWeight() {
		return this.left.totalWeight() + this.right.totalWeight();
	}

	/** compute the total height of this mobile */
	public int height() {
		if (this.right.height() > this.left.height()) {
			return this.length + this.right.height();
		}
		else {
			return this.length + this.left.height();
		}
	}

	/** compute the "torque" on this mobile structure */
	public int torque() {
		return (this.right.totalWeight() * this.rightSide) - 
				(this.left.totalWeight() * this.leftSide);
	}



	/** is this mobile is balanced? */
	public boolean isBalanced() {
		if (this.torque() == 0 
				&& this.left.isBalanced() 
				&& this.right.isBalanced()) {
			return true;
		}
		else {
			return false;
		}
	} 
	
	
}