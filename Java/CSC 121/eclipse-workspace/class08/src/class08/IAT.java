package class08;

import java.util.Objects;

/**
 * represents an ancestral tree (family tree)
 */
public interface IAT {

}


/**
 *  represents an unknown person in the tree 
 *   or a branch of the tree we know nothing about
 */
class Unknown implements IAT {
	
	@Override
	public boolean equals(Object other) {
	    return other instanceof Unknown;
	}

	@Override
	public int hashCode() {
	    return Unknown.class.hashCode();
	}

	@Override
	public String toString() {
		return "Unknown []";
	}
	
	
}

/**
 * represents a person in the family tree 
 */
class Person implements IAT {
	String name;
	int yob;
	IAT mother;
	IAT father;
	
	Person(String name, int yob, IAT mother, IAT father) {
		super();
		this.name = name;
		this.yob = yob;
		this.mother = mother;
		this.father = father;
	}

	@Override
	public int hashCode() {
		return Objects.hash(father, mother, name, yob);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		return Objects.equals(father, other.father) && Objects.equals(mother, other.mother)
				&& Objects.equals(name, other.name) && yob == other.yob;
	}

	// OVERLOADING the constructor
	//  define more than one version of the same constructor/method 
	//  with different signatures/headers/parameters
	public Person(String name, int yob) {
		this.name = name;
		this.yob = yob;
		this.mother = new Unknown();
		this.father = new Unknown();
	}
	
	@Override
	public String toString() {
		return "Person [name=" + name + ", yob=" + yob + ", mother=" + mother + ", father=" + father + "]";
	}
	
	
}
