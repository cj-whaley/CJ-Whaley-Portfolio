/**
 * represents an IMobile that contains only one item, no 
 *   additional Simple or Complex IMobiles
 * @author cjwhaley
 */

import java.awt.Color;
import java.util.Objects;

/** to represent an item hanging at the end of a mobile */
class Simple implements IMobile {
	int length;
	int weight;
	Color color;

	Simple(int length, int weight, Color color) {
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
		Simple other = (Simple) obj;
		return Objects.equals(color, other.color) && length == other.length && weight == other.weight;
	}

	@Override
	public String toString() {
		return "Simple [length=" + length + ", weight=" + weight + ", color=" + color + "]";
	}

	/* TEMPLATE:
  public ??? simpleMethod(...) {
     FIELDS:
    ... this.length ...      - int
    ... this.weight ...      - int
    ... this.color ...       - Color

    METHODS FOR THIS CLASS:
	    ... this.totalWeight()      -- int
	    ... this.height()           -- int
	    ... this.torque()           -- int
	    ... this.isBalanced()       -- boolean
  }
	 */

	/** compute the total weight of this mobile */
	public int totalWeight() {
		return this.weight;
	}

	/** compute the total height of this mobile */
	public int height() {
		return this.length;
	}

	/** compute the "torque" on this mobile structure */
	public int torque() {
		return 0;
	}

	/** is this mobile is balanced? */
	public boolean isBalanced() {
		return true;
	}

}